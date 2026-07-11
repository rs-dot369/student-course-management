import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow implements ActionListener {
    JFrame frame;
    JButton courses;
    JButton admin;
    mainWindow() {
        frame = new JFrame();
        frame.setTitle("Shrivastava Computer Education Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setVisible(true);

        //Adding buttons
        courses = new JButton();
        courses.setText("Courses");
        courses.setBounds(200,160,100,35);
        courses.setFocusable(false);
        courses.setFont(new Font("Comic Sans",Font.BOLD | Font.ITALIC,17));
        courses.setBorder(BorderFactory.createEtchedBorder());
        //courses.setIconTextGap(5);
        courses.addActionListener(this);

        admin = new JButton();
        admin.setText("Admin");
        admin.setBounds(200,220,100,35);
        admin.setFocusable(false);
        admin.setFont(new Font("Comic Sans",Font.BOLD | Font.ITALIC,17));
        admin.setBorder(BorderFactory.createEtchedBorder());
        admin.addActionListener(this);

        //adding buttons to the frame
        frame.setLayout(new FlowLayout());
        frame.add(courses);
        frame.add(admin);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==courses){
            //System.out.println("Courses button is clicked");
            new courseWindow();
        }
        if(e.getSource()==admin){
            //System.out.println("Admin button is clicked");
            new login();
            //frame.dispose();
        }
    }
}
