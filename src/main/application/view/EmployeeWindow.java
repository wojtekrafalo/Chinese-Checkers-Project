package application.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import javax.swing.*;

public class EmployeeWindow extends HumanWindow {
    private static final int WIDTH = 600, HEIGHT = 600;

    private JScrollPane scrollPane = new JScrollPane();
    private JPanel panel = new JPanel();
    private JLabel[][] infoRooms;

    private ResultSet roomTable;

    private JButton logOUT = new JButton("LOG OUT");
    private JButton refreshButton = new JButton("Refresh");


    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuExit=new MenuItem("EXIT");


    private static String infoMessage = "INFO\n" +
            "INFO";

    public EmployeeWindow() {
        super("EmployeeWindow");
        logOUT.setBounds(440,0,80,40);
        refreshButton.setBounds(480,0,80,40);
//        setting roomTable into JLabel[][] table and adding JLabel[][] into  panel. try use 'setInfoAboutRooms()' method

        scrollPane.setViewportView(panel);

        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuExit);

        myMenu.add(menu1);
        this.setMenuBar(myMenu);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);

        this.add(scrollPane);
        this.add(logOUT);
        this.add(refreshButton);
    }

    public void setInfoAboutRooms () {

    }


    void addListener(ActionListener listener){
        logOUT.addActionListener(listener);
        refreshButton.addActionListener(listener);
        menuInfo.addActionListener(listener);
        menuExit.addActionListener(listener);
    }

    public MenuItem getMenuInfo () {
        return menuInfo;
    }

    public JButton getLogOUT() {                          //send selected field of List
        return logOUT;
    }

    public void displayInfo(){
        JOptionPane.showMessageDialog(this, infoMessage);
    }
}