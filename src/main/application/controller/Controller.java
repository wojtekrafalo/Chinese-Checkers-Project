package main.application.controller;

import application.model.Model;
import main.application.view.*;
import com.sun.org.apache.bcel.internal.generic.Instruction;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private application.view.View theView;
    private Model theModel;

    private String infoMessage = "This is Chinese Checkers Game\n" +
            "Add some instructions";

    public Controller() {
        System.out.println("theController created");

        this.theView = new application.view.View();
        this.theView.addListener(new MyActionListener(), new MouseListListener());
    }

    public void setModel(Model model) {
        this.theModel = model;
    }

    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nick = "";
            FirstWindow firstWindow = theView.getFirstWindow();
            LoginWindow loginWindow = theView.getLoginWindow();
            AdminWindow adminWindow = theView.getAdminWindow();
            ClientWindow clientWindow = theView.getClientWindow();
            EmployeeWindow employeeWindow = theView.getEmployeeWindow();
            EditEmployeeWindow editEmployeeWindow = theView.getEditEmployeeWindow();

            HumanWindow chosenFrame = null;
            if (e.getSource().equals(firstWindow.getAdminButton())) {
                System.out.println("\nAdmin");
                chosenFrame = adminWindow;
                theView.hideShow1();
            }
            if (e.getSource().equals(firstWindow.getClientButton())) {
                System.out.println("\nClient");
                chosenFrame = clientWindow;
                theView.hideShow1();
            }
            if (e.getSource().equals(firstWindow.getEmployeeButton())) {
                System.out.println("\nEmployee");
                chosenFrame = employeeWindow;
                theView.hideShow1();
            }
            if (e.getSource().equals(firstWindow.getEditEmployeeButton())) {
                System.out.println("\nEditEmployee");
                chosenFrame = editEmployeeWindow;
                theView.hideShow1();
            }

//            if (e.getSource().equals(adminWindow.getLogOUT()) || e.getSource().equals(clientWindow.getLogOUT()) || e.getSource().equals(employeeWindow.getLogOUT()) || e.getSource().equals(editEmployeeWindow.getLogOUT())) {
//                theView.logOutShow();
//            }

            if (e.getSource().equals(loginWindow.getOK())) {
                String pesel;
                char[] password;

                pesel = loginWindow.getPesel();
                password = loginWindow.getPassword();                           //cos dalej z tymi zmiennymi, wykorzystac chosenFrame, weryfikacja danych

                theView.hideShow2(chosenFrame);
            }
        }
    }


    class MouseListListener implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {                                            //do poprawy, zeby nie wyrzucal wyjatku jak klikne puste pole
            if (theView.getClientWindow().getData() != null) {
                int index = theView.getClientWindow().getList().locationToIndex(e.getPoint());
                System.out.println("Clicked on Item " + index);
                theView.getClientWindow().setInfoAboutCat(index);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mouseDragged(MouseEvent e) {}
        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    public application.view.View getView() {
        return theView;
    }
}