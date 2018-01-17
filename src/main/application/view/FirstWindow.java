package application.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class FirstWindow extends JFrame {
    private JLabel additionLabel = new JLabel("Please, choose your statement");

    private JButton adminButton = new JButton("ADMIN");
    private JButton clientButton = new JButton("CLIENT");
    private JButton employeeButton = new JButton("EMPLOYEE");
    private JButton editEmployeeButton = new JButton("EDIT_EMPLOYEE");

    public FirstWindow(int width, int height){
        super("FirstWindow");
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new FlowLayout());
        firstPanel.setSize(new Dimension(width,height));
        firstPanel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);

        firstPanel.add(additionLabel);
        adminButton.setSize(new Dimension(50,50));
        clientButton.setSize(new Dimension(50,50));
        employeeButton.setSize(new Dimension(50,50));
        editEmployeeButton.setSize(new Dimension(50,50));
        firstPanel.add(clientButton);
        firstPanel.add(employeeButton);
        firstPanel.add(editEmployeeButton);
        firstPanel.add(adminButton);

        this.add(firstPanel);
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getEmployeeButton() {
        return employeeButton;
    }

    public JButton getEditEmployeeButton() {
        return editEmployeeButton;
    }

    public JButton getClientButton() {
        return clientButton;
    }

    void addListener(ActionListener listener){
        adminButton.addActionListener(listener);
        clientButton.addActionListener(listener);
        employeeButton.addActionListener(listener);
        editEmployeeButton.addActionListener(listener);
    }
}