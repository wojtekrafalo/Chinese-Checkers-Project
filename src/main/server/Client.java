package server;

import common.model.connection.Command;
import common.model.connection.Instruction;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Client extends Thread {

    private ObjectInputStream input;

    private ObjectOutputStream output;

    private Session session;

    private Socket socket;

    private String nickname;

    private boolean isOnline;

    private int clientID;

    Client(Socket socket) {
        this.clientID = Server.getNextClientID();
        this.socket = socket;
        this.isOnline = true;
        System.out.println("Connection with new client established. Client ID: " + clientID);
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("(in Client const) Lost connection with Client: " + clientID + "!");
            Thread.currentThread().interrupt();
        }
        new Thread(() -> {
            try {
                listen();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("(in listen thread) Lost connection with Client: " + clientID + "!");
            }
        }).start();
    }

    private void listen() throws IOException, ClassNotFoundException {
        while (isOnline && Server.isRunning()) {
            //Read command from input
            Command command = (Command) input.readObject();
            //Print command on server console
            System.out.println(command.getName().toString() +
                    "\nwith params: " + command.getParameters().toString() +
                    "\nfrom: " + this.clientID);

            //Check if number of command parameters is ok
            //If not, print error on server console
            if (command.getName().getNrParams() != command.getParameters().size() && command.getParameters().size() != -1) {
                System.out.println("But parameters were wrong!");
                output.writeObject(new Command(Instruction.WRONG_NUM_OF_PARAMS));
            } else {

                switch (command.getName()) {

                    //TODO: cases

                }
            }
        }
    }

}
