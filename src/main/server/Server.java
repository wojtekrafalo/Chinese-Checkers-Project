package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic server logic class with listener for new client attempting to connect to socket.
 */
public class Server {

    /**
     * Instance of the server.
     */
    private static volatile Server server;

    /**
     * Auxiliary variable for server to grant Client's IDs.
     */
    private static int nextClientID;

    /**
     * Port for socket.
     */
    private int port;

    /**
     * Variable for storing ServerSocket.
     */
    private ServerSocket serverSocket;

    /**
     * Variable for breaking infinite socket interception loop.
     */
    private static boolean running;

    /**
     * List of current sessions.
     */
    private static List<Session> sessionsList;

    /**
     * Variable for storing thread for listening for new clients
     */
    private Thread listeningThread;

    /**
     * List of connected clients.
     */
    private static List<Client> clientsList;

    /**
     * Constructor for Server object.
     *
     * @param port Port on which server works
     * @throws IOException When something goes wrong with ServerSocket
     */
    private Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.port = port;
        nextClientID = 1;
        running = true;
        sessionsList = new ArrayList<>();
        clientsList = new ArrayList<>();
        listeningThread = new Thread(this::listen);
        listeningThread.start();
    }

    /**
     * Singleton method for getting an instance of a Server.
     * @param port Port on which server works
     * @return Instance of <code>Server</code>
     * @throws IOException When something goes wrong with ServerSocket
     */
    public static Server getServer(int port) throws IOException {
        if (server == null) {
            synchronized (Server.class) {
                if (server == null) {
                    server = new Server(port);
                }
            }
        }
        return server;
    }

    /**
     * Listens for sockets, have to be run as thread.
     */
    private void listen() {
        while(Server.running) {
            try {
                Socket socket = serverSocket.accept();
                Client thread = new Client(socket);
                thread.start();
                clientsList.add(thread);
            } catch (IOException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Getter for socket port.
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter for list of connected clients
     * @return list
     */
    public static List<Client> getClientsList() {
        return clientsList;
    }

    /**
     * Getter for list of sessions
     * @return list
     */
    public static List<Session> getSessionsList() {
        return sessionsList;
    }

    /**
     * Get next clientID to assign fo client
     * @return ClientID
     */
    public static int getNextClientID() {
        return nextClientID++;
    }

    /**
     * Boolean that checks if server is running
     * @return boolean
     */
    static boolean isRunning() {
        return running;
    }

    /**
     * Method for interrupting all the clients
     */
    static void interrupt() {
        running = false;
        for (Client client: clientsList) {
            client.interrupt();
        }
    }

    /**
     * Main method of Server
     * @param args args
     */
    public static void main(String[] args) {
        try {
            Server.getServer(5001);
        } catch (IOException e) {
            System.out.println("Port already in use");
        }
    }
}