package client.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FirstWindow extends JFrame {
    private JLabel additionLabel = new JLabel("Please, insert your nickname");
    private JTextField nickName = new JTextField(30);
    private JButton OK = new JButton("OK");

    FirstWindow(){
        super("FirstWindow");
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new FlowLayout());
        firstPanel.setSize(new Dimension(600,200));
        firstPanel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        firstPanel.add(additionLabel);
        firstPanel.add(nickName);
        OK.setSize(new Dimension(50,50));
        firstPanel.add(OK);
        this.add(firstPanel);
    }
    public String getNickName(){
        return nickName.getText();
    }

    public JButton getOK() {
        return OK;
    }

    void addListener(ActionListener listener){
        OK.addActionListener(listener);
    }
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
