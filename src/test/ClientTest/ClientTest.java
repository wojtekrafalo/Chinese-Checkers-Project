package ClientTest;

import client.controller.Controller;
import client.model.Model;
import client.view.View;

public class ClientTest {
    public static void main(String[] args) {
        View theView = new View();
        Model theModel = new Model();
        Controller theController = new Controller(theView, theModel);
    }
}