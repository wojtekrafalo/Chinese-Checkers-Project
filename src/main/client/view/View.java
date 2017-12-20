package client.view;

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
    private FirstWindow firstWindow = new FirstWindow();
    private MainWindow mainWindow   = new MainWindow();
    private GameWindow gameWindow   = new GameWindow();

    public View() {
//        super("ViewWindow");
        firstWindow.setVisible(true);
    }

    public void addListener(ActionListener listener, ListSelectionListener listListener, MouseInputListener mouseListener){
        firstWindow.addListener(listener);
        mainWindow.addListener(listener, listListener);
        gameWindow.addListener(listener, mouseListener);
    }

    public void hideShow1 () {
        firstWindow.setVisible(false);
        mainWindow.setVisible(true);
    }
    public void hideShow2 () {
        mainWindow.setVisible(false);
        gameWindow.setVisible(true);
    }
    public void hideShow3 () {
        gameWindow.setVisible(false);
        mainWindow.setVisible(true);
    }

    public FirstWindow getFirstWindow () {
        return firstWindow;
    }
    public MainWindow getMainWindow () {
        return mainWindow;
    }
    public GameWindow getGameWindow () {
        return gameWindow;
    }
}