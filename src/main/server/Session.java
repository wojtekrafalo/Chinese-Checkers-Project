package server;

import common.model.game.Color;
import common.model.game.Game;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private Client host;

    private String name;

    private int nrPlayers;

    private List<Client> players;

    private Game game;

    private Color turn;

    Session(String name, String nrPlayers, Client host) {
        players = new ArrayList<>();
        this.name = name;
        this.nrPlayers = Integer.parseInt(nrPlayers);
        this.host = host;
        players.add(host);
        //todo determine color of host Command
    }

    String getSessionName() {
        return name;
    }

    public void setTurn() {
        //TODO set turn
    }

    void join(Client client) {
        //TODO join client and determine colof of client command
    }

    public List<Client> getPlayers() {
        return players;
    }

    void leave(Client client){

    }

    private void start() {
        this.game = new Game(nrPlayers,17);
        //todo send information about game to clients
    }

    void move(int prevX, int prevY, int nextX, int nextY, Color color, Client movingPlayer ){
        //todo move
    }

    void pass(Client client){

    }

    Client getHost() {
        return host;
    }

    //todo determine winner and continue class
}
