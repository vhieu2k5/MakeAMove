package jvtest;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class GamePlay extends javax.swing.JFrame {

    public static JButton[][] squares = new JButton[8][8];
    public JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    public JPanel ArchievePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    boolean clickedAChess = false; // Kiểm tra xem đã bấm vào chess hay chưa
    point currPo = new point(-1, -1);
    Board board = new Board();
    public JLabel CMtext = new JLabel("");
    public JLabel BlackArchieve = new JLabel(" ");
    public JLabel WhiteArchieve = new JLabel(" ");

    public static Color turn = Color.WHITE; // Check xem bên nào đang đi true là trắng, false là đen

    public GameTimer timerChess;
    public JLabel whiteTimerLabel, blackTimerLabel;

    public GamePlay(int minutes) {
        setTitle("Chess Game");
        setSize(700, 700);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        whiteTimerLabel = new JLabel("White: " + String.format("%02d:00", minutes));
        blackTimerLabel = new JLabel("Black: " + String.format("%02d:00", minutes));
        whiteTimerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        blackTimerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        whiteTimerLabel.setForeground(Color.DARK_GRAY);
        blackTimerLabel.setForeground(Color.BLACK);
        whiteTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        blackTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.add(whiteTimerLabel, BorderLayout.NORTH);
        timerPanel.add(blackTimerLabel, BorderLayout.SOUTH);
        add(timerPanel, BorderLayout.EAST);

        if (minutes > 0) {
            timerChess = new GameTimer(minutes, new GameTimer.OnTimeChangeListener() {
                @Override
                public void onWhiteTimeChange(int whiteSeconds) {
                    whiteTimerLabel.setText("White: " + timerChess.formatTime(whiteSeconds));
                }

                @Override
                public void onBlackTimeChange(int blackSeconds) {
                    blackTimerLabel.setText("Black: " + timerChess.formatTime(blackSeconds));
                }

                @Override
                public void onTimeUp(String side) {
                    JOptionPane.showMessageDialog(GamePlay.this,
                        side + " Chiến Thắng ",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }
            });
    
        timerChess.start();
        }
        else {
            timerChess = null;
            // whiteTimerLabel.setText("Not Limited Time");
            // blackTimerLabel.setText("Not Limited Time");
            whiteTimerLabel.setVisible(false);
            blackTimerLabel.setVisible(false);
        }

        // Tạo bàn cờ
        JPanel chessBoard = new JPanel(new GridLayout(8, 8));
        chessBoard.setPreferredSize(new Dimension(500, 500));
        boolean white = true;
        for (int row = 0; row < 8; row++) {
            white = !white; // alternate colors each row
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton(); //Mỗi ô là một button
                int r = row;
                int c = col;
                squares[row][col].addActionListener(_ -> onSquareClicked(r, c)); //Xác nhận khi nhấn vào một ô
                squares[row][col].setOpaque(true);
                squares[row][col].setBorderPainted(false);

                // Set square color
                if (white) {
                    squares[row][col].setBackground(new Color(240, 217, 181)); // light
                } else {

                    squares[row][col].setBackground(new Color(181, 136, 99));  // dark
                }
                white = !white;

                chessBoard.add(squares[row][col]);
            }
        }

        // ===================Control Panel================

        JButton pauseBtn = new JButton("Pause");
        controlPanel.add(pauseBtn, BorderLayout.CENTER);
        controlPanel.add(CMtext, BorderLayout.EAST);
        ArchievePanel.add(BlackArchieve, BorderLayout.WEST);
        ArchievePanel.add(WhiteArchieve, BorderLayout.EAST);
        ArchievePanel.setBackground(Color.gray);
        add(chessBoard, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(ArchievePanel, BorderLayout.NORTH);
        board.InitChessPlay();

        // PauseBtn
        pauseBtn.addActionListener(e -> {
            if(timerChess != null){
                timerChess.stop();
            }
            new PausePanel(timerChess,this, minutes).setVisible(true);
        });
    }


    public  void Warning(Board board) {
        if (board.blackKing4.isCheckMate()) {
            CMtext.setText("CHECKMATE!"); 
            if (this.isLose()) CMtext.setText("YOU LOSE!!!");  

        }else if (CMtext.getText().compareTo("CHECKMATE!") == 0) {
            CMtext.setText("");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GamePlay(10).setVisible(true));
    }

    private void onSquareClicked(int r, int c) {
        //System.out.println(r + " " + c);
        if (Board.chessBoard[r][c].getName() != null && Board.chessBoard[r][c].getColor() == turn) {
            if (clickedAChess == false) { //Trường hợp chưa chọn quân nào
                currPo = new point(r, c);
                Board.chessBoard[r][c].showValidMove();
                clickedAChess = true;
            } else {
                if (clickedAChess == true) { //Trường hợp đã chọn 1 quân nhưng không đi -> Chuyển sang quân khác
                    if (currPo.i >= 0 && currPo.j >= 0) {
                        Board.chessBoard[currPo.i][currPo.j].deleteValidMove();
                        currPo = new point(r, c);
                        Board.chessBoard[r][c].showValidMove();
                    }
                }
            }
        } else {
            if (clickedAChess == true) { //Trường hợp đã click 1 quân và đang click vào 1 valid move
                List<point> vlm = Board.chessBoard[currPo.i][currPo.j].ValidMoves();
                for (point t : vlm) //Xét tất cả point hợp lệ để xem cái vị trí bấm có đúng với validMove hiện tại không
                {
                    if (t.i == r && t.j == c) {
                        Board.chessBoard[currPo.i][currPo.j].deleteValidMove();        
                        //Di chuyển Nhập thành(Castle)
                        if (t.name!=null && t.name.equals("Castle")) {
                            int co = 0; //Màu để biết nên lấy con xe ở hàng nào
                            if (Board.chessBoard[currPo.i][currPo.j].color==Color.BLACK) {
                                co=7;
                            }
                            //Check hiệu của vị trí hiện tại và mục tiêu để xem phải hay trái
                            if (currPo.j-c<0) //Bên phải
                            {
                                Board.chessBoard[co][7].setMove(new point(co,7),r,c-1);
                                squares[co][7].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
                                squares[co][c-1].setText(Board.chessBoard[co][c-1].symbol);
                                squares[co][c-1].setFont(new Font("Serif", Font.BOLD, 36));
                                squares[co][c-1].setForeground(Board.chessBoard[co][c-1].color);
                                King k = (King)Board.chessBoard[currPo.i][currPo.j];
                                k.setMoveCount(1);
                                Board.chessBoard[currPo.i][currPo.j].setMove(currPo, r, c);
                            }
                            else {
                                Board.chessBoard[co][0].setMove(new point(co,0),r,c+1);
                                squares[co][0].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
                                squares[co][c+1].setText(Board.chessBoard[co][c+1].symbol);
                                squares[co][c+1].setFont(new Font("Serif", Font.BOLD, 36));
                                squares[co][c+1].setForeground(Board.chessBoard[co][c+1].color);
                                King k = (King)Board.chessBoard[currPo.i][currPo.j];
                                k.setMoveCount(1);
                                Board.chessBoard[currPo.i][currPo.j].setMove(currPo, r, c);
                            }
                        }
                        else {
                            //Di chuyển bình thường

                            if (Board.chessBoard[currPo.i][currPo.j].name.equals("King")) {
                                King k = (King)Board.chessBoard[currPo.i][currPo.j];
                                k.setMoveCount(1);
                            }
                            String sym = Board.chessBoard[r][c].symbol;
                            if (Board.chessBoard[r][c].is_Chess) SetArchieve(sym);
                            Board.chessBoard[currPo.i][currPo.j].setMove(currPo, r, c);
                            //
                            ChessPiece moved = Board.chessBoard[r][c];
                            if (moved.name.equals("Pawn") && (r == 0 || r == 7)) {
                                promotePawn(r, c, moved.color);
                                }

                        }
                        squares[currPo.i][currPo.j].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
                        squares[r][c].setText(Board.chessBoard[r][c].symbol);
                        squares[r][c].setFont(new Font("Serif", Font.BOLD, 36));
                        squares[r][c].setForeground(Board.chessBoard[r][c].color);
                        //System.out.println("UI xong");
                        currPo = new point(-1, -1);
                        clickedAChess = false;
                        turn = SwitchTurn(turn);
                        Warning(board); //Warning Chieeus Tuowngs!
                        //System.out.println(turn.toString());
                        timerChess.switchTurn();
                    }
                }
            }
        }

        //  if (Board.chessBoard[r][c].name!=null && Board.chessBoard[r][c].name.compareTo("King")==0) showCheckMateMove();
    }

    Color SwitchTurn(Color turn) {
        if (turn == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    public static void showAllMove() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece cp = Board.chessBoard[i][j];
                if (cp.name != null && !cp.ValidMoves().isEmpty() && cp.color != turn) {
                    Board.chessBoard[i][j].showValidMove();
                }
            }
        }
    }

    public void SetArchieve(String sym) {
        if (turn != Color.BLACK) {
            BlackArchieve.setText(BlackArchieve.getText() + sym);
            BlackArchieve.setForeground(Color.BLACK);
        } else {
            WhiteArchieve.setText(WhiteArchieve.getText() + sym);
            WhiteArchieve.setForeground(Color.white);
        }
    }

    public static void Result() {
        //System.out.print(Ches);
    }
    
    private void promotePawn(int r, int c, Color color) {
    String[] options = {"Queen", "Rock", "Bishop", "Knight"};
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Chọn quân để phong cấp:",
            "Phong cấp tốt",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            "Queen"
    );

    if (choice != null) {
        ChessPiece newPiece = switch (choice) {
            case "Rock" -> new Rock(r, c, color);
            case "Bishop" -> new Bishop(r, c, color);
            case "Knight" -> new Knight(r, c, color);
            default -> new Queen(r, c, color);
        };

        // Cập nhật trong mảng dữ liệu
        Board.chessBoard[r][c] = newPiece;

        // Cập nhật giao diện
        squares[r][c].setText(newPiece.symbol);
        squares[r][c].setFont(new Font("Serif", Font.BOLD, 36));
        squares[r][c].setForeground(color);
    }
}

    public boolean isLose() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = Board.chessBoard[i][j];
                if (piece == null || !piece.is_Chess) {
                    continue;
                }
                if (piece.color != turn) {
                    continue;
                }

                List<point> validMoves = piece.ValidMoves();
                if (validMoves == null || validMoves.isEmpty()) {
                    continue;
                }

                point fromPoint = new point(piece.x, piece.y);

                for (point p : validMoves) {
                    ChessPiece captured = Board.chessBoard[p.i][p.j];
                    piece.makeMove(p.i, p.j);
                  //  System.out.println("Moved " + piece.name + " to " + p.i + " " + p.j);
                    boolean check = board.blackKing4.isCheckMate();
                    //Undo
                    Board.chessBoard[p.i][p.j] = captured;
                    Board.chessBoard[fromPoint.i][fromPoint.j] = piece;
                    piece.x = fromPoint.i;
                    piece.y = fromPoint.j;
                  //  System.out.println("Moved back to " + fromPoint.i + " " + fromPoint.j);
                    if (!check) {
                        System.out.println("You can move the " + Board.chessBoard[i][j].name + "-" + Board.chessBoard[i][j].color + " to " + p.i + " " + p.j);
                        return false;
                    }

                }
            }
        }
        return true; // every possible move keeps king in check
    }
}
