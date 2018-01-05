package client.model;

//sdafxczfdsda
import client.controller.Controller;
import common.model.connection.Command;
import common.model.connection.Instruction;
import common.utils.Converter;
import server.Server;
import server.Session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandle extends Thread{

    private ObjectInputStream input;

    private ObjectOutputStream output;

    private Session session;

    private Socket socket;

    private int port;

    private boolean isOnline;

    private int clientID;

    private static int nextClientID;

    private ServerSocket serverSocket;

    private static boolean running;

    private Thread listeningThread;

    private Model model;

    private Controller controller;

    ServerHandle(Socket socket, Model model, Controller contorller) {
//        this.clientID = Server.getNextClientID();
        this.socket = socket;
        this.model = model;
        this.controller = contorller;
        this.isOnline = true;

        System.out.println("Connection between Game and Server established.");

        try {
            input = new ObjectInputStream(this.socket.getInputStream());
            output = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Lost connection with Server.");
            Thread.currentThread().interrupt();
        }
        new Thread(() -> {
            try {
                listen();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("(in listen thread) Lost connection with Server.");
            }
        }).start();
    }

    private void listen() throws IOException, ClassNotFoundException {
        while (isOnline) {
            Command command = (Command) input.readObject();
            System.out.println(command.getName().toString() +
                    "\nwith params: " + command.getParameters().toString() +
                    "\nfrom: " + this.clientID);
            if (command.getName().getNrParams() != command.getParameters().size() && command.getParameters().size() != -1) {
                System.out.println("But parameters were wrong!");
                write(new Command(Instruction.WRONG_NUM_OF_PARAMS));
            } else {

                switch (command.getName()) {

                    case CREATED:
                        this.model.createNewGame(
                                command.getParameters().get(0),
                                command.getParameters().get(1),
                                command.getParameters().get(2)
                        );

                        System.out.println("New game created");
                        write(new Command(Instruction.START_GAME));
                        break;

                    case START_GAME:
                        controller.start();
                        System.out.println("Game started");
                        break;

                    case MOVE_MADE:
                        controller.repaint();
                        System.out.println("Move made from: (" + command.getParameters().get(0) + ", " + command.getParameters().get(1) + ") to: (" + command.getParameters().get(2) + ", " + command.getParameters().get(3) + ") by " + command.getParameters().get(4) +".");
                        break;

                    case JOINED:
                        controller.addPlayer(command.getParameters().get(0), command.getParameters().get(1));
                        break;

                    case SEND_SESSIONS:
                        model.setSessions(command.getParameters().get(0));
                        break;

                    case SESSION_CHOSEN:
                        this.write(new Command(Instruction.JOIN_GAME));
                        break;

                    default:
                        break;
                }
            }
        }
        Thread.currentThread().interrupt();
    }

    void write(Command command) {
        try {
            output.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
