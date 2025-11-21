
package Make_A_Move;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;
import jvtest.MenuGame;

public class LogInForm extends JFrame {
    
    DAOLogin db = new DAOLogin();
    public MenuGame menu = new MenuGame();
    
    public LogInForm(){
        setTitle("Đăng nhập");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(new Color(245, 245, 245));
        box.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 255), 2));
        box.setBounds(20, 20, 450, 270);
        add(box);

        JLabel title = new JLabel("Đăng nhập");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(40, 70, 130));
        title.setBounds(160, 20, 200, 40);
        box.add(title);

        JLabel lblUser = new JLabel("UserName:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setBounds(80, 80, 80, 25);
        box.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(170, 80, 200, 28);
        box.add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setBounds(80, 130, 80, 25);
        box.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(170, 130, 200, 28);
        box.add(txtPass);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(160, 200, 90, 35);
        btnBack.setBackground(new Color(220, 220, 220));
        btnBack.setFocusPainted(false);
        box.add(btnBack);

        JButton btnOK = new JButton("OK");
        btnOK.setBounds(270, 200, 90, 35);
        btnOK.setBackground(new Color(43, 93, 135));
        btnOK.setForeground(Color.WHITE);
        btnOK.setFocusPainted(false);
        box.add(btnOK);
        
        btnBack.addActionListener(e -> {
            SignUpForm su = new SignUpForm();
            su.setVisible(true);
            dispose();
            
        });  
        
        btnOK.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = txtPass.getText().trim();
            if(user.isEmpty() || pass.isEmpty()){
                System.err.println("Vui lòng nhập đủ thông tin");
                return;
            }
            
            int res = db.loginUser(user, pass);
            if(res > 0){
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                dispose();
                menu.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "Tài khoản không đúng!");
            }
        });
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new LogInForm().setVisible(true));
    }
    
}
