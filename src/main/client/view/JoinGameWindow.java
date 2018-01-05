package client.view;

import server.Session;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class JoinGameWindow extends JFrame {
    private static String infoMessage = "INFO\n" +
            "INFO";

    private static final int WIDTH = 600, HEIGHT = 600;
//    private JLabel leftLabel = new JLabel();
    private JList list;
    private JSplitPane splitPane;
    private JPanel rightPanel = new JPanel();
    private JButton OK = new JButton("OK");

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuNew=new MenuItem("CREATE A NEW GAME"), menuExit=new MenuItem("EXIT");

    private String[] data = {"zero", "jeden", "dwa", "trzy", "cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery", "cztery","cztery","cztery","cztery","cztery","cztery","cztery", "cztery"};

    JoinGameWindow(){
        super("Join to existing Game");

        list = new JList(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(4);
        list.setLayoutOrientation(JList.VERTICAL);

        rightPanel.add(OK);
//        we add all information about sessions to rightPanel
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(list), new JScrollPane(rightPanel));
        splitPane.setDividerLocation(WIDTH/3);

        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuNew);
        menu1.add(menuExit);

        myMenu.add(menu1);
        this.setMenuBar(myMenu);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);

        this.add(splitPane);
    }

    void addListener(ActionListener listener, ListSelectionListener listListener){
        OK.addActionListener(listener);
        list.addListSelectionListener(listListener);
        menuInfo.addActionListener(listener);
        menuNew.addActionListener(listener);
        menuExit.addActionListener(listener);
    }
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    //it can be used for example, when there is not any more places in chosen game
    public JPanel getRightPanel () {
        return rightPanel;
    }
    public JButton getOK () {
        return OK;
    }

    public MenuItem getMenuInfo () {
        return menuInfo;
    }
    public MenuItem getMenuNew () {
        return menuNew;
    }

//    public Session getSelectedGame() {                          //send selected field of List
//        return new Session();
//    }
    public void displayInfo(){
        JOptionPane.showMessageDialog(this, infoMessage);
    }
}
