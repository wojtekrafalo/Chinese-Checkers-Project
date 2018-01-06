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
import server.Session;

import java.util.ArrayList;

public class ClientTest {
    public static void main(String[] args) {
        Controller theController = new Controller();
        ArrayList<String> sessions = new ArrayList<>();
        sessions.add("session");
        sessions.add("host1");
        sessions.add("3");
        sessions.add("3");
        sessions.add("172.168.0.0");
        sessions.add("session2");
        sessions.add("host2");
        sessions.add("32");
        sessions.add("32");
        sessions.add("172.168.0.02");
        sessions.add("session3");
        sessions.add("host3");
        sessions.add("33");
        sessions.add("33");
        sessions.add("172.168.0.03");
        theController.setSessions(sessions);
    }
}