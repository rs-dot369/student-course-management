import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Pattern;

public class newAdminFrame implements ActionListener {
    JFrame adminFrame;
    JPanel bottomPanel, topPanel;
    JButton studentButton, courseButton;
    CardLayout cardLayout;

    // Student Section Components
    JPanel studentOptionsPanel;
    JButton addStudentButton, updateStudentButton, deleteStudentButton, clearSearchButton;
    JTable studentTable;
    DefaultTableModel studentModel;

    // Course Section Components
    JPanel coursePanel;
    JButton addCourseButton, updateCourseButton, deleteCourseButton;

    private static final String url = "jdbc:mysql://localhost:3308/scec_database";
    private static final String username = "root";
    private static final String password = "toor";

    newAdminFrame() {
        // Initialize the Model FIRST
        studentModel = new DefaultTableModel(new String[]{"Name", "Father's Name", "Phone", "Aadhaar","Course","Total Fee","Paid","Due","Extra Note"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create the Table using that model
        studentTable = new JTable(studentModel);
        setupTableStyle(studentTable);
        JScrollPane tableScroll = new JScrollPane(studentTable);

        // Create the Sorter and attach it to the table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(studentModel);
        studentTable.setRowSorter(sorter);
        adminFrame = new JFrame();
        adminFrame.setTitle("Shrivastava Computer Education Centre | Admin");
        adminFrame.setSize(1200, 800); // Adjusted for better viewing
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(new BorderLayout());

        // --- TOP NAVIGATION PANEL ---
        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(1920, 45));

        studentButton = new JButton("Manage Students");
        courseButton = new JButton("Manage Courses");

        // Styling and Listeners
        for (JButton btn : new JButton[]{studentButton, courseButton}) {
            btn.setFocusable(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.addActionListener(this);
            topPanel.add(btn);
        }

        // --- BOTTOM CARD PANEL ---
        cardLayout = new CardLayout();
        bottomPanel = new JPanel(cardLayout);

        // STUDENT PANEL SETUP (The Dual Section)
        studentOptionsPanel = new JPanel(new BorderLayout()); // Main container

        // Top Section: Buttons
        JPanel studentButtonsSubPanel = new JPanel(new BorderLayout());
        studentButtonsSubPanel.setBackground(new Color(166, 165, 165));
        studentButtonsSubPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addStudentButton = new JButton("Add");
        addStudentButton.setBackground(new Color(40, 167, 69));
        addStudentButton.setForeground(Color.WHITE);

        updateStudentButton = new JButton("Update");
        updateStudentButton.setEnabled(false);
        updateStudentButton.setBackground(new Color(0, 102, 204));
        updateStudentButton.setForeground(Color.WHITE);

        deleteStudentButton = new JButton("Delete");
        deleteStudentButton.setEnabled(false);
        deleteStudentButton.setBackground(new Color(255, 0, 27));
        deleteStudentButton.setForeground(Color.WHITE);

        buttonPanel.add(addStudentButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(deleteStudentButton);
        ///////////////////////////////////
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(new JLabel(""));
        //searchPanel.setFont(new Font("Verdana", Font.BOLD, 14));

        clearSearchButton = new JButton("Clear");
        clearSearchButton.setBackground(new Color(41, 128, 185));
        clearSearchButton.setForeground(new Color(236, 240, 241));
        clearSearchButton.setFont(new Font("Verdana",Font.BOLD, 14));
        clearSearchButton.setFocusable(false);
        clearSearchButton.setOpaque(true);

        // search logic
        JTextField searchField = new JTextField(20);
        searchField.setText("Search by Name, Phone, or Aadhaar...");
        searchField.setFont(new Font("Verdana", Font.BOLD, 15));
        searchField.setForeground(Color.GRAY);
        //searchField.selectAll();

        // Get the sorter from the table
        //TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) studentTable.getRowSorter();
        // Adding DocumentListener for real-time filtering
         searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    filter();
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    filter();
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    filter();
                }

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

        ///////////////////////////////////////////
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search by Name, Phone, or Aadhaar...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Search by Name, Phone, or Aadhaar...");
                }
            }
        });
        searchPanel.add(clearSearchButton);
        searchPanel.add(searchField);

        studentButtonsSubPanel.add(buttonPanel, BorderLayout.WEST);
        studentButtonsSubPanel.add(searchPanel, BorderLayout.EAST);

        clearSearchButton.addActionListener(e -> {
            searchField.setText("Search by Name, Phone, or Aadhaar...");
            searchField.setForeground(Color.GRAY);
            sorter.setRowFilter(null); // This resets the table
            searchField.requestFocusInWindow(); // Puts cursor back for new search
        });

        JButton[] sButtons = {addStudentButton, updateStudentButton, deleteStudentButton};
        for (JButton b : sButtons) {
            b.addActionListener(this);
            b.setFocusable(false);
            b.setFont(new Font("Verdana",Font.BOLD, 14));
            b.setOpaque(true);
            //studentButtonsSubPanel.add(b);
        }

        //update button will enable when row is selected
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            // getValueIsAdjusting ensures this only runs once per click
            if (!e.getValueIsAdjusting()) {
                boolean rowSelected = studentTable.getSelectedRow() != -1;
                updateStudentButton.setEnabled(rowSelected);
                deleteStudentButton.setEnabled(rowSelected);
            }
        });
        // Adding to Student Options Panel
        studentOptionsPanel.add(studentButtonsSubPanel, BorderLayout.NORTH);
        studentOptionsPanel.add(tableScroll, BorderLayout.CENTER);
        buttonPanel.setOpaque(false);
        searchPanel.setOpaque(false);

        // 2. COURSE PANEL SETUP
        coursePanel = new JPanel(new FlowLayout());
        addCourseButton = new JButton("Add Course");
        updateCourseButton = new JButton("Update Course");
        deleteCourseButton = new JButton("Delete Course");

        JButton[] cButtons = {addCourseButton, updateCourseButton, deleteCourseButton};
        for (JButton b : cButtons) {
            b.addActionListener(this);
            coursePanel.add(b);
        }

        // Add both to CardLayout
        bottomPanel.add(studentOptionsPanel, "StudentPanel");
        bottomPanel.add(coursePanel, "CoursePanel");

        adminFrame.add(topPanel, BorderLayout.NORTH);
        adminFrame.add(bottomPanel, BorderLayout.CENTER);

        // Initial Data Load
        refreshStudentTable();

        adminFrame.setVisible(true);
        studentOptionsPanel.setFocusable(true);
        studentOptionsPanel.requestFocusInWindow();
    }

    private void setupTableStyle(JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.BOLD, 15));
    }

    public void refreshStudentTable() {
        studentModel.setRowCount(0); // Clear existing data
        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT s.full_name, s.father_name, s.contact, s.aadhaar_no, s.course_of_interest, c.fee, s.paid, s.extra_note FROM students AS s INNER JOIN courses AS c ON s.course_of_interest=c.course")) {
            while (rs.next()) {
                double totalFee = rs.getDouble("c.fee");
                double paid = rs.getDouble("s.paid");
                double due = totalFee - paid;
                studentModel.addRow(new Object[]{
                        rs.getString("full_name"),
                        rs.getString("father_name"),
                        rs.getString("contact"),
                        rs.getString("aadhaar_no"),
                        rs.getString("course_of_interest"),
                        rs.getString("fee"),
                        rs.getString("paid"),
                        due,
                        rs.getString("extra_note")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == studentButton) {
            //System.out.println("Student is selected");
            cardLayout.show(bottomPanel, "StudentPanel");
        }
        if (e.getSource()==addStudentButton){
            studentManager student = new studentManager();
            student.addStudent(adminFrame);
            refreshStudentTable();
        }
        /*
        if (e.getSource()==clearSearchButton){
            new studentManager().searchStudent(adminFrame, studentTable);
        }
        */


        if (e.getSource()==updateStudentButton){
            String name="";
            String phone="";
            String aadhaar="";
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = studentTable.convertRowIndexToModel(selectedRow);
                // Fetch data from table
                name = studentTable.getModel().getValueAt(modelRow, 0).toString();
                phone = studentTable.getModel().getValueAt(modelRow, 2).toString();
                aadhaar = studentTable.getModel().getValueAt(modelRow, 3).toString();
            }
            //System.out.println("name: "+name);
            new studentManager().updateStudent(adminFrame, name, phone, aadhaar, studentTable);
            refreshStudentTable();
        }

        if (e.getSource()==deleteStudentButton){
            String name="";
            String phone="";
            String aadhaar="";
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = studentTable.convertRowIndexToModel(selectedRow);
                // Fetch data from table
                name = studentTable.getModel().getValueAt(modelRow, 0).toString();
                phone = studentTable.getModel().getValueAt(modelRow, 2).toString();
                aadhaar = studentTable.getModel().getValueAt(modelRow, 3).toString();
            }
            //System.out.println("name: "+name);
            new studentManager().deleteStudent(adminFrame, name, phone, aadhaar, studentTable);
            refreshStudentTable();

        }

        if (e.getSource() == courseButton) {
            //System.out.println("courses is selected");
            cardLayout.show(bottomPanel, "CoursePanel");
        }
        if (e.getSource() == addCourseButton) {
            courseManager course = new courseManager();
            //System.out.println("add course button is clicked");
            course.addCourse(adminFrame);
        }
        if(e.getSource()==updateCourseButton){
            courseManager course = new courseManager();
            course.updateFee(adminFrame);
        }
        if(e.getSource()==deleteCourseButton){
            courseManager course = new courseManager();
            course.deleteCourse(adminFrame);
        }
    }
}

