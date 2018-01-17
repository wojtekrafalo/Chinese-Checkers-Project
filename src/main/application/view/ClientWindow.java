package application.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

public class ClientWindow extends HumanWindow {
    private static String infoMessage = "INFO\n" +
            "INFO";

    private static final int WIDTH = 600, HEIGHT = 600;
    //    private JLabel leftLabel = new JLabel();
    private JList list;
    private JSplitPane splitPane;
    private JPanel rightPanel = new JPanel();

    private JButton logOUT = new JButton("LOG OUT");
    private JButton refreshButton = new JButton("Refresh");

    private JLabel infoOwnerName = new JLabel();
    private JLabel infoOwnerPesel = new JLabel();
    private JLabel infoCatName = new JLabel();
    private JLabel infoRoom = new JLabel();
    private JLabel infoRoomStandard = new JLabel();
    private JLabel infoDataOfMoveIn = new JLabel();
    private JLabel infoDataOfMoveOut = new JLabel();
    private JLabel infoComments = new JLabel();

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuExit=new MenuItem("EXIT");

    private int chosenSession=0;
    private String[] data = {};
    private ResultSet catTable;

    private ArrayList<String> ownersCats;

    public ClientWindow(){
        super("ClientWindow");

        list = new JList();
        setData(data);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(4);
        list.setLayoutOrientation(JList.VERTICAL);


        rightPanel.setLayout(null);
        infoOwnerName.setBounds(0,0,300,50);
        rightPanel.add(infoOwnerName);
        infoOwnerPesel.setBounds(0,50,300,50);
        rightPanel.add(infoOwnerPesel);
        infoCatName.setBounds(0,100,300,50);
        rightPanel.add(infoCatName);
        infoRoom.setBounds(0,150,300,50);
        rightPanel.add(infoRoom);
        infoRoomStandard.setBounds(0,200,300,50);
        rightPanel.add(infoRoomStandard);
        infoDataOfMoveIn.setBounds(0,250,300,50);
        rightPanel.add(infoDataOfMoveIn);
        infoDataOfMoveOut.setBounds(0,300,300,50);
        rightPanel.add(infoDataOfMoveOut);
        infoComments.setBounds(0,350,300,50);
        rightPanel.add(infoComments);

        logOUT.setBounds(100,300,70,40);
        rightPanel.add(logOUT);
        refreshButton.setBounds(170,300,70,40);
        rightPanel.add(refreshButton);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(list), new JScrollPane(rightPanel));
        splitPane.setDividerLocation(WIDTH/3);

        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuExit);

        myMenu.add(menu1);
        this.setMenuBar(myMenu);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);

        this.add(splitPane);
        System.out.println("JoinGameWindow created");
    }

    void addListener(ActionListener listener, MouseInputListener mouseInputListener){
        logOUT.addActionListener(listener);
        refreshButton.addActionListener(listener);
        menuInfo.addActionListener(listener);
        menuExit.addActionListener(listener);
        list.addMouseListener(mouseInputListener);
    }

    public MenuItem getMenuInfo () {
        return menuInfo;
    }

    public JList getList () {
        return list;
    }

    public void setInfoAboutCat (int i) {
        infoCatName.setText("Cat's name:                                   " + ownersCats.get(i*6));
        infoRoom.setText("Host Name:                          " + ownersCats.get(i*6 + 1));
        infoRoomStandard.setText("Host's ID:                            " + ownersCats.get(i*6 + 2));
        infoDataOfMoveIn.setText("Number of players:          " + ownersCats.get(i*6 + 3));
        infoDataOfMoveOut.setText("Number of Boots:        " + ownersCats.get(i*6 + 4));
        infoComments.setText("Number of Humans:        " + ownersCats.get(i*6 + 5));
        chosenSession = i;
    }

    public void setData (String[] data) {
    }
//    public void setData (ArrayList<String> sessions) {
//        this.sessions = sessions;
//
//        String[] data = new String[sessions.size()/6];
//
//        for (int i=0; i<sessions.size(); i+=6) {
//            data[i/6] = sessions.get(i);
//        }
//        this.list.setListData(data);
//
//        System.out.println("\n\n\n\n\n");
//        for (String s : sessions) {
//            System.out.println(s);
//        }
//        System.out.println("\n\n\n\n\n");
//        for (String s : data) {
//            System.out.println(s);
//        }
//        System.out.println("\n\n\n\n\n");
//    }


    public void setData(ResultSet data) {
//        this.data = data;
//        String[] displayData = new String[data.length/6];
//
//        for (int i=0; i<data.length; i+=6) {
//            displayData[i/6] = data[i];
//        }
//        this.list.setListData(displayData);
    }

    public int getSelectedNumber() {                          //send selected field of List
        return chosenSession;
    }

    public String[] getData() {                          //send selected field of List
        return data;
    }

    public JButton getLogOUT() {                          //send selected field of List
        return logOUT;
    }

    public void setOwnerData(String name, String pesel) {
        infoOwnerName.setText(name);
        infoOwnerPesel.setText(pesel);
    }

    public void displayInfo(){
        JOptionPane.showMessageDialog(this, infoMessage);
    }
}