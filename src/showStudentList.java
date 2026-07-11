import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class showStudentList {
    private static final String url = "jdbc:mysql://localhost:3308/scec_database";
    private static final String username = "root";
    private static final String password = "toor";
    showStudentList(){
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,username,password);

                JTable studentListTable = new JTable();
                //DefaultTableModel model = new DefaultTableModel(new String[]{},0);
        }
        catch(ClassNotFoundException | SQLException e){
            Logger.getLogger(courseWindow.class.getName()).log(Level.SEVERE,null,e);
        }
    }
}
