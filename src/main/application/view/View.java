package application.view;

import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

public class View {
    private static final int DEF_WIDTH = 600, DEF_HEIGHT = 600,
            FIRST_WIDTH= 260, FIRST_HEIGHT=200,
            LOGIN_WIDTH= 500, LOGIN_HEIGHT=200,
            NEW_WIDTH = 300, NEW_HEIGHT = 600;

    private FirstWindow firstWindow = new FirstWindow(FIRST_WIDTH, FIRST_HEIGHT);
    private LoginWindow loginWindow;

    private ClientWindow clientWindow;
    private EmployeeWindow employeeWindow;
    private EditEmployeeWindow editEmployeeWindow;
    private AdminWindow adminWindow;

    public View() {
        System.out.println("theView created");
        firstWindow.setVisible(true);
        loginWindow = new LoginWindow(LOGIN_WIDTH, LOGIN_HEIGHT);
//        clientWindow = new ClientWindow();
//        adminWindow = new AdminWindow();
//        employeeWindow = new EmployeeWindow();
//        editEmployeeWindow = new EditEmployeeWindow();
    }

    public void addListener(ActionListener listener, MouseInputListener mouseListListener){
        firstWindow.addListener(listener);
        loginWindow.addListener(listener);
//        clientWindow.addListener(listener);
//        adminWindow.addListener(listener);
    }

    public void hideShow1 () {
        firstWindow.setVisible(false);
        loginWindow.setVisible(true);
    }

    public void hideShow2 (HumanWindow window) {
        loginWindow.setVisible(false);                                      //uwidzialnic wybrane okno
        window.setVisible(true);
    }

    public void logOutShow () {
        firstWindow.setVisible(true);
        loginWindow.setVisible(false);
        clientWindow.setVisible(false);
        adminWindow.setVisible(false);
        employeeWindow.setVisible(false);
        editEmployeeWindow.setVisible(false);
    }

    public FirstWindow getFirstWindow () {
        return firstWindow;
    }
    public LoginWindow getLoginWindow() {
        return loginWindow;
    }
    public ClientWindow getClientWindow() {
        return clientWindow;
    }
    public AdminWindow getAdminWindow () {
        return adminWindow;
    }
    public EmployeeWindow getEmployeeWindow () {
        return employeeWindow;
    }
    public EditEmployeeWindow getEditEmployeeWindow () {
        return editEmployeeWindow;
    }
}