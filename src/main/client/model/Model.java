package client.model;
import common.model.game.*;
import server.Server;
import server.Session;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Game> listOfGames = new ArrayList<>();
    private Game game;
    private int numberOfPlayers;
    private Session session;
//    arrays of field of stones

    public Model (Game game) {
        this.game = game;
    }
    public void setNumberOfPlayers (int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    public void createNewGame() {
        Session session = new Session();
        this.session = session;

    }
    public void joinToGame(Session chosenSession) {
        setSession(chosenSession);
    }
    public void addPlayer(String nick) {

    }
//    public void setGame (Game game) {
//        this.game = game;
//    }
    public Game getGame () {
        return game;
    }
    public void setSession (Session session) {
        this.session = session;
    }
}