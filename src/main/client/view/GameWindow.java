package client.view;

import client.model.LocalSession;
import common.model.game.Game;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static int WIDTH = 600, HEIGHT = 600;
    private static String infoMessage = "INFO\n" +
            "INFO";

    private GamePanel gamePanel;
//    private LocalSession localSession;

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuReset=new MenuItem("RESET"), menuSurrender=new MenuItem("SURRENDER");

    public GameWindow (LocalSession localSession) {
        super("Chinese_Checkers");
        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuReset);
        menu1.add(menuSurrender);

        myMenu.add(menu1);
        this.setMenuBar(myMenu);
//        setResizable(false);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(60,60,WIDTH,HEIGHT);

        gamePanel = new GamePanel(WIDTH, HEIGHT, localSession, 4);
        gamePanel.setBounds(new Rectangle(60,60,WIDTH,HEIGHT));
        add(gamePanel);
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