package client.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class MainWindow extends JFrame {
    private static final int WIDTH = 600, HEIGHT = 600;
    private JLabel leftLabel = new JLabel();
    private JList list;
    private JSplitPane splitPane;
    private CheckersPanel rightPanel = new CheckersPanel();                     //instead of initializing CheckersPanel here, I will create Builder in Constructor and then create correct right panel by idea of AbstractFactory pattern
    private String[] data = {"zero", "jeden", "dwa", "trzy", "cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery"};
                                                                                //it's useless. delete, when you would create handling of list and radio buttons
    MainWindow(){
        super("MainWindow");

        list = new JList(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(4);
        list.setLayoutOrientation(JList.VERTICAL);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(list), new JScrollPane(rightPanel));
        splitPane.setDividerLocation(WIDTH/3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.add(splitPane);
    }


    void addListener(ActionListener listener, ListSelectionListener listListener){
        rightPanel.addActionListener(listener);
        list.addListSelectionListener(listListener);
    }
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
                                                                            //it can be used for example, when there is not any more places in chosen game
    public CheckersPanel getRightPanel () {
        return rightPanel;
    }
}
