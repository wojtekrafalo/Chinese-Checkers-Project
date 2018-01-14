package client.view;

import client.model.LocalSession;
import common.model.game.Game;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static int WIDTH = 700, HEIGHT = 700;
    private static String infoMessage = "INFO\n" +
            "INFO";

    private GamePanel gamePanel;
    private SessionPanel sessionPanel;
//    private LocalSession localSession;

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuReset=new MenuItem("RESET"), menuSurrender=new MenuItem("SURRENDER");

    GameWindow(LocalSession localSession) {
        super("Chinese_Checkers");
        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuReset);
        menu1.add(menuSurrender);

        myMenu.add(menu1);
        this.setMenuBar(myMenu);
//        setResizable(false);
        setResizable(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,WIDTH+300,HEIGHT+300);

        gamePanel = new GamePanel(WIDTH, HEIGHT, localSession, 4);
        gamePanel.setBounds(new Rectangle(0,0,WIDTH,HEIGHT));
        add(gamePanel);

        sessionPanel = new SessionPanel(localSession);
        sessionPanel.setBounds(new Rectangle(700, 0, 3*60, 6*40));
        add(sessionPanel);
//        pack();
    }

    public void addListener(ActionListener actionListener, MouseInputListener mouseListener) {
        menuInfo.addActionListener(actionListener);
        menuReset.addActionListener(actionListener);
        menuSurrender.addActionListener(actionListener);
        gamePanel.addMouseListener(mouseListener);
    }

    public MenuItem getMenuInfo() {
        return menuInfo;
    }
    public MenuItem getMenuReset() {
        return menuReset;
    }
    public MenuItem getMenuSurrender() {
        return menuSurrender;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void displayInfo(){
        JOptionPane.showMessageDialog(this, infoMessage);
    }
}