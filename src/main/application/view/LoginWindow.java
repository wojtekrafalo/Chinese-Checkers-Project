package application.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginWindow extends JFrame{

    private String errorMessage = "Please, insert correct nickName";
    private JLabel additionLabel = new JLabel("Please, insert your PESEL & password");
    private JTextField peselTextField = new JTextField(30);
    private JPasswordField passwordField = new JPasswordField(30);
    private JButton OK = new JButton("OK");

    LoginWindow (int width, int height) {
        super("LoginWindow");
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        loginPanel.setSize(new Dimension(width,height));
        loginPanel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);

        additionLabel.setSize(new Dimension(width - 40, height/4));
        peselTextField.setSize(new Dimension(width - 40, height/4));
        passwordField.setSize(new Dimension(width - 40, height/4));

        loginPanel.add(additionLabel);
        loginPanel.add(peselTextField);
        loginPanel.add(passwordField);
        loginPanel.add(OK);

        this.add(loginPanel);
    }

    public void addListener(ActionListener listener){
        OK.addActionListener(listener);
    }

    public String getPesel() {
        return peselTextField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public JButton getOK() {
        return OK;
    }
}
