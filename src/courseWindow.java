import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class courseWindow{
    private static final String url = "jdbc:mysql://localhost:3308/scec_database";
    private static final String username = "root";
    private static final String password = "toor";
    courseWindow(){
        JFrame courseFrame = new JFrame();
        courseFrame.setTitle("Shrivastava Computer Education Centre | Course Details");
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseFrame.setResizable(true);
        courseFrame.setSize(500, 500);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            //System.out.println("Connection successfull");
            JTable courseTable = new JTable();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Course","Duration","Course Fee"},0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    // This makes all cells uneditable
                    return false;
                }
            };
            courseTable.setModel(model);

            String query = "SELECT * from courses";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()){
                String course = res.getString("course");
                String duration = res.getString("duration");
                String fee = res.getString("fee");

                model.addRow(new Object[]{course,duration,fee});
            }
            stmt.close();
            con.close();

            courseTable.setRowHeight(30);
            courseTable.setGridColor(java.awt.Color.LIGHT_GRAY);
            courseTable.setShowGrid(true);
            courseTable.setSelectionBackground(java.awt.Color.decode("#AED6F1"));
            courseTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
            courseTable.getTableHeader().setBackground(Color.DARK_GRAY);
            courseTable.getTableHeader().setForeground(Color.WHITE);
            courseTable.setFont(new Font("Constantia",Font.BOLD,18));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            courseTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            courseTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            courseTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

            courseFrame.add(new JScrollPane(courseTable));
        }
        catch(ClassNotFoundException | SQLException e){
            Logger.getLogger(courseWindow.class.getName()).log(Level.SEVERE,null,e);
        }

        courseFrame.setVisible(true);
    }
}
