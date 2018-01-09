package client.model;
import com.sun.scenario.effect.LockableResource;
import common.model.game.*;
import common.utils.Converter;
import server.Session;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Boot> listOfBoots = new ArrayList<>();
    private Game game;
    private int numberOfPlayers;
    private int numberOfBoots;
    private int id;
    private int size;
    private Socket socket;
    private ServerHandle serverHandle;
    private LocalSession localSession;
    private List<Session> sessions;
    private String nameOfSession;
    private String hostNick;
    private Color hostColor;

    public Model (String nick, ServerHandle serverHandle) {
        this.serverHandle = serverHandle;
    }

    public void createNewGame(String name, String nrPlayers, String nrBoots, String hostColor , String nick, int id, int size) {
        this.nameOfSession = name;
        this.numberOfPlayers = Integer.parseInt(nrPlayers);
        this.numberOfBoots = Integer.parseInt(nrBoots);
        this.hostColor = Converter.parseColor(hostColor);
        this.hostNick = nick;
        this.id = id;
        this.size = size;
        this.game = new Game(this.numberOfPlayers + this.numberOfBoots, size);

        while (numberOfBoots > 0) {
//            listOfBoots.add(new Boot(game, Color.randomColor()));
        }
    }

    public Game getGame () {
        return game;
    }

    public void setSessions(String sessions) {
//        this.sessions = sessions;
    }

    public LocalSession getLocalSession() {
        return localSession;
    }

    public void setLocalSession(LocalSession localSession) {
        this.localSession = localSession;
    }
}