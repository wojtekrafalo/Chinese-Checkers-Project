package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CheckersPanel extends JPanel {
    private static final int WIDTH = 600, HEIGHT = 600;
    private JButton OK = new JButton("OK");
    private JRadioButton player2= new JRadioButton("two players"), player3= new JRadioButton("three players"), player4= new JRadioButton("four players"), player6= new JRadioButton("six players");
    private ButtonGroup group;
    private JLabel titleLabel = new JLabel("Choose a number of players");
    private JLabel radioLabel = new JLabel();
    CheckersPanel() {
//        this.setLayout(new GridLayout(5,0));
        this.setLayout(null);

        titleLabel.setBounds(0, 0,300,50);
        player2.setBounds(WIDTH/6, HEIGHT/6 + 50,300,50);
        player3.setBounds(WIDTH/6, HEIGHT/6 + 100,300,50);
        player4.setBounds(WIDTH/6, HEIGHT/6 + 150,300,50);
        player6.setBounds(WIDTH/6, HEIGHT/6 + 200,300,50);
        OK.setBounds((int)(WIDTH/6), (int)(HEIGHT/6 + 250), 70,50);
        player2.setSelected(true);

        group = new ButtonGroup();
        group.add(player2);
        group.add(player3);
        group.add(player4);
        group.add(player6);
        radioLabel.setBounds((int)(WIDTH/6), (int)(HEIGHT/6), WIDTH, (int)(HEIGHT/2));
        radioLabel.add(titleLabel);
        radioLabel.add(player2);
        radioLabel.add(player3);
        radioLabel.add(player4);
        radioLabel.add(player6);
        radioLabel.add(OK);
        this.add(radioLabel);
        this.add(OK);
        this.add(player2);
        this.add(player3);
        this.add(player4);
        this.add(player6);
    }
    public void addActionListener(ActionListener listener) {
        player2.addActionListener(listener);
        player3.addActionListener(listener);
        player4.addActionListener(listener);
        player6.addActionListener(listener);
        OK.addActionListener(listener);
    }

    public JButton getOK () {
        return OK;
    }
    public JRadioButton getPlayer2 () {
        return player2;
    }
    public JRadioButton getPlayer3 () {
        return player3;
    }
    public JRadioButton getPlayer4 () {
        return player4;
    }
    public JRadioButton getPlayer6 () {
        return player6;
    }
}
