package client.model;
import common.model.game.*;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Game> listOfGames = new ArrayList<>();
    private int numberOfPlayers;
    //arrays of field of stones

    public void setNumberOfPlayers (int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    public void addPlayer(String nick) {

    }
}