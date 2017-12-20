package client.view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private JLabel field = new JLabel();

    private MenuBar myMenu = new MenuBar();
    private Menu menu1 = new Menu("Menu");
    private MenuItem menuInfo = new MenuItem("INFO"), menuReset=new MenuItem("RESET"), menuSurrender=new MenuItem("SURRENDER");

//
    GameWindow () {
        super("Chinese_Checkers");
        menu1.add(menuInfo);
        menu1.addSeparator();
        menu1.add(menuReset);
        menu1.add(menuSurrender);

        myMenu.add(menu1);
        field.setIcon(new ImageIcon("C:/Users/WojciechKarol/Desktop/listy zadan/3semestr/technologia programowania/lab/images/field.jpg"));
        add(field);
        this.setMenuBar(myMenu);
        pack();
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addListener(ActionListener actionListener, MouseInputListener mouseListener) {
        menuInfo.addActionListener(actionListener);
        menuReset.addActionListener(actionListener);
        menuSurrender.addActionListener(actionListener);
        field.addMouseListener(mouseListener);
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
}
