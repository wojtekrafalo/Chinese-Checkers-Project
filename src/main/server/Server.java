package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static volatile Server server;

    private static int nextClientID;

    private int port;

    private ServerSocket serverSocket;

    private static boolean running;

    private static List<Session> sessionsList;

    private Thread listeningThread;

    private static List<Client> clientsList;

    private Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.port = port;
        nextClientID = 0;
        running = true;
        sessionsList = new ArrayList<>();
        clientsList = new ArrayList<>();
        listeningThread = new Thread(this::listen);
        listeningThread.start();
    }

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

    public int getPort() {
        return port;
    }

    public static List<Client> getClientsList() {
        return clientsList;
    }

    public static List<Session> getSessionsList() {
        return sessionsList;
    }

    public static int getNextClientID() {
        return nextClientID++;
    }

    static boolean isRunning() {
        return running;
    }

    static void interrupt() {
        running = false;
        for (Client client: clientsList) {
            client.interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            Server.getServer(5001);
        } catch (IOException e) {
            System.out.println("Port already in use");
        }
    }
}