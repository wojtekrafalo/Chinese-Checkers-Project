package client.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class NewGameWindow extends JFrame {
    private static String infoMessage = "INFO\n" +
            "INFO";
    private static String errorMessage = "Please, give correct data";
    private static final int WIDTH = 400, HEIGHT = 600;
//    private JLabel panel = new JLabel();
    private CheckersPanel rightPanel = new CheckersPanel();                     //instead of initializing CheckersPanel here, I will create Builder in Constructor and then create correct right panel by idea of AbstractFactory pattern

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuJoin=new MenuItem("JOIN TO ANOTHER GAME"), menuExit=new MenuItem("EXIT");

    NewGameWindow () {
        super("Create New Game");
        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuJoin);
        menu1.add(menuExit);

        myMenu.add(menu1);
        this.setMenuBar(myMenu);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.add(rightPanel);
    }

    void addListener(ActionListener listener){
        rightPanel.addActionListener(listener);
        menuInfo.addActionListener(listener);
        menuJoin.addActionListener(listener);
        menuExit.addActionListener(listener);
    }

    public void displayErrorMessage(){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    //it can be used for example, when there is not any more places in chosen game
//    public JLabel getPanel () {
//        return panel;
//    }
    public JButton getOK () {
        return rightPanel.getOK();
    }
    public MenuItem getMenuInfo () {
        return menuInfo;
    }
    public MenuItem getMenuJoin () {
        return menuJoin;
    }
    public MenuItem getMenuExit () {
        return menuExit;
    }
    public CheckersPanel getRightPanel () {
        return rightPanel;
    }

    public void displayInfo(){
        JOptionPane.showMessageDialog(this, infoMessage);
    }
}