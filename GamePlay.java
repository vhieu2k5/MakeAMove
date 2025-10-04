package jvtest;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class GamePlay extends javax.swing.JFrame {
    public static final JButton[][] squares = new JButton[8][8];
    Board board = new Board();
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
                squares[row][col].addActionListener(e-> onSquareClicked(r,c));
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
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int index = r*8+c;
        point currPo = Board.chessBoard.get(index);
        if (currPo.piece!=null) {
            switch(currPo.piece) {
                case "Pawn":
                    Pawn p = new Pawn(r,c,currPo.c);
                    for (point i:p.ValidMoves()) {
                        squares[i.i][i.j].setText("⚫");
                        squares[i.i][i.j].setFont(new Font("Serif", Font.BOLD, 36));
                        squares[i.i][i.j].setForeground(Color.GREEN);
                    }
                    break;
            }
        }
    }
}
