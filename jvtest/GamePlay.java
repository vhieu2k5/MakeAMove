package jvtest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import make_a_move.DAOHistory;

public class GamePlay extends javax.swing.JFrame {

    public static JButton[][] squares = new JButton[8][8];
    public JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    public JPanel ArchievePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    boolean clickedAChess = false; // Kiểm tra xem đã bấm vào chess hay chưa
    point currPo = new point(-1, -1);
    Board board = new Board();
    public static List<Move> priorityMoves = new ArrayList<>();
    public static boolean isCheckedMate = false;
    public static boolean isGameOver = false;
    public static JLabel CMtext = new JLabel("");
    public static JLabel BlackArchieve = new JLabel(" ");
    public static JLabel WhiteArchieve = new JLabel(" ");

    public static Color turn = Color.white; //Check xem bên nào đang đi true là trắng, false là đen

    public GameTimer timerChess;
    public JLabel whiteTimerLabel, blackTimerLabel;
    
    //DB
    private boolean isSaved = false;
    private int currentUserID;
    private String currentUserName;
    public String gameMode;



    public GamePlay(int minutes, String gameMode, int currentUserID, String currentUserName) {
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

                    if (!isSaved) {
                        if (side.equalsIgnoreCase("White")) {
                            saveResultToDB("Win");   // nếu người chơi là White thắng
                        } else {
                            saveResultToDB("Lose");  // nếu người chơi là Black thắng
                        }
                        isGameOver = true;
                    }
                }
            });

            timerChess.start();
        } else {
            timerChess = null;
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
            if (timerChess != null) {
                timerChess.stop();
            }
            new PausePanel(timerChess, this, minutes).setVisible(true);
        });

        JPanel undoPanel = new JPanel(new BorderLayout());
        JButton undoWhiteBtn = new JButton("↶");
        JButton undoBlackBtn = new JButton("↶");
        undoPanel.add(undoWhiteBtn, BorderLayout.SOUTH);
        undoPanel.add(undoBlackBtn, BorderLayout.NORTH);
       // add(undoPanel, BorderLayout.WEST);
    }


    public void Warning() {

        if (Board.blackKing4.isCheck()) {
            CMtext.setText("CHECKMATE!");
            boolean check = BlackisLose();
            if (check) {
                CMtext.setText("You Lose!!!!!!");
                isGameOver = true;
                JOptionPane.showMessageDialog(this,
                        "Black Chiến Thắng ",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                //Database
                if (!isSaved) {
                    saveResultToDB("Lose");
                }
                //
            }
        } else if (CMtext.getText().compareTo("CHECKMATE!") == 0) {

            CMtext.setText("");
        }

        Board.getAllValidMoves(Color.white.toString());

        if (Board.blackKing4.isCheck()) {
            CMtext.setText("CHECKMATE the Black King!");
            boolean check = BlackisLose();
            if (check) {
                CMtext.setText("You Win!!!!!!");
                isGameOver = true; 
                JOptionPane.showMessageDialog(this,
                        "White Chiến Thắng ",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                if (!isSaved) {
                    saveResultToDB("Win");
                }
            }
        } else if (CMtext.getText().contains("CHECKMATE")) {
            CMtext.setText("");
        }
        Board.getAllValidMoves(Color.BLACK.toString());
        if (Board.whiteKing4.isCheck()) {
            isCheckedMate = true;
            CMtext.setText("CHECKMATE the White King!");
            boolean check = WhiteisLose();
            Board.showPriorityMoves();
            if (check) {
                CMtext.setText("You Lose!!!!!!");
                isGameOver = true;
            }
        } else if (CMtext.getText().contains("CHECKMATE")) {
            CMtext.setText("");
            isCheckedMate = false;
        }


               
    }
    public void saveResultToDB(String result){
        if(currentUserID <= 0 || currentUserName == null){
            System.err.println("Không có thông tin người dùng, không thể lưu lịch sử");
            return;
        }
        DAOHistory dao = new DAOHistory();
        boolean ok = dao.saveGameRes(gameMode, currentUserID, currentUserName, result);
        if(ok){
            System.out.println("Đã lưu lịch sử: " + result);
            isSaved = true;
        }
        else{
            System.err.println("Lưu lịch sử thất bại");
        }
    }
        

    public void WhiteEasyBotMove() {
        ChessBot bot = new ChessBot();
        Move bestMove;
        bestMove = bot.findBestMove(Board.chessBoard);

        if (bestMove != null) {
            ChessPiece piece = Board.chessBoard[bestMove.fromX][bestMove.fromY];
            piece.setMove(new point(piece.x, piece.y), bestMove.toX, bestMove.toY);

            // Update UI
            squares[bestMove.fromX][bestMove.fromY].setText("");
            squares[bestMove.toX][bestMove.toY].setText(piece.symbol);
            squares[bestMove.toX][bestMove.toY].setFont(new Font("Serif", Font.BOLD, 36));
            squares[bestMove.toX][bestMove.toY].setForeground(piece.color);

        }
        turn = SwitchTurn(turn);
        System.out.println("Player's Turn!");
        Warning();
    }

    public void WhiteLevelBotMove(int level) {
        StockfishBot bot = new StockfishBot();
        bot.startEngine("../MakeAMove/jvtest/engine/stockfish-windows-x86-64-avx2.exe");
        String fen = Board.generateFEN(turn);
        String move = bot.getBestMove(fen, level);
        Move bestMove = Board.translateChessCode(move);

        if (bestMove != null) {
            ChessPiece piece = Board.chessBoard[bestMove.fromX][bestMove.fromY];

            if (piece.name.equals("King") && (Math.abs(bestMove.fromY - bestMove.toY) > 1)) {
                Castle(bestMove.toX, bestMove.toY, new point(piece.x, piece.y));
            }

            piece.setMove(new point(piece.x, piece.y), bestMove.toX, bestMove.toY);

            if (piece.name.equals("Pawn") && bestMove.toX == 7) {
                promotePawn(bestMove.toX, bestMove.toY, new point(bestMove.toX, bestMove.toY), Color.BLACK);
                piece = Board.chessBoard[bestMove.toX][bestMove.toY];
            }
            // Update UI
            squares[bestMove.fromX][bestMove.fromY].setText("");
            squares[bestMove.toX][bestMove.toY].setText(piece.symbol);
            squares[bestMove.toX][bestMove.toY].setFont(new Font("Serif", Font.BOLD, 36));
            squares[bestMove.toX][bestMove.toY].setForeground(piece.color);

        }
        System.out.println("The Valid moves of King:");
        for (point p : Board.whiteKing4.ValidMoves()) {
            System.out.println(p.i + "-" + p.j);
        }
        System.out.println("_____________________________");
        turn = SwitchTurn(turn);
        System.out.println("Player's Turn!");
        Warning();
    }

    private void onSquareClicked(int r, int c) {
        //System.out.println(r + " " + c);
        if (Board.chessBoard[r][c].getName() == null) {
            System.out.println("Null chosen one!");
        }
        if (!isGameOver) {
            if (Board.chessBoard[r][c].getName() != null && Board.chessBoard[r][c].getColor() == turn) {
                // System.out.println("Choosing in " + Board.chessBoard[r][c].getName() + " " + turn.toString());
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
                            String sym = Board.chessBoard[r][c].symbol;

                            if (Board.chessBoard[r][c].is_Chess) {
                                SetArchieve(sym);
                            }
                            Board.chessBoard[currPo.i][currPo.j].deleteValidMove();
                            //Nhap thanh
                            if (t.name != null && t.name.equals("Castle")) {
                                Castle(r, c, currPo);
                            } else {
                                //Di chuyển bình thường
                                if (Board.chessBoard[currPo.i][currPo.j].name.equals("King")) {
                                    King k = (King) Board.chessBoard[currPo.i][currPo.j];
                                    k.setMoveCount(1);
                                }
                                ChessPiece moved = Board.chessBoard[currPo.i][currPo.j];

                                if (moved.name != null && moved.name.equals("Pawn") && (r == 0 || r == 7)) {
                                    System.out.println("Phong hậu được!");
                                    promotePawn(r, c, currPo, moved.color);
                                }

                            }
                            Board.chessBoard[currPo.i][currPo.j].setMove(currPo, r, c);
                            //System.out.println("Da move");
                            squares[currPo.i][currPo.j].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
                            squares[r][c].setText(Board.chessBoard[r][c].symbol);
                            squares[r][c].setFont(new Font("Serif", Font.BOLD, 36));
                            squares[r][c].setForeground(Board.chessBoard[r][c].color);
                            //System.out.println("UI xong");
                            currPo = new point(-1, -1);
                            clickedAChess = false;
                            turn = SwitchTurn(turn);
                            System.out.println("Bot is Processing...");
                            // 
                            Warning();
                            if (!isGameOver) {
                                switch (MenuGame.level) {
                                    case 0 -> {
                                    }
                                    case 1 ->
                                        WhiteEasyBotMove();
                                    case 2 ->
                                        WhiteLevelBotMove(5);
                                    case 3 ->
                                        WhiteLevelBotMove(15);
                                    default -> {
                                    }
                                }
                            }
                            Warning(); //Warning Chieeus Tuowngs!
                            //System.out.println(turn.toString());
                            if (timerChess != null) {
                                timerChess.switchTurn();
                            }
                        }
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

    public boolean BlackisLose() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = Board.chessBoard[i][j];
                if (piece == null || !piece.is_Chess) {
                    continue;
                }
                if (piece.color != Color.BLACK) {
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
                    // System.out.println("Moved " + piece.name + " to " + p.i + " " + p.j);
                    boolean check = Board.blackKing4.isCheck();
                    //Undo
                    Board.chessBoard[p.i][p.j] = captured;
                    Board.chessBoard[fromPoint.i][fromPoint.j] = piece;
                    piece.x = fromPoint.i;
                    piece.y = fromPoint.j;
                    // System.out.println("Moved back to " + fromPoint.i + " " + fromPoint.j);
                    if (!check) {
                        // System.out.println("You can move the " + Board.chessBoard[i][j].name + "-" + Board.chessBoard[i][j].color + " to " + p.i + " " + p.j);
                        return false;
                    }

                }
            }
        }
        return true; // every possible move keeps king in check
    }

    public boolean WhiteisLose() {
        priorityMoves = new ArrayList<>();
        boolean finalcheck = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = Board.chessBoard[i][j];
                if (piece == null || !piece.is_Chess) {
                    continue;
                }
                if (piece.color != Color.white) {
                    continue;
                }
                List<point> moves;
                moves = piece.ValidMoves();
                if (moves == null || moves.isEmpty()) {
                    continue;
                }
                point fromPoint = new point(piece.x, piece.y);

                for (point p : moves) {
                    ChessPiece captured = Board.chessBoard[p.i][p.j];
                    piece.makeMove(p.i, p.j);
                    // System.out.println("Moved " + piece.name + " to " + p.i + " " + p.j);
                    Board.getAllValidMoves(Color.black.toString()); //Reset Valid Moves cho chessboard
                    boolean check = Board.whiteKing4.isCheck();
                    //Undo
                    Board.chessBoard[p.i][p.j] = captured;
                    Board.chessBoard[fromPoint.i][fromPoint.j] = piece;
                    piece.x = fromPoint.i;
                    piece.y = fromPoint.j;
                    // System.out.println("Moved back to " + fromPoint.i + " " + fromPoint.j);
                    if (!check) {
                        finalcheck = false;
                        //  System.out.println("Suggestion: You can move the " + Board.chessBoard[i][j].name + "-" + Board.chessBoard[i][j].color + " to " + p.i + " " + p.j);
                        priorityMoves.add(new Move(i, j, p.i, p.j));
                        break;
                    }

                }
            }
        }
        return finalcheck; // every possible move keeps king in check
    }

    private void promotePawn(int r, int c, point currPo, Color color) {
        String[] options = {"Queen", "Rock", "Bishop", "Knight"};
        String choice;
        if (color != Color.BLACK) {
            choice = (String) JOptionPane.showInputDialog(
                    this,
                    "Chọn quân để phong cấp:",
                    "Phong cấp tốt",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    "Queen"
            );
        } else {
            choice = "Queen";
        }

        if (choice != null) {
            ChessPiece newPiece = switch (choice) {
                case "Rock" ->
                    new Rock(r, c, color);
                case "Bishop" ->
                    new Bishop(r, c, color);
                case "Knight" ->
                    new Knight(r, c, color);
                case "Queen" ->
                    new Queen(r, c, color);
                default ->
                    new Queen(r, c, color);
            };

            // Cập nhật trong mảng dữ liệu
            Board.chessBoard[currPo.i][currPo.j] = newPiece;

            // Cập nhật giao diện
            squares[currPo.i][currPo.j].setText(newPiece.symbol);
            squares[currPo.i][currPo.j].setFont(new Font("Serif", Font.BOLD, 36));
            squares[currPo.i][currPo.j].setForeground(color);
        }
    }

    private void Castle(int r, int c, point currPo) {
        int co = 7; //Màu để biết nên lấy con xe ở hàng nào
        if (Board.chessBoard[currPo.i][currPo.j].color == Color.BLACK) {
            co = 0;
        }
        //Check hiệu của vị trí hiện tại và mục tiêu để xem phải hay trái
        if (currPo.j - c < 0) //Bên phải
        {
            Board.chessBoard[co][7].setMove(new point(co, 7), r, c - 1);
            squares[co][7].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
            squares[co][c - 1].setText(Board.chessBoard[co][c - 1].symbol);
            squares[co][c - 1].setFont(new Font("Serif", Font.BOLD, 36));
            squares[co][c - 1].setForeground(Board.chessBoard[co][c - 1].color);
            King k = (King) Board.chessBoard[currPo.i][currPo.j];
            k.setMoveCount(1);
        } else {
            Board.chessBoard[co][0].setMove(new point(co, 0), r, c + 1);
            squares[co][0].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
            squares[co][c + 1].setText(Board.chessBoard[co][c + 1].symbol);
            squares[co][c + 1].setFont(new Font("Serif", Font.BOLD, 36));
            squares[co][c + 1].setForeground(Board.chessBoard[co][c + 1].color);
            King k = (King) Board.chessBoard[currPo.i][currPo.j];
            k.setMoveCount(1);
        }
    }
}
