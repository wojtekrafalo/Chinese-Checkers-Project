package client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class View {
    private FirstWindow firstWindow = new FirstWindow();
    private MainWindow mainWindow   = new MainWindow();

    public View() {
//        super("ViewWindow");
  //      firstWindow.setVisible(true);
    }

    public void addListener(ActionListener listener){
        firstWindow.addListener(listener);
        mainWindow.addListener(listener);
    }

    public void hideShow1 () {
//        primaryStage.hide();
//        mainStage.show();
    }

    public FirstWindow getFirstWindow () {
        return firstWindow;
    }

    public MainWindow getMainWindow () {
        return mainWindow;
    }

    public void startView (Stage primaryStage) throws Exception {
        Parent rootFirst = FXMLLoader.load(getClass().getResource("/client/view/first.fxml"));
//        Parent rootMain  = FXMLLoader.load(getClass().getResource("/client/view/Main.fxml"));
//        Parent rootGame  = FXMLLoader.load(getClass().getResource("/client/view/GameWindow.fxml"));

        Scene sceneFirst = new Scene(rootFirst,370,170);
//        Scene sceneMain = new Scene(rootMain,370,170);
//        Scene sceneGame = new Scene(rootGame,370,170);

        Stage mainStage = new Stage();
        Stage gameStage = new Stage();

        primaryStage.setScene(sceneFirst);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Write your nickname");
        primaryStage.show();

//        mainStage.setScene(sceneMain);
        mainStage.setResizable(false);
        mainStage.setTitle("Main Menu");

//        gameStage.setScene(sceneGame);
        gameStage.setResizable(false);
        gameStage.setTitle("Game");
    }
}