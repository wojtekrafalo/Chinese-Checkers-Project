package test;

import main.application.controller.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mysqltutorial.org
 */
public class SimpleClass {

    public static void main(String[] args) {
        AdminWindow adminWindow = new AdminWindow();
        adminWindow.setVisible(true);
        ClientWindow clientWindow = new ClientWindow();
        clientWindow.setVisible(true);
        EditEmployeeWindow editEmployeeWindow = new EditEmployeeWindow();
        editEmployeeWindow.setVisible(true);
        EmployeeWindow employeeWindow = new EmployeeWindow();
        employeeWindow.setVisible(true);

        Controller controller = new Controller();
        String sql = "SELECT first_name, last_name, email " +
                "FROM candidates";

        try (Connection conn = MySQLJDBCUtil.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t" +
                        rs.getString("last_name")  + "\t" +
                        rs.getString("email"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}