package client.view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame {
    private JLabel additionLabel = new JLabel("Please, insert your nickname");
    private JTextField nickName = new JTextField(30);
    private JButton OK = new JButton("OK");

    MainWindow(){
        super("MainWindow");
        JPanel firstPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        firstPanel.add(additionLabel);
        firstPanel.add(nickName);
        firstPanel.add(OK);
        this.add(firstPanel);
    }
    public String getnickName(){
        return nickName.getText();
    }

    void addListener(ActionListener listener){
        OK.addActionListener(listener);
    }
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
