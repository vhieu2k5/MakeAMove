
import java.awt.*;
import javax.swing.*;

public class GamePlay extends javax.swing.JFrame {

    public final JButton[][] squares = new JButton[8][8];
    Piece piece = new Piece();
    int click = 0;
    int fromCol = -1;
    int fromRow = -1;

    public GamePlay() {
        setTitle("Chess Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel chessBoard = new JPanel(new GridLayout(8, 8));
        chessBoard.setPreferredSize(new Dimension(500, 500));
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
                // Add OnClick Listener
                final int r = row;
                final int c = col;
squares[row][col].addActionListener(e -> {
  //  if (piece.chessBoard[r][c] != null ) JOptionPane.showMessageDialog(this,piece.chessBoard[r][c].symbol+ ":"+r +" "+c);
    if (click == 0 && piece.chessBoard[r][c] != null) {
        fromCol = c;
        fromRow = r;
        click = 1;
        squares[r][c].setBackground(Color.YELLOW);
    } else if (click == 1) {
        int toRow = r;
        int toCol = c;
       // JOptionPane.showMessageDialog(this, "Move from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")");
        squares[fromRow][fromCol].setBackground(
            (fromRow + fromCol) % 2 == 0 
                ? new  Color(181, 136, 99)
                : new  Color(240, 217, 181)
        );
        piece.chessBoard[fromRow][fromCol].setMove(toRow, toCol, this);
        click = 0;
        fromRow = -1;
        fromCol = -1;
    }
});

            }

        }

        // ===================Control Panel================
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton replayBtn = new JButton("Replay");
        controlPanel.add(replayBtn, BorderLayout.CENTER);

        add(chessBoard, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        piece.InitChessPlay(this);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GamePlay().setVisible(true));
    }
}
