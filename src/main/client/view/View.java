package client.view;

import common.model.game.Game;
import client.model.LocalSession;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

public class View {
    private static final int DEF_WIDTH = 600, DEF_HEIGHT = 600,
                             FIRST_WIDTH= 600, FIRST_HEIGHT=200,
                             NEW_WIDTH = 300, NEW_HEIGHT = 600;

    private FirstWindow firstWindow = new FirstWindow();
    private GameWindow gameWindow;
    private NewGameWindow newGameWindow;
    private JoinGameWindow joinGameWindow;


    public View() {
        System.out.println("theView created");
        newGameWindow = new NewGameWindow();
        joinGameWindow = new JoinGameWindow();
        firstWindow.setVisible(true);
    }

    public void initializeGameWindow (LocalSession localSession) {
        gameWindow = new GameWindow(localSession);
    }

    public void addListener(ActionListener listener, MouseInputListener mouseListener){
        firstWindow.addListener(listener);
        newGameWindow.addListener(listener);
        joinGameWindow.addListener(listener, mouseListener);
    }

    public void addGameWindowListener(ActionListener listener, MouseInputListener mouseListener, Game game){
        gameWindow.addListener(listener, mouseListener);
    }

    public void hideShow1 () {
        firstWindow.setVisible(false);
        newGameWindow.setVisible(true);
    }
    public void hideShow2 () {
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
        joinGameWindow.setVisible(true);
    }

    public FirstWindow getFirstWindow () {
        return firstWindow;
    }
    public NewGameWindow getNewGameWindow() {
        return newGameWindow;
    }
    public JoinGameWindow getJoinGameWindow() {
        return joinGameWindow;
    }
    public GameWindow getGameWindow () {
        return gameWindow;
    }

    public void displayNotContinueInfo() {
        getJoinGameWindow().displayNotContinueInfo();
    }
}