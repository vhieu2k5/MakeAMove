package jvtest;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JFrame {
    private GameTimer timerChess;
    private GamePlay gamePlay;
    private JLabel whiteTimerLabel, blackTimerLabel;
    private int minutes;

    public PausePanel(GameTimer timerChess, GamePlay gamePlay, int minutes) {
        this.timerChess = timerChess;
        this.minutes = minutes;
        this.gamePlay = gamePlay;

        setTitle("Pause Game");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        ImageIcon bgIcon = new ImageIcon("../MakeAMove/pics/pausePanel.jpg");
        JLabel pauseGame = new JLabel(bgIcon);
        pauseGame.setBounds(0, 0, 400, 400);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(145, 100,  100, 35);
        continueButton.setFont(new Font("Arial", Font.BOLD, 12));

        JButton replayButton = new JButton("Replay");
        replayButton.setBounds(145, 150, 100, 35);
        replayButton.setFont(new Font("Arial", Font.BOLD, 12));

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(145, 200, 100, 35);
        homeButton.setFont(new Font("Arial", Font.BOLD, 12));

        add(continueButton);
        add(replayButton);
        add(homeButton);
        add(pauseGame);

        continueButton.addActionListener(e -> {
            if (timerChess != null){

                timerChess.start(); 
            }
            dispose();
        });

        replayButton.addActionListener(e -> {
            if (timerChess != null) {
                timerChess.stop();
            }
            if (gamePlay != null) {
                gamePlay.dispose();
            }

            new GamePlay(minutes).setVisible(true);
            dispose();
        });

        homeButton.addActionListener(e -> {
            if (timerChess != null){

                timerChess.stop(); 
            }
            gamePlay.dispose(); 
            dispose(); 
            new MenuGame().setVisible(true);
        });
    }
}
