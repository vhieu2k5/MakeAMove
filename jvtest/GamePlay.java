package jvtest;

import java.awt.*;
import java.util.List;
import javax.swing.*;
public class GamePlay extends javax.swing.JFrame {
    public JButton[][] squares = new JButton[8][8];
    boolean clickedAChess = false; //Kiểm tra xem đã bấm vào chess hay chưa
    point currPo = new point(-1,-1);
    Board board = new Board();
    Color turn = Color.WHITE; //Check xem bên nào đang đi true là trắng, false là đen
    public GamePlay() {
        setTitle("Chess Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Tạo bàn cờ
        JPanel chessBoard = new JPanel(new GridLayout(8,8));
        chessBoard.setPreferredSize(new Dimension(500,500));
        boolean white = true;
        for (int row = 0; row < 8; row++) {
            white = !white; // alternate colors each row
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton(); //Mỗi ô là một button
                int r = row;
                int c = col;
                squares[row][col].addActionListener(e-> onSquareClicked(r,c)); //Xác nhận khi nhấn vào một ô
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
       JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       JButton replayBtn = new JButton("Replay");
       controlPanel.add(replayBtn,BorderLayout.CENTER);
       
       add(chessBoard,BorderLayout.CENTER);
       add(controlPanel,BorderLayout.SOUTH);
       board.InitChessPlay(this);
    }
    public static void main(String args[]) {
       
       java.awt.EventQueue.invokeLater(() -> new GamePlay().setVisible(true));
    }

    private void onSquareClicked(int r, int c) {
        //System.out.println(r + " " + c);
        if (Board.chessBoard[r][c].getName()!=null && Board.chessBoard[r][c].getColor()==turn ) {
            if (clickedAChess==false){ //Trường hợp chưa chọn quân nào
                currPo = new point(r,c);
                Board.chessBoard[r][c].showValidMove(this);
                clickedAChess=true;
            }
            else {
                if (clickedAChess==true) { //Trường hợp đã chọn 1 quân nhưng không đi -> Chuyển sang quân khác
                   if (currPo.i>=0 && currPo.j>=0) {
                   Board.chessBoard[currPo.i][currPo.j].deleteValidMove(this);
                    currPo = new point(r,c);
                    Board.chessBoard[r][c].showValidMove(this); 
                } 
                }
            }
        }
        else {
            if (clickedAChess==true) { //Trường hợp đã click 1 quân và đang click vào 1 valid move
                List<point> vlm = Board.chessBoard[currPo.i][currPo.j].ValidMoves();
                for (point t:vlm) //Xét tất cả point hợp lệ để xem cái vị trí bấm có đúng với validMove hiện tại không
                {
                    if (t.i==r && t.j==c) {
                        Board.chessBoard[currPo.i][currPo.j].deleteValidMove(this);
                        Board.chessBoard[currPo.i][currPo.j].setMove(currPo, r,c);
                        //System.out.println("Da move");
                        squares[currPo.i][currPo.j].setText(""); //Sau khi thay đổi xong trong mảng dữ liệu, cập nhật lại ui
                        squares[r][c].setText(Board.chessBoard[r][c].symbol);
                        squares[r][c].setFont(new Font("Serif", Font.BOLD, 36));
                        squares[r][c].setForeground(Board.chessBoard[r][c].color);
                        //System.out.println("UI xong");
                        currPo = new point(-1,-1);
                        clickedAChess=false;
                        turn = SwitchTurn(turn);
                        //System.out.println(turn.toString());
                    }
                }
            }
        }
    }
    
    Color SwitchTurn(Color turn) {
        if (turn==Color.WHITE) {
            return Color.BLACK;
        }
        else {
            return Color.WHITE;
        }
    } 
}
