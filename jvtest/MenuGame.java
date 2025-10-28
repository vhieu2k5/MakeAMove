package jvtest;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuGame extends JFrame {

    private JButton playButton, play2Button, instructionButton;
    private JLabel titleLabel, titleLabel2, bgLabel;

    public MenuGame() {
        setTitle("Chess Game");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bgLabel = new JLabel(new ImageIcon("../MakeAMove/pics/co_vua.jpg"));
        bgLabel.setBounds(0, 0, 600, 700);
        bgLabel.setLayout(null); 
        add(bgLabel);

        titleLabel = new JLabel("Chess Game");
        titleLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 48));
        titleLabel.setForeground(new Color(217, 170, 110));
        titleLabel.setBounds(120, 80, 400, 60);
        bgLabel.add(titleLabel);
        
        titleLabel2 = new JLabel("Chess Game");
        titleLabel2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 48));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setBounds(118, 82, 400, 60);
        bgLabel.add(titleLabel2);


        playButton = new JButton("PLAY");
        playButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        playButton.setBackground(new Color(217, 170, 110));
        playButton.setForeground(Color.WHITE);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.setBorder(BorderFactory.createBevelBorder(1));
        playButton.setBounds(235, 200, 130, 45);
        bgLabel.add(playButton);

        play2Button = new JButton("Mode");
        play2Button.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 14));
        play2Button.setBackground(new Color(217, 170, 110));
        play2Button.setForeground(Color.WHITE);
        play2Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        play2Button.setBorder(BorderFactory.createBevelBorder(1));
        play2Button.setBounds(235, 260, 130, 45);
        bgLabel.add(play2Button);


        instructionButton = new JButton("Instruction");
        instructionButton.setFont(new Font("Snap ITC", Font.BOLD, 12));
        instructionButton.setBackground(new Color(217, 170, 110));
        instructionButton.setForeground(Color.WHITE);
        instructionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instructionButton.setBorder(BorderFactory.createBevelBorder(1));
        instructionButton.setBounds(249, 320, 100, 40);
        bgLabel.add(instructionButton);

        playButton.addActionListener((ActionEvent e) -> {
            String[] options = {"None", "3 phút", "10 phút", "20 phút", "30 phút"};
            String selected = (String) JOptionPane.showInputDialog(
                    MenuGame.this,
                    "⏳ Chọn thời gian cho mỗi bên:",
                    "Chọn thời gian chơi",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (selected != null) {
                int minutes = 5;

                switch (selected) {
                    
                    case "None": minutes = 0; break;
                    case "3 phút": minutes = 3; break;
                    case "10 phút": minutes = 10; break;
                    case "20 phút": minutes = 20; break;
                    case "30 phút": minutes = 30; break;
                }
                if(minutes < 0){
                    new GamePlay(0).setVisible(true);
                }
                else{
                    new GamePlay(minutes).setVisible(true);
                    dispose(); 
                }
               
            }
        });

        play2Button.addActionListener((ActionEvent e) -> {
            String[] modeGame = {"Chơi với máy", "Chơi Cờ úp"};
            String optionMode = (String) JOptionPane.showInputDialog(
                    MenuGame.this,
                    "⏳ Chọn chế độ chơi: ",
                    "Mode Game",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    modeGame,
                    modeGame[0]
            );

            if(optionMode != null){
                int check = 0;
                switch (optionMode) {
                    case "Chơi với máy": check=1;break;
                
                    case "Chơi Cờ úp": check=2;break;
                }

                if(check == 2){
                    new GamePlay2().setVisible(true);
                }
                if(check == 1){
                    
                }
            }
        });


        instructionButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(
                MenuGame.this,
                "♟️ Hướng dẫn chơi Chess Game:\n\n"
                + "- Quân trắng và quân đen thay phiên nhau di chuyển.\n"
                + "- Không được đi khiến vua mình bị chiếu.\n"
                + "- Ăn hết vua đối phương là thắng.\n\n"
                + "👉 Nhấn 'Replay' để chơi lại ván mới.",
                "Instruction",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

//        setResizable(false);
//        setVisible(true); 
    }

}