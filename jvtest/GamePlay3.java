package jvtest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import make_a_move.DAOHistory;

public class GamePlay3 extends javax.swing.JFrame {

    public static JButton[][] squares = new JButton[8][8];
    public JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    public JPanel ArchievePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    boolean clickedAChess = false;
    point currPo = new point(-1, -1);
    Board board = new Board();
    public static List<Move> priorityMoves = new ArrayList<>();
    boolean isCheckedMate = false;
    boolean isGameOver = false;
    public static JLabel CMtext = new JLabel("");
    public static JLabel BlackArchieve = new JLabel(" ");
    public static JLabel WhiteArchieve = new JLabel(" ");

    public static Color turn = Color.white;

    public GameTimer timerChess;
    public JLabel whiteTimerLabel, blackTimerLabel;
    
    //DB
    private boolean isSaved = false;
    private int currentUserID;
    private String currentUserName;
    private String gameMode;

    public GamePlay3(int minutes, String gameMode, int currentUserID, String currentUserName) {
        setTitle("Chess Game");
        setSize(800, 800);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        this.currentUserID = currentUserID;
        this.currentUserName = currentUserName;
        this.gameMode = gameMode;

        whiteTimerLabel = new JLabel("White: " + String.format("%02d:00", minutes));
        blackTimerLabel = new JLabel("Black: " + String.format("%02d:00", minutes));
        whiteTimerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        blackTimerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        whiteTimerLabel.setForeground(Color.DARK_GRAY);
        blackTimerLabel.setForeground(Color.BLACK);
        whiteTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        blackTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.add(whiteTimerLabel, BorderLayout.SOUTH);
        timerPanel.add(blackTimerLabel, BorderLayout.NORTH);
        add(timerPanel, BorderLayout.EAST);

        // Timer
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
                    JOptionPane.showMessageDialog(GamePlay3.this,
                        side + " Chiến Thắng ",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            timerChess.start();
        } else {
            timerChess = null;
            whiteTimerLabel.setVisible(false);
            blackTimerLabel.setVisible(false);
        }

        // Chess Board
        JPanel chessBoard = new JPanel(new GridLayout(8, 8));
        chessBoard.setPreferredSize(new Dimension(500, 500));
        boolean white = true;
        for (int row = 0; row < 8; row++) {
            white = !white;
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                int r = row, c = col;
                squares[row][col].addActionListener(_ -> onSquareClicked(r, c));
                squares[row][col].setOpaque(true);
                squares[row][col].setBorderPainted(false);
                if (white) squares[row][col].setBackground(new Color(240, 217, 181));
                else squares[row][col].setBackground(new Color(181, 136, 99));
                white = !white;
                chessBoard.add(squares[row][col]);
            }
        }

        // Panels
        JButton pauseBtn = new JButton("Pause");
        controlPanel.add(pauseBtn);
        controlPanel.add(CMtext);
        ArchievePanel.add(BlackArchieve);
        ArchievePanel.add(WhiteArchieve);
        ArchievePanel.setBackground(Color.gray);
        add(chessBoard, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(ArchievePanel, BorderLayout.NORTH);

        board.InitChessPlay();

        pauseBtn.addActionListener(e -> {
            if(timerChess != null){
                timerChess.stop();
            }
            new PausePanel3(timerChess,this, minutes).setVisible(true);
        });
    }

