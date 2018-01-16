package client.model;

//sdafxczfdsda
import client.controller.Controller;
import common.model.connection.Command;
import common.model.connection.Instruction;
import common.model.game.Game;
import common.utils.Converter;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerHandle extends Thread{

    private static volatile ServerHandle serverHandle;

    private ObjectInputStream input;

    private ObjectOutputStream output;

    private Socket socket;

    private static String host;

    private String nick;

    private int id = 9;

    private static int port;

    private boolean isOnline;

    private int clientID;

    private static int nextClientID;

    private ServerSocket serverSocket;

    private static boolean running;

    private Thread listeningThread;

    private Model model;

    private LocalSession localSession;

    private Controller controller;

    private ServerHandle(String host, int port) throws IOException {
        ServerHandle.host = host;
        ServerHandle.port = port;

        this.socket = new Socket(host, port);
        System.out.println("next step");

        this.isOnline = true;

        try {
            System.out.println("Connection .");
            output = new ObjectOutputStream(this.socket.getOutputStream());
            System.out.println("Connection .c2");
            input = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Lost connection with Server.");
            Thread.currentThread().interrupt();
        }
        new Thread(() -> {
            try {
                System.out.println("Connection between Game and Server established.");
                listen();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("(in listen thread) Lost connection with Server.");
            }
        }).start();
    }

    public void send(Command command) throws IOException {
        ServerHandle.setHost("0.0.0.0");
        //Default port
        ServerHandle.setPort(5001);

        if (serverHandle == null) {
            synchronized (ServerHandle.class) {
                if (serverHandle == null) {
                    ServerHandle.serverHandle = new ServerHandle(host,port);
                }
            }
        }
        this.write(command);
    }

    private void listen() throws IOException, ClassNotFoundException {
        System.out.println("listen().");
        while (isOnline) {
            Command command = (Command) input.readObject();

            System.out.println(command.getName().toString() +
                    "\nwith params: " + command.getParameters().toString());

            if (command.getName().getNrParams() != command.getParameters().size() && command.getParameters().size() != -1) {
                System.out.println("But parameters were wrong!");
                write(new Command(Instruction.WRONG_NUM_OF_PARAMS));
            } else {

                switch (command.getName()) {

                    case CREATED:
//                        System.out.println(this.model.getLocalSession().getGame());

                        Game game = new Game(Integer.parseInt(command.getParameters().get(1)), 17);

                        LocalSession localSession = new LocalSession(command.getParameters().get(0), command.getParameters().get(1), command.getParameters().get(2), nick, id, command.getParameters().get(3), game);
                        localSession.addPlayer(id, nick, Converter.parseColor(command.getParameters().get(3)));
                        this.model.setLocalSession(localSession);
                        this.controller.setLocalSession(localSession);

                        System.out.println("Created, players: " + localSession.getPlayers());

                        controller.createGameView();

                        System.out.println("New game created");
                        //write(new Command(Instruction.START_GAME));
                        break;

                    case NICK_INSERTED:
                        this.nick = command.getParameters().get(0);
                        this.id = Integer.parseInt(command.getParameters().get(1));
                        this.model = new Model(command.getParameters().get(0), this);
                        System.out.println("theModel created in ServerHandle");

                        controller.setModel(this.model);
//                        this.model.setServerHandle(this);

                        System.out.println("New nick added:" + command.getParameters().get(0));

//                        write(new Command(Instruction.START_GAME));
                        break;

                    case START_GAME:
                        this.model.getLocalSession().setTurn(Converter.parseColor(command.getParameters().get(0)));
                        this.model.getLocalSession().setStarted(true);

                        System.out.println("Game started");
                        break;

                    case MOVE_MADE:
                        model.getLocalSession().getGame().makeMove(Integer.parseInt(command.getParameters().get(0)), Integer.parseInt(command.getParameters().get(1)), Integer.parseInt(command.getParameters().get(2)), Integer.parseInt(command.getParameters().get(3)), model.getLocalSession().getTurn());

                        System.out.println("Move made from: (" + command.getParameters().get(0) + ", " + command.getParameters().get(1) + ") to: (" + command.getParameters().get(2) + ", " + command.getParameters().get(3) + ") by " + model.getLocalSession().getTurn() +".");

                        model.getLocalSession().setTurn(Converter.parseColor(command.getParameters().get(4)));

                        controller.getView().getGameWindow().getSessionPanel().writeTurn();
                        controller.repaint();
                        break;

                    case JOINED:
                        ArrayList<String> lista = new ArrayList<String>(Arrays.asList(command.getParameters().get(5).split(",")));
                        int nrPlayers = Integer.parseInt(command.getParameters().get(2));
                        LocalSession localSession1 = new LocalSession(command.getParameters().get(0), command.getParameters().get(2),command.getParameters().get(3), nick, Integer.parseInt(command.getParameters().get(1)), command.getParameters().get(4), new Game(nrPlayers, 17));
                        for (int i=0; i<lista.size(); i+=3) {
                            localSession1.addPlayer(Integer.parseInt(lista.get(i)), lista.get(i+1), Converter.parseColor(lista.get(i+2)));
                        }

                        System.out.println("Jined, players: " + localSession1.getPlayers());

                        this.model.setLocalSession(localSession1);
                        this.controller.setLocalSession(localSession1);

                        controller.createGameView();
//                        write(new Command(Instruction.PLAYER_JOINED, lista.get(lista.size()-3), lista.get(lista.size()-2), lista.get(lista.size()-1)));
                        break;

                    case SEND_SESSIONS:
                        ArrayList<String> list = new ArrayList<String>(Arrays.asList(command.getParameters().get(0).split(",")));
                        this.model.setSessions(list);
                        if (list.size() % 6 ==0) this.controller.getView().getJoinGameWindow().setData(list);
                        break;

                    case SESSION_CHOSEN:
                        this.write(new Command(Instruction.JOIN_GAME));
                        break;

                    case BOOT_ADD:
                        this.localSession.addBoot(command.getParameters().get(0), command.getParameters().get(1));
                        break;

                    case PLAYER_JOINED:
                        this.controller.getLocalSession().addPlayer(
                                Integer.parseInt(command.getParameters().get(0)),
                                command.getParameters().get(1),
                                Converter.parseColor(command.getParameters().get(2))
                        );
                        controller.getView().getGameWindow().getSessionPanel().repaintLabels();
                        break;

                    case CANT_MOVE:
                        controller.getView().getGameWindow().displayError();
                        break;

                    case LEAVED:
                        this.localSession = null;
                        break;

                    case HOST_LEAVED:
                        this.localSession.setHostId(Integer.parseInt(command.getParameters().get(0)));
                        break;

                    case PLAYER_LEAVED:
                        for(Pair <Pair<Integer, String>, common.model.game.Color> pair : this.localSession.getPlayers()) {
                            if (pair.getKey().getKey().equals(
                                    new Integer(Integer.parseInt(command.getParameters().get(0))))) {
                                this.localSession.getPlayers().remove(pair);
                                break;
                            }
                        }
                        controller.getView().getGameWindow().getSessionPanel().repaintLabels();
                        break;

                    case PLAYER_LEAVED_IN_GAME:

                        for(Pair <Pair<Integer, String>, common.model.game.Color> pair : this.localSession.getPlayers()) {
                            if (pair.getKey().getKey().equals(
                                    new Integer(Integer.parseInt(command.getParameters().get(0))))) {
                                this.localSession.getGame().deleteMarbles(pair.getValue());
                                this.localSession.getPlayers().remove(pair);
                                break;
                            }
                        }
                        controller.getView().getGameWindow().getSessionPanel().repaintLabels();
                        break;

                    case HOST_LEAVED_IN_GAME:
                        for(Pair <Pair<Integer, String>, common.model.game.Color> pair : this.localSession.getPlayers()) {
                            if (pair.getValue().equals(
                                    this.localSession.getHostColor())) {
                                this.localSession.getGame().deleteMarbles(pair.getValue());
                                this.localSession.getPlayers().remove(pair);
                                break;
                            }
                        }
                        controller.getView().getGameWindow().getSessionPanel().repaintLabels();

                        this.localSession.setHostId(Integer.parseInt(command.getParameters().get(0)));
                        break;

                    case PASSED:

                        break;

                    case WIN:
                        this.controller.getView().hideShow3();
                        this.localSession = null;
                        this.controller.displayWinner(command.getParameters().get(0));
                        break;

                    case INSTANT_WIN:

                        break;

                    case LOST:
                        this.controller.getView().hideShow3();
                        this.localSession = null;
                        this.controller.displayWinner(command.getParameters().get(0));
                        break;

                    case LOST_CONTINUE:
                        int n = this.controller.displayLostContinueWindow(command.getParameters().get(0));
                        if (n==1 || n==2) {
                            write(new Command(Instruction.NOT_CONTINUE));
                        }
//                        else
//                            write(new Command(Instruction.CONTINUE));
                        break;

                    case CONTINUE:

                        break;

                    case NOT_CONTINUE:
                        this.controller.getView().hideShow3();
                        this.localSession = null;
                        this.controller.getView().displayNotContinueInfo();
                        break;

                    default:
                        break;
                }
            }
        }
        Thread.currentThread().interrupt();
    }

    public void write(Command command) {
        try {
            output.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setModel(Model theModel) {
        this.model = theModel;
    }

    public void setLocalSession(LocalSession localSession) {
        this.localSession = localSession;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static void setHost (String host) {
        ServerHandle.host = host;
    }

    public static void setPort (int port) {
        ServerHandle.port = port;
    }

    public static ServerHandle getServerHandle() throws IOException {
        ServerHandle.setHost("0.0.0.0");
        //Default port
        ServerHandle.setPort(5001);
        //Singleton
        if (serverHandle == null) {
            serverHandle = new ServerHandle(host, port);
        }
        return serverHandle;
    }
}
