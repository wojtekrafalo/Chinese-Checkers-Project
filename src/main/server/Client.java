package server;

import common.model.connection.Command;
import common.model.connection.Instruction;
import common.model.game.Color;
import common.utils.Converter;

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

    private Color color;

    private int clientID;

    Client(Socket socket) {
        this.clientID = Server.getNextClientID();
        this.socket = socket;
        this.isOnline = true;
        System.out.println("Connection with new client established. Client ID: " + clientID);
        try {
            input = new ObjectInputStream(this.socket.getInputStream());
            output = new ObjectOutputStream(this.socket.getOutputStream());
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
            Command command = (Command) input.readObject();
            System.out.println(command.getName().toString() +
                    "\nwith params: " + command.getParameters().toString() +
                    "\nfrom: " + this.clientID);
            if (command.getName().getNrParams() != command.getParameters().size()) {
                System.out.println("But parameters were wrong!");
                write(new Command(Instruction.WRONG_NUM_OF_PARAMS));
            } else {

                switch (command.getName()) {

                    case CREATE_GAME:
                        if (this.session != null) {
                            Server.getSessionsList().remove(session);
                            this.session = null;
                        }

                        for (Session session : Server.getSessionsList()){
                            if (session.getHost() == this) {
                                Server.getSessionsList().remove(session);
                            }
                        }

                        this.session = new Session(
                                command.getParameters().get(0),
                                command.getParameters().get(1),
                                this
                        );
                        Server.getSessionsList().add(this.session);
                        write(new Command(Instruction.CREATED));
                        System.out.println("New session created");
                        break;

                    case JOIN_GAME:

                        boolean exists = false;

                        for (Session session : Server.getSessionsList()) {
                            if (session.getHost().clientID == Integer.parseInt(command.getParameters().get(0))) {
                                if (session.getPlayers().size() < session.getNrPlayers()) {
                                    this.session = session;
                                    this.session.join(this);
                                    exists = true;
                                    this.write(new Command(Instruction.JOINED));
                                }

                            }
                        }
                        if (!exists) {
                            this.write(new Command(Instruction.SESSION_UNAVAILABLE));
                        }
                        break;

                    case REQUIRE_SESSIONS:
                        this.write(new Command(Instruction.SEND_SESSIONS, Client.sessionsToList(Server.getSessionsList())));
                        break;

                    case LEAVE_GAME:
                        //TODO leaving game, delete marbles, etc
                        break;

                    case MAKE_MOVE:
                        this.session.move(
                                Integer.parseInt(command.getParameters().get(0)),
                                Integer.parseInt(command.getParameters().get(1)),
                                Integer.parseInt(command.getParameters().get(2)),
                                Integer.parseInt(command.getParameters().get(3)),
                                //Converter.parseColor(command.getParameters().get(4)),
                                this
                        );
                        break;

                    case PASS:
                        session.pass(this);
                        break;

                    case CLIENT_ENDS:
                        if (this.session != null) {
                            Server.getSessionsList().remove(session);
                            this.session = null;
                        }
                            Server.getClientsList().remove(this);
                            Thread.currentThread().interrupt();
                        break;

                    default:
                        break;

                }
            }
        }
        Server.getClientsList().remove(this);
        Thread.currentThread().interrupt();
    }

    private static List<String> sessionsToList(List<Session> sessions) {
        List<String> sessionsList = new ArrayList<>();
        for (Session s :
                sessions) {
            sessionsList.add(s.getSessionName());
            sessionsList.add(s.getHost().getName());
            sessionsList.add(Integer.toString(s.getPlayers().size()));
            sessionsList.add(Integer.toString(s.getNrPlayers()));
            sessionsList.add(Integer.toString(s.getHost().getClientID()));
        }
        return sessionsList;

    }

    void write(Command command) {
        try {
            output.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }

    public int getClientID() {
        return clientID;
    }

    public String getNickname() {
        return nickname;
    }
}
