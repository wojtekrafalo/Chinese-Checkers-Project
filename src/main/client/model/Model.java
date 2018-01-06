package client.model;
import common.model.game.*;
import server.Server;
import server.Session;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//dsafseasfcx
public class Model {
    private List<Boot> listOfBoots = new ArrayList<>();
    private Game game;
    private int numberOfPlayers;
    private int numberOfBoots;
    private Socket socket;
    private ServerHandle serverHandle;
    private List<Session> sessions;

    public Model (String nick, ServerHandle serverHandle) {
        this.serverHandle = serverHandle;
    }

    public void createNewGame(String nrPlayers, String size , String nrOfBoots) {
//        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBoots = Integer.parseInt(nrOfBoots);
        this.game = new Game(Integer.parseInt(nrPlayers), Integer.parseInt(size));

        while (numberOfBoots > 0) {
            listOfBoots.add(new Boot(game, Color.randomColor()));
        }
    }

    public Game getGame () {
        return game;
    }

    public void setSessions(String sessions) {
//        this.sessions = sessions;
    }
}