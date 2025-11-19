
package Make_A_Move;

import java.awt.*;
import static java.awt.Color.decode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SignUpForm extends JFrame {
    
    JLabel lblUser = new JLabel("UserName:");
    JLabel lblPass = new JLabel("Password:");

    JTextField txtUser = new JTextField();
    JTextField txtPass = new JTextField();
    ArrayList<JTextField> fields = new ArrayList<>();
    
    DAOLogin db = new DAOLogin();
    LogInForm lg = new LogInForm();

    public SignUpForm() {
        setTitle("Đăng kí");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setBackground(new Color(245, 245, 245));
        container.setBorder(new LineBorder(new Color(180, 180, 255), 3));
        container.setLayout(null);
        add(container);

        JLabel lblTitle = new JLabel("Lập tài khoản");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 26));
        lblTitle.setForeground(new Color(52, 105, 154));
        lblTitle.setBounds(155, 20, 200, 40);
        container.add(lblTitle);


        lblUser.setBounds(80, 90, 100, 25);
        txtUser.setBounds(180, 90, 220, 25);

        lblPass.setBounds(80, 130, 100, 25);
        txtPass.setBounds(180, 130, 220, 25);

        container.add(lblUser);
        container.add(txtUser);
        container.add(lblPass);
        container.add(txtPass);
        Collections.addAll(fields, txtUser, txtPass);

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(new Color(52, 105, 154));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBounds(350, 180, 80, 30);
        btnSave.setFocusPainted(false);
        
        JLabel a = new JLabel("Bạn đã có tài khoản?");
        a.setForeground(decode("#346999A"));
        a.setBounds(180, 200, 120, 35);
        a.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#34699A")));
        a.setCursor(new Cursor(Cursor.HAND_CURSOR));

        container.add(btnSave);
        container.add(a);
        
        a.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e){
                LogInForm lg = new LogInForm();
                lg.setVisible(true);
                dispose();
            }
        });
        
        btnSave.addActionListener(new BtnClicked());
    }
    
    public class BtnClicked implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String userName = txtUser.getText().trim();
            String passWord = txtPass.getText().trim();
            
            for(JTextField fi: fields){
                if(fi.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(SignUpForm.this,
                            "Vui lòng nhập đầy đủ thông tin", 
                            "Thiếu dữ liệu", 
                            JOptionPane.WARNING_MESSAGE);
                    
                    return;
                }
            }
            
            SignUpTxt extra = new SignUpTxt(userName, passWord);
            db.addInfo(extra);
            JOptionPane.showMessageDialog(SignUpForm.this,"Đã lưu thông tin vào CSDL");
            lg.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUpForm().setVisible(true));
    }
}

