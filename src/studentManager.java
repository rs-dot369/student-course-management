import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public  class studentManager implements ActionListener{
    private static final String url = "jdbc:mysql://localhost:3308/scec_database";
    private static final String username = "root";
    private static final String password = "toor";

    public void addStudent(Frame owner){
        JDialog addStudentDialog= new JDialog(owner,"Add Student",true);
        addStudentDialog.setResizable(false);
        addStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addStudentDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // name
        JLabel addStudentName = new JLabel("Name");
        addStudentName.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=0;
        addStudentDialog.add(addStudentName,gbc);

        JTextField addStudentNameField = new JTextField(20);
        addStudentNameField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=0;
        addStudentDialog.add(addStudentNameField,gbc);

        // father's/guardian name
        JLabel addStudentFatherName = new JLabel("Father's/Guardian Name");
        addStudentFatherName.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=2;
        addStudentDialog.add(addStudentFatherName,gbc);

        JTextField addStudentFatherNameField = new JTextField(20);
        addStudentFatherNameField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=2;
        addStudentDialog.add(addStudentFatherNameField,gbc);

        // date of brith
        JLabel addStudentDOB = new JLabel("Date Of Birth");
        addStudentDOB.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=4;
        addStudentDialog.add(addStudentDOB,gbc);

        DatePicker datePicker = new DatePicker();
        DatePickerSettings settings = datePicker.getSettings();
        settings.setAllowKeyboardEditing(true);
        settings.setDateRangeLimits(LocalDate.of(1920, 1, 1), LocalDate.now());
        gbc.gridx=1;
        gbc.gridy=4;
        addStudentDialog.add(datePicker,gbc);
        datePicker.setDate(LocalDate.of(2000, 1, 1));   // Start the calendar at the year 2000 for convenience
        //System.out.println(datePicker.getDate());

        // sex
        JLabel addStudentSex = new JLabel("Sex");
        addStudentSex.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=6;
        addStudentDialog.add(addStudentSex,gbc);

        // Radio Buttons
        JRadioButton maleBtn = new JRadioButton("Male");
        JRadioButton femaleBtn = new JRadioButton("Female");
        JRadioButton otherBtn = new JRadioButton("Other");

        // This makes them mutually exclusive
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        genderGroup.add(otherBtn);

        // a Panel to hold buttons side-by-side
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radioPanel.add(maleBtn);
        radioPanel.add(femaleBtn);
        radioPanel.add(otherBtn);
        //if (maleBtn.isSelected()) gender = "Male";
        // Add the PANEL to the dialog
        gbc.gridx = 1;
        gbc.gridy = 6;
        addStudentDialog.add(radioPanel, gbc);

        // CourseofInterest
        JLabel addStudentCourseOfInterest = new JLabel("Course Of Interest");
        addStudentCourseOfInterest.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=8;
        addStudentDialog.add(addStudentCourseOfInterest,gbc);

        JTextField addStudentCourseOfInterestField = new JTextField(20);
        addStudentCourseOfInterestField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=8;
        addStudentDialog.add(addStudentCourseOfInterestField,gbc);

        //  aadhar No.
        JLabel addStudentAadhaar = new JLabel("Aadhaar Number");
        addStudentAadhaar.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=10;
        addStudentDialog.add(addStudentAadhaar,gbc);

        JTextField addStudentAadhaarField = new JTextField(20);
        addStudentAadhaarField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=10;
        addStudentDialog.add(addStudentAadhaarField,gbc);

        // education qualification
        JLabel addStudentQualification = new JLabel("Education Qualification");
        addStudentQualification.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=12;
        addStudentDialog.add(addStudentQualification,gbc);

        JTextField addStudentQualificationField = new JTextField(20);
        addStudentQualificationField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=12;
        addStudentDialog.add(addStudentQualificationField,gbc);

        // address
        JLabel addStudentAddress = new JLabel("Address");
        addStudentAddress.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=14;
        addStudentDialog.add(addStudentAddress,gbc);

        JTextField addStudentAddressField = new JTextField(20);
        addStudentAddressField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=14;
        addStudentDialog.add(addStudentAddressField,gbc);

        // contact no
        JLabel addStudentContact = new JLabel("Contact Number");
        addStudentContact.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=16;
        addStudentDialog.add(addStudentContact,gbc);

        JTextField addStudentContactField = new JTextField(20);
        addStudentContactField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=16;
        addStudentDialog.add(addStudentContactField,gbc);

        JLabel extraNoteLabel = new JLabel("Add Note");
        extraNoteLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=0;
        gbc.gridy=18;
        addStudentDialog.add(extraNoteLabel,gbc);

        JTextField extraNoteField = new JTextField(20);
        extraNoteField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
        gbc.gridx=1;
        gbc.gridy=18;
        addStudentDialog.add(extraNoteField,gbc);

        JButton addStudentButton = new JButton("ADD STUDENT");
        addStudentButton.setFocusable(false);
        addStudentButton.setFont(new Font("Arial",Font.BOLD,15));
        addStudentButton.addActionListener(this);
        gbc.gridx=0;
        gbc.gridy=20;
        gbc.gridwidth=3;
        addStudentDialog.add(addStudentButton,gbc);

        addStudentButton.addActionListener((ActionEvent e) -> {
            String name = addStudentNameField.getText();
            String father = addStudentFatherNameField.getText();
            LocalDate dob = datePicker.getDate();
            String gender= maleBtn.isSelected() ? "Male" : femaleBtn.isSelected() ? "Female": "other";
            String course_of_interest = addStudentCourseOfInterestField.getText();
            String aadhaar = addStudentAadhaarField.getText();
            String education = addStudentQualificationField.getText();
            String address = addStudentAddressField.getText();
            String contact = addStudentContactField.getText();
            String extraNote = extraNoteField.getText();

            StringBuilder errorMessage = new StringBuilder();
            {
                if (name.isEmpty()) {
                    errorMessage.append("- Name cannot be empty.\n");
                } else if (!name.matches("^[a-zA-Z\\s]+$")) {
                    errorMessage.append("- Name should only contain letters.\n");
                }

                if (!contact.matches("^[0-9]{10}$")) {
                    errorMessage.append("- Contact must be exactly 10 digits.\n");
                }

                if (!aadhaar.isEmpty() && !aadhaar.matches("^[0-9]{12}$")) {
                    errorMessage.append("- Aadhaar must be 12 digits (if provided).\n");
                }

                if (course_of_interest.isEmpty()) {
                    errorMessage.append("- Course of Interest is required.\n");
                }
            }
            // Show Errors or Proceed
            if (errorMessage.length() > 0) {
                JOptionPane.showMessageDialog(addStudentDialog, errorMessage.toString(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String query = "INSERT INTO students (full_name, father_name, dob, sex, course_of_interest, aadhaar_no, address, highest_education, contact, extra_note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (Connection con = DriverManager.getConnection(url, username, password);
                     PreparedStatement pstmt = con.prepareStatement(query)) {

                    pstmt.setString(1, name);
                    pstmt.setString(2, father);
                    // Convert LocalDate to String or SQL Date
                    pstmt.setString(3, (dob != null) ? dob.toString() : null);
                    pstmt.setString(4, gender);
                    pstmt.setString(5, course_of_interest);
                    pstmt.setString(6, aadhaar);
                    pstmt.setString(7, address);
                    pstmt.setString(8, education);
                    pstmt.setString(9, contact);
                    pstmt.setString(10, extraNote);

                    // This must be INSIDE the try block where pstmt is defined
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(addStudentDialog, "Student Registered Successfully!");
                    addStudentDialog.dispose();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addStudentDialog, "Database Error: " + ex.getMessage());
                }
            }
        });

        addStudentDialog.pack();
        addStudentDialog.setLocationRelativeTo(owner);
        addStudentDialog.setVisible(true);
    }

    public void updateStudent(Frame owner, String name, String phone, String aadhaar1, JTable studentTable) {
        //
        JDialog updateDialog = new JDialog(owner, "Update Student", true);
        updateDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create the Form Fields
        JLabel nameField = new JLabel(name);
        JLabel phoneField = new JLabel(phone);
        JLabel aadhaarField = new JLabel(aadhaar1);
        JTextField paidAmountField = new JTextField(20);
        JTextField extraNoteField = new JTextField(20);
        JButton saveBtn = new JButton("Save Changes");

        // Add Components to Dialog
        gbc.gridx = 0; gbc.gridy = 0; updateDialog.add(new JLabel("Name"), gbc);
        gbc.gridx = 1; updateDialog.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; updateDialog.add(new JLabel("Aadhaar"), gbc);
        gbc.gridx = 1; updateDialog.add(aadhaarField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; updateDialog.add(new JLabel("Phone"), gbc);
        gbc.gridx = 1; updateDialog.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; updateDialog.add(new JLabel("Paid Amount"), gbc);
        gbc.gridx = 1; updateDialog.add(paidAmountField, gbc);

        gbc.gridx=0; gbc.gridy=4; updateDialog.add(new JLabel("Add Note"),gbc);
        gbc.gridx=1; updateDialog.add(extraNoteField,gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        updateDialog.add(saveBtn, gbc);

        // Logic for the "Save Changes" button inside the dialog
        saveBtn.addActionListener(saveEvt -> {
            //System.out.println("save button is clicked");
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) return;

            int modelRow = studentTable.convertRowIndexToModel(selectedRow);
            String inputText = paidAmountField.getText().trim();
            String  extraNote = extraNoteField.getText().trim()+", ";

            // Validation
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(updateDialog, "Please enter an amount.");
                return;
            }
            try {
                double addedPayment = Double.parseDouble(inputText);
                String aadhaar = studentTable.getModel().getValueAt(modelRow, 3).toString();

                // Database Update
                try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    String sql = "UPDATE students SET paid = COALESCE(paid, 0) + ?, extra_note = CONCAT(COALESCE(extra_note,''),?)  WHERE TRIM(aadhaar_no) = TRIM(?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setDouble(1, addedPayment);
                    pstmt.setString(2,extraNote);
                    pstmt.setString(3, aadhaar);

                    int rowsAffected= pstmt.executeUpdate();
                    //System.out.println("Rows affected: " + rowsAffected);

                    if (rowsAffected > 0) {
                        // Update UI Table Model only if Database update succeeded
                        // Fetch the current value from the table and add the new payment to it
                        Object value = studentTable.getModel().getValueAt(modelRow, 6);
                        double currentPaidInTable = (value != null) ? Double.parseDouble(value.toString()) : 0.0;
                        studentTable.getModel().setValueAt(currentPaidInTable + addedPayment, modelRow, 6);
                        studentTable.getModel().setValueAt(extraNote,modelRow,8);

                        JOptionPane.showMessageDialog(updateDialog, "Payment Updated Successfully!");
                        updateDialog.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(updateDialog, "Student not found in database.");
                    }
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(updateDialog, "Invalid amount. Please enter numbers only.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateDialog, "Database Error: " + ex.getMessage());
            }
        });
        updateDialog.pack();
        updateDialog.setLocationRelativeTo(owner);
        updateDialog.setVisible(true);
    }

    public void searchStudent(Frame owner, JTable studentTable) {
        JDialog searchStudentDialog = new JDialog(owner, "Search Student", false); // 'false' allows clicking back and forth
        searchStudentDialog.setSize(300, 100);
        searchStudentDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField searchField = new JTextField(20);
        searchField.setText("Search by Name, Phone, or Aadhaar...");
        searchField.selectAll();
        gbc.gridx=0;
        searchStudentDialog.add(searchField,gbc);

        // Get the sorter from the table
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) studentTable.getRowSorter();

        // Adding DocumentListener for real-time filtering
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();
                if (sorter == null) return;

                if (text.trim().isEmpty() || text.equals("Search by Name, Phone, or Aadhaar...")) {
                    sorter.setRowFilter(null);
                } else {
                    // Pattern.quote treats input as literal text, not regex commands
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text.trim()), 0, 2, 3));
                }
            }
        });

        searchStudentDialog.setLocationRelativeTo(owner);
        searchStudentDialog.setVisible(true);
    }

    public void deleteStudent(Frame owner, String name, String phone, String aadhaar, JTable studentTable){
        //JOptionPane.showConfirmDialog(owner,"- Name: "+name+"\n- Phone: "+phone+"\n- Aadhaar: "+aadhaar);
        int response = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete\n- Name: " + name + "\n- Phone: " + phone + "\n- Aadhaar No.: " + aadhaar + " ?\nThis action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        //System.out.println(response);
        if(response == JOptionPane.YES_OPTION){
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "DELETE FROM students WHERE TRIM(aadhaar_no) = TRIM(?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, aadhaar);

                int rowsDeleted = pstmt.executeUpdate();

                if (rowsDeleted > 0) {
                    // Remove from the UI Table
                    //((DefaultTableModel) studentTable.getModel()).removeRow(modelRow);
                    JOptionPane.showMessageDialog(null, "Student record deleted successfully.");
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage());
            }
        }
        else {
        JOptionPane.showMessageDialog(null, "Please select a student to delete.");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
