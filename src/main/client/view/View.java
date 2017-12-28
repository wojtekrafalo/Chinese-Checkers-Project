package client.view;

import common.model.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

public class View {
    private static final int DEF_WIDTH = 600, DEF_HEIGHT = 600,
                             FIRST_WIDTH= 600, FIRST_HEIGHT=200,
                             NEW_WIDTH = 300, NEW_HEIGHT = 600;

    private FirstWindow firstWindow = new FirstWindow();
//    private MainWindow mainWindow   = new MainWindow();
    private GameWindow gameWindow;
    private NewGameWindow newGameWindow  = new NewGameWindow();
    private JoinGameWindow joinGameWindow  = new JoinGameWindow();

    public View(Game game) {
        firstWindow.setVisible(true);
//        mainWindow.setVisible(true);
//        gameWindow.setVisible(true);
//        newGameWindow.setVisible(true);
//        joinGameWindow.setVisible(true);
        gameWindow   = new GameWindow(DEF_WIDTH, DEF_HEIGHT, game);
    }

    public void addListener(ActionListener listener, ListSelectionListener listListener, MouseInputListener mouseListener){
        firstWindow.addListener(listener);
//        mainWindow.addListener(listener, listListener);
        gameWindow.addListener(listener, mouseListener);
        newGameWindow.addListener(listener);
        joinGameWindow.addListener(listener, listListener);
    }

    public void hideShow1 () {
        firstWindow.setVisible(false);
//        mainWindow.setVisible(true);
        newGameWindow.setVisible(true);
    }
    public void hideShow2 () {
//        mainWindow.setVisible(false);
        newGameWindow.setVisible(false);
        joinGameWindow.setVisible(false);
        gameWindow.setVisible(true);
    }
    public void hideShowChange () {
        newGameWindow.setVisible(!newGameWindow.isVisible());
        joinGameWindow.setVisible(!joinGameWindow.isVisible());
    }
    public void hideShow3 () {
        gameWindow.setVisible(false);
//        mainWindow.setVisible(true);
        joinGameWindow.setVisible(true);
    }


    public FirstWindow getFirstWindow () {
        return firstWindow;
    }
//    public MainWindow getMainWindow () {
//        return mainWindow;
//    }
    public NewGameWindow getNewGameWindow() {
        return newGameWindow;
    }
    public JoinGameWindow getJoinGameWindow() {
        return joinGameWindow;
    }
    public GameWindow getGameWindow () {
        return gameWindow;
    }
}