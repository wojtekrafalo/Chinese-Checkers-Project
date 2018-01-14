package client.clientClass;

import client.controller.Controller;
import common.model.game.Color;

import java.io.IOException;
import java.util.ArrayList;


public class ClientClass {
    public static void main(String[] args) {

        Controller theController;
        try {
            theController = new Controller();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
