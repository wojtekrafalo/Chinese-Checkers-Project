package client.model;

import common.model.game.Color;
import common.model.game.Game;
import common.utils.Converter;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class LocalSession {

    private String name;

    private String hostName;

    private int nrPlayers;

    private int hostId;

    private int nrBoots;

    private Game game;

    private boolean started;

    private Color turn;

    private Color hostColor;

    private List<Pair<Pair<Integer, String>, Color>> players;
    private List<Pair<String, Color>> boots = new ArrayList<>();

    public LocalSession(String name, String nrPlayers, String nrBoots, String nick, int id, String color, Game game) {
        this.name = name;
        this.nrPlayers = Integer.parseInt(nrPlayers);
        this.nrBoots = Integer.parseInt(nrBoots);
        this.hostColor = Converter.parseColor(color);

        this.hostId = id;
        this.players = new ArrayList<>();
//        addPlayer(id, nick, Converter.parseColor(color));
        this.game = game;
    }

    public void addPlayer(int id, String nick, Color color) {
        players.add(new Pair(new Pair(new Integer(id), nick), color));
    }

    public void addBoot(String nick, String color) {
        Color color1 = Converter.parseColor(color);
        boots.add(new Pair(nick, color1));
    }

    String getSessionName() {
        return name;
    }

//    public void setTurn() {
//        int index = colors.indexOf(turn);
//        if (index == nrPlayers - 1) {
//            turn = colors.get(0);
//        }
//        else {
//            turn = colors.get(index + 1);
//        }
//    }

//    public List<Client> getPlayers() {
//        return players;
//    }

    Color getHostColor() {
        return hostColor;
    }

    public Color getTurn() {
        return turn;
    }

    public void setTurn(Color color) {
        this.turn = color;
    }

    public int getNrPlayers() {
        return nrPlayers;
    }

    public List<Pair<Pair<Integer, String>, Color>> getPlayers() {
        return players;
    }

    public List<Pair<String, Color>> getBoots() {
        return boots;
    }

    public int getNrBoots() {
        return nrBoots;
    }

    public Game getGame() {
        return game;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean getStarted() {
        return started;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }
}
