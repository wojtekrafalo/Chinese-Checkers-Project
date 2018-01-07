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
    private JLabel infoAboutHumans = new JLabel();
    private JLabel infoAboutID = new JLabel();

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuNew=new MenuItem("CREATE A NEW GAME"), menuExit=new MenuItem("EXIT");

    private int chosenSession=0;
    private String[] data = {"session", "host1", "3", "3", "172.168.0.0", "session2", "host2", "32", "32", "172.168.0.02", "session3", "host3", "33", "33", "172.168.0.03", };

    JoinGameWindow(){
        super("Join to existing Game");

        System.out.println("JoinGameWindow created");
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
        infoAboutID.setBounds(0,200,300,50);
        rightPanel.add(infoAboutID);
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

    void addListener(ActionListener listener, ListSelectionListener listListener, MouseInputListener mouseInputListener){
        OK.addActionListener(listener);
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
        infoAboutSession.setText("Name:                                   " + data[i*5]);
        infoAboutHost.setText("Host Name:                          " + data[i*5 + 1]);
        infoAboutPlayers.setText("Number of players:          " + data[i*5 + 2]);
        infoAboutHumans.setText("Number of Humans:        " + data[i*5 + 3]);
        infoAboutID.setText("Host's ID:                            " + data[i*5 + 4]);
        chosenSession = i;
    }

    public void setData (ArrayList<String> data) {
        this.data = new String[data.size()];
        for(int i=0; i<data.size(); i++) {
            this.data[i] = data.get(i);
        }

        String[] displayData = new String[data.size()/5];

        for (int i=0; i<data.size(); i+=5) {
            displayData[i/5] = data.get(i);
        }
        this.list.setListData(displayData);
    }

    public void setData (String[] data) {
        this.data = data;

        String[] displayData = new String[data.length/5];

        for (int i=0; i<data.length; i+=5) {
            displayData[i/5] = data[i];
        }
        this.list.setListData(displayData);
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
