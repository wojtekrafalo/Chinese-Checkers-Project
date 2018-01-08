package client.clientClass;

import client.controller.Controller;

import java.io.IOException;
import java.util.ArrayList;

public class ClientClass {
    public static void main(String[] args) {
        int pa = 8;
        Integer integ = new Integer(pa);
        System.out.println(integ);
        Controller theController;
        try {
            theController = new Controller();
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
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