        // Undo buttons
//        JPanel undoPanel = new JPanel(new BorderLayout());
//        JButton undoWhiteBtn = new JButton("⟲");
//        JButton undoBlackBtn = new JButton("⟲");
//
//        undoPanel.add(undoWhiteBtn, BorderLayout.SOUTH);
//        undoPanel.add(undoBlackBtn, BorderLayout.NORTH);
//
//        undoWhiteBtn.addActionListener(e -> undoLastMove());
//        undoBlackBtn.addActionListener(e -> undoLastMove());
//
//    }

//    public void undoLastMove() {
//        if (history.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không có nước đi để hoàn tác!");
//            return;
//        }
//
//        MoveHistory last = history.remove(history.size() - 1);
//
//        // trả quân về vị trí cũ
//        Board.chessBoard[last.fromR][last.fromC] = last.movedPiece;
//        Board.chessBoard[last.toR][last.toC] = last.capturedPiece;
//
//        // cập nhật UI
//        squares[last.fromR][last.fromC].setText(last.movedPiece.symbol);
//        squares[last.fromR][last.fromC].setForeground(last.movedPiece.color);
//
//        if (last.capturedPiece == null) {
//            squares[last.toR][last.toC].setText("");
//        } else {
//            squares[last.toR][last.toC].setText(last.capturedPiece.symbol);
//            squares[last.toR][last.toC].setForeground(last.capturedPiece.color);
//        }
//
//        // trả lại lượt
//        turn = last.prevTurn;
//
//        // trả lại timer
//        if (timerChess != null)
//            timerChess.switchTurn();
//
//        clickedAChess = false;
//        currPo = new point(-1, -1);
//    }


    public void Warning(Board board) {

        if (board.blackKing4.isCheckMate()) {
            CMtext.setText("CHECKMATE!");
            boolean check = BlackisLose();
            if (check) {
                CMtext.setText("You Lose!!!!!!");
                isGameOver = true;
                if (!isSaved) saveResultToDB("1vs1","Lose");               //Thua
            }
        } else if (CMtext.getText().equals("CHECKMATE!")) {
            CMtext.setText("");
        }

        if (board.whiteKing4.isCheckMate()) {
            isCheckedMate = true;
            CMtext.setText("CHECKMATE!");
            boolean check = WhiteisLose();
            if (check) {
                CMtext.setText("You Win!!!!!!");
                isGameOver = true;
                if (!isSaved) saveResultToDB("1v1","Win");                //Thắng
            } else if (CMtext.getText().equals("CHECKMATE!")) {
                CMtext.setText("");
                isCheckedMate = false;
            }
        }
    }
    
    //Lưu kết quả vào SQL
    public void saveResultToDB(String gamemMode, String result){
        
        if(currentUserID <= 0 || currentUserName == null){
            System.err.println("Khong co thong tin nguoi dung, khong the luu lich su!");
            return;
        }
        DAOHistory dao = new DAOHistory();
        boolean ok = dao.saveGameRes(gameMode, currentUserID, currentUserName, result);
        if(ok){
            System.out.println("Đa luu lich su: " + result);
            isSaved = true;
        }
        else{
            System.err.println("Luu lich su that bai");
        }
    }

