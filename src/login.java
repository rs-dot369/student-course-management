import com.mysql.cj.jdbc.SuspendableXAConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login implements ActionListener {
    JFrame adminFrame;
    JDialog loginDialog;
    JTextField usernameField;
    JTextField passwordField;
    JButton okButton;
    login(){

        adminFrame = new JFrame();
        adminFrame.setTitle("Shrivastava Computer Education centre | Admin");
        adminFrame.setSize(1920,1080);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setVisible(true);

        loginDialog = new JDialog((Frame) null,"Login",true);
        loginDialog.setSize(300,200);
        loginDialog.setLocationRelativeTo(adminFrame);
        loginDialog.setUndecorated(true);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(250,40));
        usernameField.setFont(new Font("Consolas",Font.PLAIN,15));
        usernameField.setText("Username");

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250,40));
        passwordField.setFont(new Font("Consolas",Font.PLAIN,15));
        passwordField.setText("Password");

        okButton = new JButton();
        okButton.setBounds(300,100,100,100);
        okButton.setFocusable(false);
        okButton.setText("Login");
        okButton.setFont(new Font("Comic Sans",Font.BOLD,15));
        okButton.addActionListener(this);

        loginDialog.add(usernameField);
        loginDialog.add(passwordField);
        loginDialog.add(okButton);
        loginDialog.setLayout(new FlowLayout());
        loginDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==okButton){
            //check credentials
            String username = "admin";
            String passwd = "admin";
            if(username.equals(usernameField.getText()) && passwd.equals(passwordField.getText())){
                //System.out.println("Login success");
                loginDialog.dispose();
                adminFrame.dispose();
                new newAdminFrame();
            }
            else{
                loginDialog.dispose();
                adminFrame.dispose();
            }
        }
    }
}
