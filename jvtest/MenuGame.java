package jvtest;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.List;
import make_a_move.DAOHistory;

public class MenuGame extends JFrame {

    private JButton playButton, play2Button, instructionButton, historyButton;
    private JLabel titleLabel, titleLabel2, bgLabel;
    public DAOHistory dh = new DAOHistory();
    public String userName;
    public static int level=1;
    public static String mode="bot";
    public static int timer=0;
    public MenuGame() {
        setTitle("Make A Move");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bgLabel = new JLabel(new ImageIcon("../MakeAMove/pics/co_vua.jpg"));
        bgLabel.setBounds(0, 0, 600, 700);
        bgLabel.setLayout(null); 
        add(bgLabel);

        titleLabel = new JLabel("Make A Move");
        titleLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 48));
        titleLabel.setForeground(new Color(217, 170, 110));
        titleLabel.setBounds(110, 80, 400, 60);
        bgLabel.add(titleLabel);
        
        titleLabel2 = new JLabel("Make A Move");
        titleLabel2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 48));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setBounds(108, 82, 400, 60);
        bgLabel.add(titleLabel2);


        playButton = new JButton("PLAY");
        playButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        playButton.setBackground(new Color(217, 170, 110));
        playButton.setForeground(Color.WHITE);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.setBorder(BorderFactory.createBevelBorder(1));
        playButton.setBounds(235, 190, 130, 45);
        bgLabel.add(playButton);

        play2Button = new JButton("MODE");
        play2Button.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 14));
        play2Button.setBackground(new Color(217, 170, 110));
        play2Button.setForeground(Color.WHITE);
        play2Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        play2Button.setBorder(BorderFactory.createBevelBorder(1));
        play2Button.setBounds(235, 245, 130, 45);
        bgLabel.add(play2Button);


        instructionButton = new JButton("Instruction");
        instructionButton.setFont(new Font("Snap ITC", Font.BOLD, 12));
        instructionButton.setBackground(new Color(217, 170, 110));
        instructionButton.setForeground(Color.WHITE);
        instructionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instructionButton.setBorder(BorderFactory.createBevelBorder(1));
        instructionButton.setBounds(249, 300, 100, 35);
        bgLabel.add(instructionButton);
        
        historyButton = new JButton("History");
        historyButton.setFont(new Font("Snap ITC", Font.BOLD, 12));
        historyButton.setBackground(new Color(217, 170, 110));
        historyButton.setForeground(Color.WHITE);
        historyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        historyButton.setBorder(BorderFactory.createBevelBorder(1));
        historyButton.setBounds(249, 345, 100, 30);
        bgLabel.add(historyButton);

        playButton.addActionListener((ActionEvent e) -> {
        
            if (mode.equals("bot")){
                String[] options = {"Level 1", "Level 2", "Level 3"};
            String selected = (String) JOptionPane.showInputDialog(
                    MenuGame.this,
                    "Chọn level chơi phù hợp",
                    "Chọn level",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
         if (selected != null) 
                switch (selected) {
                
                case "Level 1": level = 1; break;
                case "Level 2": level = 2; break;
                case "Level 3": level = 3; break;
               
            }
            if(mode.equals("bot")){
                java.awt.EventQueue.invokeLater(() ->new jvtest.GamePlay(0, mode, 1001, "TTK").setVisible(true));     //chơi với máy thì bỏ time đi nhé (fix lại theo code u)
                this.setVisible(false);
            }
            } else if (mode.equals("up")){
               java.awt.EventQueue.invokeLater(() -> new jvtest.gameplay02.GamePlay2().setVisible(true));
              this.setVisible(false);
            }
            else if (mode.equals("person")){
                java.awt.EventQueue.invokeLater(() -> new GamePlay(timer, mode, 1001, "TTK").setVisible(true));
                this.setVisible(false);
            }
        });
    

        play2Button.addActionListener((ActionEvent e) -> {
            String[] modeGame = {"Chơi với bot","Chơi với bạn", "Chơi Cờ úp"};
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
                    case "Chơi với máy" : check =0; break;
                    case "Chơi với bạn": check=1;break;
                    case "Chơi Cờ úp": check=2;break;
                }
                if (check==0){
                    mode = "bot";
                }
                if(check == 2){
                    mode="up";
                  java.awt.EventQueue.invokeLater(() -> new jvtest.gameplay02.GamePlay2().setVisible(true));
                    dispose();
                }
                if(check == 1){
                    level=0;
                    mode ="person";
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
                            new GamePlay(0, "person", 1001, "TTK").setVisible(true);
                        }
                        else{
                            new GamePlay(minutes, "person", 1001, "TTK").setVisible(true);
                            dispose(); 
                        }
                      timer = minutes;
                    }
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
        
        historyButton.addActionListener(e -> showHistoryPanel());
       
    }
    public void showHistoryPanel() {
        List<String> history = dh.getHistoryTable();
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 13));
        
        if(history.isEmpty()) textArea.setText("Chưa có ván đấu nào được lưu");
        else{
            StringBuilder sb = new StringBuilder("Lịch sử các vấn đấu:\n\n");
            for(String res: history){
                sb.append(res).append("\n");
            }
            textArea.setText(sb.toString());
        }
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 250));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, panel, "Lịch sử đấu", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuGame().setVisible(true);
        }); 
    }
}