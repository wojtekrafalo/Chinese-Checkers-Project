package server;

import common.model.connection.Command;
import common.model.connection.Instruction;
import common.model.game.Color;
import common.utils.Converter;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class instance is being generated after every new client connects to the server
 */
class Client extends Thread {

    /**
     * This client input stream
     */
    private ObjectInputStream input;

    /**
     * This client output stream
     */
    private ObjectOutputStream output;

    /**
     * This client current session (if host, player)
     */
    private Session session;

    /**
     * This client's socket
     */
    private Socket socket;

    /**
     * This client name (player name)
     */
    private String nickname;

    /**
     * This client status if is online
     */
    private boolean isOnline;

    /**
     * This client color if is in session
     */
    private Color color;

    /**
     * Specific client ID (for new client nextID is stored in <code>server.Server</code> class
     */
    private int clientID;

    /**
     * Creation of new Client instance
     *
     * @param socket Socket on which client class (on <code>server.Server</code>) communicates with client game
     */
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

    /**
     * Method for listening for input from client, run as another thread
     *
     * @throws IOException            Any of the usual Input/Output related exceptions.
     * @throws ClassNotFoundException Class of a serialized object cannot be
     *                                found.
     */
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
                    case NICK_ADD:
                        nickname = command.getParameters().get(0);
                        write(new Command(Instruction.NICK_INSERTED,nickname,String.valueOf(clientID)));
                        break;

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
                                command.getParameters().get(2),
                                this
                        );
                        Server.getSessionsList().add(this.session);
                        break;

                    case JOIN_GAME:

                        boolean exists = false;

                        for (Session session : Server.getSessionsList()) {
                            if (session.getHost().clientID == Integer.parseInt(command.getParameters().get(0))) {
                                if (session.getPlayers().size() < session.getNrPlayers()) {
                                    this.session = session;
                                    this.session.join(this);
                                    exists = true;
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
                        this.session.leave(this);
                        this.session = null;
                        this.write(new Command(Instruction.LEAVED));
                        break;

                    case MAKE_MOVE:
                        this.session.move(
                                Integer.parseInt(command.getParameters().get(0)),
                                Integer.parseInt(command.getParameters().get(1)),
                                Integer.parseInt(command.getParameters().get(2)),
                                Integer.parseInt(command.getParameters().get(3)),
                                this
                        );
                        break;

                    case PASS:
                        this.session.pass(this);
                        break;

                    case IF_CONTINUE:
                        this.session.continuer(Boolean.parseBoolean(command.getParameters().get(0)));
                        break;
                    case CLIENT_ENDS:
                        if (this.session != null) {
                            this.session.leave(this);
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

    /**
     * Method for parsing information about sessions from list to string
     * @param sessions List of sessions
     * @return String with information about sessions
     */
    private static String sessionsToList(List<Session> sessions) {
        StringBuilder sessionsList = new StringBuilder();
        for (Session s :
                sessions) {
            sessionsList.append(s.getSessionName())
                    .append(",")
                    .append(s.getHost().getNickname())
                    .append(",")
                    .append(s.getHost().getClientID())
                    .append(",")
                    .append(s.getPlayers().size())
                    .append(",")
                    .append(s.getNrBoots())
                    .append(",")
                    .append(s.getNrPlayers())
                    .append(",");
        }
        return sessionsList.toString();

    }

    /**
     * Writes to the client
     * @param command Command to be written
     */
    void write(Command command) {
        try {
            output.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter for client color in session
     * @param color color we want to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getter for client color in session
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for clientId
     * @return id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Getter for client nickname
     * @return Returns name of client
     */
    public String getNickname() {
        return nickname;
    }
}
