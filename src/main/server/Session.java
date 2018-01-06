package server;

import common.model.connection.Command;
import common.model.connection.Instruction;
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

    private List<Color> colors;

    Session(String name, String nrPlayers, Client host) {
        players = new ArrayList<>();
        this.name = name;
        this.nrPlayers = Integer.parseInt(nrPlayers);
        this.host = host;
        players.add(host);
        setColors();

        //host color = colors[0]
    }

    private void setColors() {
        this.colors = new ArrayList<>();
        switch (nrPlayers){
            case 2:
                colors.add(Color.AZURE);
                colors.add(Color.BLUE);
                break;
            case 3:
                colors.add(Color.AZURE);
                colors.add(Color.YELLOW);
                colors.add(Color.PINK);
                break;
            case 4:
                colors.add(Color.RED);
                colors.add(Color.YELLOW);
                colors.add(Color.PINK);
                colors.add(Color.GREEN);
                break;
            case 6:
                colors.add(Color.AZURE);
                colors.add(Color.RED);
                colors.add(Color.YELLOW);
                colors.add(Color.BLUE);
                colors.add(Color.PINK);
                colors.add(Color.GREEN);
                break;
            default:
                host.write(new Command(Instruction.WRONG_NUM_OF_PLAYERS));
        }
    }

    String getSessionName() {
        return name;
    }

    public void setTurn() {
        //TODO set turn
    }

    void join(Client client) {
        if (this.players.size() < this.nrPlayers) {
            List<Client> receivers = this.players;
            this.players.add(client);
            for (Client receiver : receivers) {
                receiver.write(new Command(Instruction.PLAYER_JOINED)); //color[size - 1]
            }
            client.write(new Command(Instruction.JOINED)); //color[size - 1]
            if (this.players.size() == this.nrPlayers) {
              start();
            }
        }
    }

    public List<Client> getPlayers() {
        return players;
    }

    void leave(Client client){

    }

    private void start() {
        this.game = new Game(nrPlayers,17);
        for (Client receiver : players) {
            receiver.write(new Command(Instruction.START_GAME));
        }
    }

    void move(int prevX, int prevY, int nextX, int nextY,Client movingPlayer) {
        if(turn == movingPlayer.getColor()) {
            if(game.canMove(prevX,prevY,nextX,nextY,movingPlayer.getColor())) {
                game.makeMove(prevX,prevY,nextX,nextY, movingPlayer.getColor());
                setTurn();
                for (Client client : players) {
                    client.write(new Command(Instruction.MOVE_MADE));
                }
            }
            else {
                movingPlayer.write(new Command(Instruction.CANT_MOVE));
            }
        }
    }

    void pass(Client client) {

    }

    Client getHost() {
        return host;
    }

    public int getNrPlayers() {
        return nrPlayers;
    }

    //todo determine winner and continue class
}