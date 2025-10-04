import java.awt.*;
import javax.swing.*;
public class GamePlay extends javax.swing.JFrame {
    public final JButton[][] squares = new JButton[8][8];
    Piece piece = new Piece();
    public GamePlay() {
        setTitle("Chess Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel chessBoard = new JPanel(new GridLayout(8,8));
        chessBoard.setPreferredSize(new Dimension(500,500));
        boolean white = true;
        for (int row = 0; row < 8; row++) {
            white = !white; // alternate colors each row
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
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
       piece.InitChessPlay(this);
    }
    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(() -> new GamePlay().setVisible(true));
    }
}
