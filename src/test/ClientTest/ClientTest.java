package ClientTest;

import client.controller.Controller;
import client.model.Model;
import client.view.View;
import common.model.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientTest extends Application {
    public static void main(String[] args) {
        Game game = new Game (2,17);
        Model theModel = new Model(game);
        View theView = new View(game);

        Controller theController = new Controller(theView, theModel);
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
//        theView.startView(primaryStage);
    }
}