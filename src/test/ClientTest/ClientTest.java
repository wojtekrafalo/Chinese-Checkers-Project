package ClientTest;

import client.controller.Controller;
import client.model.Model;
import client.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientTest extends Application {
    View theView = new View();
    Model theModel = new Model();
    Controller theController = new Controller(theView, theModel);
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        theView.startView(primaryStage);
    }
}