    private void onSquareClicked(int r, int c) {

        if (!isGameOver) {

            if (Board.chessBoard[r][c].getName() != null && Board.chessBoard[r][c].getColor() == turn) {

                if (!clickedAChess) {
                    currPo = new point(r, c);
                    Board.chessBoard[r][c].showValidMove();
                    clickedAChess = true;
                } else {
                    if (currPo.i >= 0 && currPo.j >= 0) {
                        Board.chessBoard[currPo.i][currPo.j].deleteValidMove();
                        currPo = new point(r, c);
                        Board.chessBoard[r][c].showValidMove();
                    }
                }
            } 
            
            else if (clickedAChess) {

                List<point> vlm = Board.chessBoard[currPo.i][currPo.j].ValidMoves();
                for (point t : vlm) {
                    if (t.i == r && t.j == c) {

                        String sym = Board.chessBoard[r][c].symbol;
                        if (Board.chessBoard[r][c].is_Chess) SetArchieve(sym);

                        Board.chessBoard[currPo.i][currPo.j].deleteValidMove();

                        // Castle
                        if (t.name != null && t.name.equals("Castle")) {
                            int co = Board.chessBoard[currPo.i][currPo.j].color == Color.BLACK ? 0 : 7;

                            if (currPo.j - c < 0) {
                                Board.chessBoard[co][7].setMove(new point(co, 7), r, c - 1);
                                squares[co][7].setText("");
                                squares[co][c - 1].setText(Board.chessBoard[co][c - 1].symbol);
                                squares[co][c - 1].setFont(new Font("Serif", Font.BOLD, 36));
                                squares[co][c - 1].setForeground(Board.chessBoard[co][c - 1].color);
                            } else {
                                Board.chessBoard[co][0].setMove(new point(co, 0), r, c + 1);
                                squares[co][0].setText("");
                                squares[co][c + 1].setText(Board.chessBoard[co][c + 1].symbol);
                                squares[co][c + 1].setFont(new Font("Serif", Font.BOLD, 36));
                                squares[co][c + 1].setForeground(Board.chessBoard[co][c + 1].color);
                            }
                        }
                        else {
                            // Promotion
                            ChessPiece moved = Board.chessBoard[currPo.i][currPo.j];
                            if (moved.name.equals("Pawn") && (r == 0 || r == 7)) {
                                promotePawn(r, c, moved.color);
                            }
                        }

                        // Move piece
                        ChessPiece movedPiece = Board.chessBoard[currPo.i][currPo.j];
                        ChessPiece capturedPiece = Board.chessBoard[r][c];

                        Board.chessBoard[currPo.i][currPo.j].setMove(currPo, r, c);

                        squares[currPo.i][currPo.j].setText("");
                        squares[r][c].setText(Board.chessBoard[r][c].symbol);
                        squares[r][c].setFont(new Font("Serif", Font.BOLD, 36));
                        squares[r][c].setForeground(Board.chessBoard[r][c].color);

                        currPo = new point(-1, -1);
                        clickedAChess = false;
                        turn = SwitchTurn(turn);

                        if (timerChess != null) timerChess.switchTurn();        //tránh lỗi trường hợp no time
                        Warning(board);
                    }
                }
            }
        }
    }

    Color SwitchTurn(Color turn) {
        return (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
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

    public boolean BlackisLose() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = Board.chessBoard[i][j];
                if (piece == null || !piece.is_Chess) continue;
                if (piece.color != turn) continue;

                List<point> validMoves = piece.ValidMoves();
                if (validMoves == null || validMoves.isEmpty()) continue;

                point fromPoint = new point(piece.x, piece.y);

                for (point p : validMoves) {
                    ChessPiece captured = Board.chessBoard[p.i][p.j];
                    piece.makeMove(p.i, p.j);

                    boolean check = board.blackKing4.isCheckMate();

                    Board.chessBoard[p.i][p.j] = captured;
                    Board.chessBoard[fromPoint.i][fromPoint.j] = piece;
                    piece.x = fromPoint.i;
                    piece.y = fromPoint.j;

                    if (!check) return false;
                }
            }
        }
        return true;
    }

    public boolean WhiteisLose() {
        priorityMoves = new ArrayList<>();
        boolean finalcheck = true;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = Board.chessBoard[i][j];
                if (piece == null || !piece.is_Chess) continue;
                if (piece.color != turn) continue;

                List<point> moves = piece.ValidMoves();
                if (moves == null || moves.isEmpty()) continue;

                point fromPoint = new point(piece.x, piece.y);

                for (point p : moves) {
                    ChessPiece captured = Board.chessBoard[p.i][p.j];
                    piece.makeMove(p.i, p.j);

                    boolean check = board.whiteKing4.isCheckMate();

                    Board.chessBoard[p.i][p.j] = captured;
                    Board.chessBoard[fromPoint.i][fromPoint.j] = piece;
                    piece.x = fromPoint.i;
                    piece.y = fromPoint.j;

                    if (!check) {
                        finalcheck = false;
                        priorityMoves.add(new Move(i, j, p.i, p.j));
                    }
                }
            }
        }

        return finalcheck;
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

            Board.chessBoard[currPo.i][currPo.j] = newPiece;

            squares[currPo.i][currPo.j].setText(newPiece.symbol);
            squares[currPo.i][currPo.j].setFont(new Font("Serif", Font.BOLD, 36));
            squares[currPo.i][currPo.j].setForeground(color);
        }
    }
}
