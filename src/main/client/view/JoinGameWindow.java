package client.view;

import server.Session;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

public class JoinGameWindow extends JFrame {
    private static String infoMessage = "INFO\n" +
            "INFO";

    private static final int WIDTH = 600, HEIGHT = 600;
//    private JLabel leftLabel = new JLabel();
    private JList list;
    private JSplitPane splitPane;
    private JPanel rightPanel = new JPanel();
    private JButton OK = new JButton("OK");
    private JButton refreshButton = new JButton("Refresh");

    private JLabel infoAboutSession = new JLabel();
    private JLabel infoAboutHost = new JLabel();
    private JLabel infoAboutPlayers = new JLabel();
    private JLabel infoAboutBoots = new JLabel();
    private JLabel infoAboutHumans = new JLabel();
    private JLabel infoAboutID = new JLabel();

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuNew=new MenuItem("CREATE A NEW GAME"), menuExit=new MenuItem("EXIT");

    private int chosenSession=0;
    private String[] data = {};
    private ArrayList<String> sessions;

    JoinGameWindow(){
        super("Join to existing Game");

        list = new JList();
        setData(data);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(4);
        list.setLayoutOrientation(JList.VERTICAL);

        rightPanel.setLayout(null);
        infoAboutSession.setBounds(0,0,300,50);
        rightPanel.add(infoAboutSession);
        infoAboutHost.setBounds(0,50,300,50);
        rightPanel.add(infoAboutHost);
        infoAboutPlayers.setBounds(0,100,300,50);
        rightPanel.add(infoAboutPlayers);
        infoAboutHumans.setBounds(0,150,300,50);
        rightPanel.add(infoAboutHumans);
        infoAboutBoots.setBounds(0,200,300,50);
        rightPanel.add(infoAboutBoots);
        infoAboutID.setBounds(0,250,300,50);
        rightPanel.add(infoAboutID);
        OK.setBounds(100,300,70,70);
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
        System.out.println("JoinGameWindow created");
    }

    void addListener(ActionListener listener, ListSelectionListener listListener, MouseInputListener mouseInputListener){
        OK.addActionListener(listener);
        refreshButton.addActionListener(listener);
        list.addListSelectionListener(listListener);
        menuInfo.addActionListener(listener);
        menuNew.addActionListener(listener);
        menuExit.addActionListener(listener);
        list.addMouseListener(mouseInputListener);
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

    public JList getList () {
        return list;
    }
    public void setInfoAboutSession (int i) {
        infoAboutSession.setText("Name:                                   " + sessions.get(i*6));
        infoAboutHost.setText("Host Name:                          " + sessions.get(i*6 + 1));
        infoAboutID.setText("Host's ID:                            " + sessions.get(i*6 + 2));
        infoAboutPlayers.setText("Number of players:          " + sessions.get(i*6 + 3));
        infoAboutBoots.setText("Number of Boots:        " + sessions.get(i*6 + 4));
        infoAboutHumans.setText("Number of Humans:        " + sessions.get(i*6 + 5));
        chosenSession = i;
    }

    public void setData (String[] data) {
        this.data = data;

        String[] displayData = new String[data.length/6];

        for (int i=0; i<data.length; i+=6) {
            displayData[i/6] = data[i];
        }
        this.list.setListData(displayData);
    }

    public void setData (ArrayList<String> sessions) {
        this.sessions = sessions;

        String[] data = new String[sessions.size()/6];

        for (int i=0; i<sessions.size(); i+=6) {
            data[i/6] = sessions.get(i);
        }
        this.list.setListData(data);

        System.out.println("\n\n\n\n\n");
        for (String s : sessions) {
            System.out.println(s);
        }
        System.out.println("\n\n\n\n\n");
        for (String s : data) {
            System.out.println(s);
        }
        System.out.println("\n\n\n\n\n");
    }

    public int getSelectedNumber() {                          //send selected field of List
        return chosenSession;
    }

    public String[] getData() {                          //send selected field of List
        return data;
    }

    public void displayInfo(){
        JOptionPane.showMessageDialog(this, infoMessage);
    }
}
