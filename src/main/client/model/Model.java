package client.model;
import common.model.game.*;
import server.Server;
import server.Session;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Game> listOfGames = new ArrayList<>();
    List<Boot> listOfBoots = new ArrayList<>();
    private Game game;
    private int numberOfPlayers;
    private int numberOfBoots;
    private Session session;

    public Model (Game game) {
        this.game = game;
    }
    public Model (Session session, Game game) {
        this.session = session;
        this.game = game;
        this.listOfGames.add(game);
    }
    public void createNewGame(int numberOfPlayers, int NumberOfBoots) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBoots = numberOfBoots;
        while (numberOfBoots > 0) {
            listOfBoots.add(new Boot());
        }
        game = new Game (numberOfPlayers, 17);
        this.listOfGames.add(game);
        this.session = new Session();
    }
    public void addPlayer(String nick) {

    }
    public void joinToGame(Session chosenSession) {
        setSession(chosenSession);
    }
    public void setGame (Game game) {
        this.game = game;
    }
    public Game getGame () {
        return game;
    }
    public void setNumberOfPlayers (int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    public void setSession (Session session) {
        this.session = session;
    }
}