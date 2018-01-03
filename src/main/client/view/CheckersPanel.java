package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CheckersPanel extends JPanel {
    private static final int WIDTH = 600, HEIGHT = 600;
    private JButton OK = new JButton("OK");
    private JRadioButton player2= new JRadioButton("two players"), player3= new JRadioButton("three players"), player4= new JRadioButton("four players"), player6= new JRadioButton("six players");
    private ButtonGroup group;
    private JLabel titleLabel1 = new JLabel("Choose a number of players");
    private JLabel titleLabel2 = new JLabel("Give a number of boots");
    private JLabel playerLabel = new JLabel();
    private JLabel bootLabel = new JLabel();
    private JTextField bootTextField = new JTextField("0");
    CheckersPanel() {
//        this.setLayout(new GridLayout(5,0));
        this.setLayout(null);

        titleLabel1.setBounds(0, 0,200,50);
        titleLabel2.setBounds(240, 100,600,50);
        player2.setBounds(WIDTH/6, HEIGHT/6 + 50,150,50);
        player3.setBounds(WIDTH/6, HEIGHT/6 + 100,150,50);
        player4.setBounds(WIDTH/6, HEIGHT/6 + 150,150,50);
        player6.setBounds(WIDTH/6, HEIGHT/6 + 200,150,50);
        bootTextField.setBounds((int)(WIDTH/2) - 50, (int)(HEIGHT/6 + 60), 100, 30);
        OK.setBounds((int)(WIDTH/6), (int)(HEIGHT/6 + 250), 70,50);
        player2.setSelected(true);

        group = new ButtonGroup();
        group.add(player2);
        group.add(player3);
        group.add(player4);
        group.add(player6);
        playerLabel.setBounds((int)(WIDTH/12), (int)(HEIGHT/6), WIDTH/6, (int)(HEIGHT/2));
        bootLabel.setBounds((int)(WIDTH/10), (int)(HEIGHT/6), WIDTH/4, (int)(HEIGHT/2));

        playerLabel.add(titleLabel1);
        playerLabel.add(player2);
        playerLabel.add(player3);
        playerLabel.add(player4);
        playerLabel.add(player6);
        playerLabel.add(OK);

        bootLabel.add(titleLabel2);
        bootLabel.add(bootTextField);
        this.add(playerLabel);
        this.add(OK);
        this.add(player2);
        this.add(player3);
        this.add(player4);
        this.add(player6);
        this.add(titleLabel2);
        this.add(bootTextField);
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
    public String getNumberOfBoots () {
        return bootTextField.getText();
    }
}
