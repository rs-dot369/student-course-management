import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class courseManager implements ActionListener {
    private static final String url = "jdbc:mysql://localhost:3308/scec_database";
    private static final String username = "root";
    private static final String password = "toor";

    // addCourse
    JTextField addCourse_courseNameField;
    JLabel addCourse_checkIfExist;
    JButton addCourseButton;
    JTextField addCourse_durationField;
    JTextField addCourse_feeField;
    JTextField feeAmountField;

    // updateFee
    JTextField updateFee_courseNameField;
    JTextField newFeeField;
    JButton updateFeeButton;
    JLabel updateFee_checkIfExist;

    // deleteCourse
    JTextField deleteCourse_courseNameField;
    JButton deleteCourseButton;
    JLabel deleteCourse_checkIfExist;

    public void addCourse(Frame owner){
        JDialog addCourseDialog = new JDialog(owner,"Add Course",true);
        addCourseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addCourseDialog.setResizable(false);
        addCourseDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel courseNameLabel = new JLabel("Course");
        courseNameLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=0;
        addCourseDialog.add(courseNameLabel,gbc);

        addCourse_courseNameField = new JTextField(20);
        addCourse_courseNameField.setText("Course name");
        addCourse_courseNameField.selectAll();
        addCourse_courseNameField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        addCourseDialog.add(addCourse_courseNameField,gbc);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=2;
        addCourseDialog.add(durationLabel,gbc);

        addCourse_durationField = new JTextField(20);
        addCourse_durationField.setText("Course Duration");
        addCourse_durationField.selectAll();
        addCourse_durationField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=2;
        addCourseDialog.add(addCourse_durationField,gbc);

        JLabel feeLabel = new JLabel("Fee");
        feeLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=4;
        addCourseDialog.add(feeLabel,gbc);

        addCourse_feeField = new JTextField(20);
        addCourse_feeField.setText("Course Fee Structure");
        addCourse_feeField.selectAll();
        addCourse_feeField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=4;
        addCourseDialog.add(addCourse_feeField,gbc);

        addCourse_checkIfExist = new JLabel();
        addCourse_checkIfExist.setText(":-)");
        addCourse_checkIfExist.setBackground(new Color(0,0,0,0));
        addCourse_checkIfExist.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,18));
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=3;
        addCourseDialog.add(addCourse_checkIfExist,gbc);

        addCourseButton = new JButton("ADD");
        addCourseButton.setFocusable(false);
        addCourseButton.setFont(new Font("Arial",Font.BOLD,15));
        addCourseButton.addActionListener(this);    //SETTING LISTENER SO BUTTON WORKS
        addCourseButton.setPreferredSize(new Dimension(470,30));
        gbc.gridx=0;
        gbc.gridy=8;
        addCourseDialog.add(addCourseButton,gbc);

        addCourseDialog.pack();
        addCourseDialog.setLocationRelativeTo(owner);
        addCourseDialog.setVisible(true);

    }
    public void updateFee(Frame owner){
        JDialog updateFeeDialog = new JDialog(owner,"Update Course",true);
        updateFeeDialog.setResizable(false);
        updateFeeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        updateFeeDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //
        JLabel courseNameLabel = new JLabel("Course Name");
        courseNameLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        updateFeeDialog.add(courseNameLabel,gbc);

        updateFee_courseNameField = new JTextField(20);
        updateFee_courseNameField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        updateFeeDialog.add(updateFee_courseNameField,gbc);
        //
        JLabel newFeeLabel = new JLabel("New Fee");
        newFeeLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        updateFeeDialog.add(newFeeLabel,gbc);

        newFeeField = new JTextField(20);
        newFeeField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=2;
        updateFeeDialog.add(newFeeField,gbc);


        updateFee_checkIfExist = new JLabel();
        updateFee_checkIfExist.setText(":-)");
        updateFee_checkIfExist.setBackground(new Color(0,0,0,0));
        updateFee_checkIfExist.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,18));
        //checkIfExist.setBorder(BorderFactory.createEmptyBorder());
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=3;
        updateFeeDialog.add(updateFee_checkIfExist,gbc);

        updateFeeButton = new JButton("UPDATE FEE");
        updateFeeButton.addActionListener(this);
        updateFeeButton.setFocusable(false);
        updateFeeButton.setFont(new Font("Arial",Font.BOLD,15));
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=3;
        gbc.gridheight=2;
        updateFeeDialog.add(updateFeeButton,gbc);

        updateFeeDialog.pack();
        updateFeeDialog.setLocationRelativeTo(owner);
        updateFeeDialog.setVisible(true);
    }
    public void deleteCourse(Frame owner){
        JDialog deleteCourseDialog = new JDialog(owner,"Delete Course",true);
        deleteCourseDialog.setResizable(false);
        deleteCourseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        deleteCourseDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel courseNameLabel = new JLabel();
        courseNameLabel.setText("Course Name");
        courseNameLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        deleteCourseDialog.add(courseNameLabel,gbc);

        deleteCourse_courseNameField = new JTextField(20);
        deleteCourse_courseNameField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        deleteCourseDialog.add(deleteCourse_courseNameField,gbc);

        deleteCourse_checkIfExist = new JLabel();
        deleteCourse_checkIfExist.setText(":-)");
        deleteCourse_checkIfExist.setBackground(new Color(0,0,0,0));
        deleteCourse_checkIfExist.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,18));
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=3;
        deleteCourseDialog.add(deleteCourse_checkIfExist,gbc);

        deleteCourseButton = new JButton("DELETE COURSE");
        deleteCourseButton.setFocusable(false);
        deleteCourseButton.setFont(new Font("Arial",Font.BOLD,15));
        deleteCourseButton.addActionListener(this);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=3;
        deleteCourseDialog.add(deleteCourseButton,gbc);

        deleteCourseDialog.pack();
        deleteCourseDialog.setLocationRelativeTo(owner);
        deleteCourseDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addCourseButton){
            String courseName = addCourse_courseNameField.getText().trim();
            if(courseName.equalsIgnoreCase("Course name") || addCourse_durationField.getText().equalsIgnoreCase("Course Duration") || addCourse_feeField.getText().equalsIgnoreCase("Course Fee") || courseName.isEmpty()){
                addCourse_checkIfExist.setText("You must add legitimate course.");
                return;
            }
            try (Connection con = DriverManager.getConnection(url, username, password)) {
                // Check for existence
                String checkQuery = "SELECT 1 FROM courses WHERE course = ?";
                try (PreparedStatement checkPstmt = con.prepareStatement(checkQuery)) {
                    checkPstmt.setString(1, courseName);
                    try (ResultSet rs = checkPstmt.executeQuery()) {
                        if (rs.next()) {
                            addCourse_checkIfExist.setText("This course is already in the system.");
                            return; // Stop here
                        }
                    }
                }

                // Perform Insert
                String insertQuery = "INSERT INTO courses (course, duration, fee) VALUES (?, ?, ?)";
                try (PreparedStatement insertPstmt = con.prepareStatement(insertQuery)) {
                    insertPstmt.setString(1, courseName);
                    insertPstmt.setString(2, addCourse_durationField.getText().trim());
                    insertPstmt.setString(3, addCourse_feeField.getText().trim());
                    insertPstmt.executeUpdate();

                    // Clearing all field
                    addCourse_courseNameField.setText("");
                    addCourse_durationField.setText("");
                    addCourse_feeField.setText("");
                    addCourse_checkIfExist.setText("Course added successfully!");
                }

            }
            catch(SQLException ee) {
                // LOG the error for the developer to see in the console/file
                Logger.getLogger(courseWindow.class.getName()).log(Level.SEVERE, "Database connection error", ee);

                // SHOW a generic message to the user
                addCourse_checkIfExist.setText("Unable to save data. Please try again later.");
            }
        }
        // updateFeeButton
        if(e.getSource()==updateFeeButton){
            //System.out.println("Update Fee Button is clicked. Course Name: "+courseNameField.getText()+" New Fee: "+newFeeField.getText());
            String courseName = updateFee_courseNameField.getText().trim();
            if(courseName.isEmpty()){
                updateFee_checkIfExist.setText("I wonder why course name is empty.");
                return;
            }
            try (Connection con = DriverManager.getConnection(url, username, password)) {
                // Check for existence
                String checkQuery = "SELECT 1 FROM courses WHERE course = ?";
                try (PreparedStatement checkPstmt = con.prepareStatement(checkQuery)) {
                    checkPstmt.setString(1, courseName);
                    try (ResultSet rs = checkPstmt.executeQuery()) {
                        if (!(rs.next())) {
                            updateFee_checkIfExist.setText("This course name don't exist.");
                            return; // Stop here
                        }
                    }
                }

                // Perform Insert
                String insertQuery = "UPDATE courses SET fee=? WHERE course=?";
                try (PreparedStatement insertPstmt = con.prepareStatement(insertQuery)) {
                    insertPstmt.setString(1, newFeeField.getText().trim());
                    insertPstmt.setString(2, courseName);
                    insertPstmt.executeUpdate();
                    //System.out.println("im here");
                    // Clearing all field
                    updateFee_courseNameField.setText("");
                    newFeeField.setText("");
                    updateFee_checkIfExist.setText("Fee Updated successfully!");
                    //System.out.println("im here2");
                }
            }
            catch(SQLException ee) {
                // LOG the error for the developer to see in the console/file
                Logger.getLogger(courseWindow.class.getName()).log(Level.SEVERE, "Database connection error", ee);

                // SHOW a generic message to the user
                updateFee_checkIfExist.setText("Unable to save data. Please try again later.");
            }
        }
        // deleteCourse
        if(e.getSource()==deleteCourseButton){
            //delete course here
            String courseName = deleteCourse_courseNameField.getText().trim();
            if(courseName.isEmpty()){
                deleteCourse_checkIfExist.setText("I wonder why course name is empty!!!");
                return;
            }
            try (Connection con = DriverManager.getConnection(url, username, password)) {
                // Check for existence
                String checkQuery = "SELECT 1 FROM courses WHERE course = ?";
                try (PreparedStatement checkPstmt = con.prepareStatement(checkQuery)) {
                    checkPstmt.setString(1, courseName);
                    try (ResultSet rs = checkPstmt.executeQuery()) {
                        if (!(rs.next())) {
                            deleteCourse_checkIfExist.setText("This course name don't exist.");
                            return; // Stop here
                        }
                    }
                }

                // Perform Delete
                String insertQuery = "DELETE FROM courses WHERE course=?";
                try (PreparedStatement insertPstmt = con.prepareStatement(insertQuery)) {
                    insertPstmt.setString(1, courseName);
                    insertPstmt.executeUpdate();

                    // Clearing all field
                    deleteCourse_courseNameField.setText("");
                    deleteCourse_checkIfExist.setText("Course deleted successfully!");
                    //System.out.println("im here2");
                }
            }
            catch(SQLException ee) {
                // LOG the error for the developer to see in the console/file
                Logger.getLogger(courseWindow.class.getName()).log(Level.SEVERE, "Database connection error", ee);

                // SHOW a generic message to the user
                deleteCourse_checkIfExist.setText("Unable to save data. Please try again later.");
            }
        }
    }
}
