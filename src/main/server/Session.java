package server;

import common.model.connection.Command;
import common.model.connection.Instruction;
import common.model.game.Boot;
import common.model.game.Color;
import common.model.game.Game;

import java.util.ArrayList;
import java.util.List;


public class Session {

    private Client host;

    private String name;

    private int nrPlayers;

    private List<Client> players;

    private List<Boot> bots;

    private Game game;

    private boolean started;

    private Color turn;

    private List<Color> colors;
    private List<Color> colorsTemporary;

    Session(String name, String nrPlayers, Client host) {
        players = new ArrayList<>();
        this.name = name;
        this.nrPlayers = Integer.parseInt(nrPlayers);
        this.host = host;
        this.started = false;
        players.add(host);
        setColors();
        host.setColor(colors.get(0));
        host.write(new Command(Instruction.CREATED));
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
        this.colorsTemporary = colors;
    }

    String getSessionName() {
        return name;
    }

    public void setTurn() {
        int index = colors.indexOf(turn);
        if (index == nrPlayers - 1) {
            turn = colors.get(0);
        }
        else {
            turn = colors.get(index + 1);
        }
    }

    void join(Client client) {
        if (this.players.size() < this.nrPlayers) {
            List<Client> receivers = this.players;
            this.players.add(client);
            client.setColor(colors.get(players.size() - 1));
            for (Client receiver : receivers) {
                receiver.write(new Command(Instruction.PLAYER_JOINED));
            }
            client.write(new Command(Instruction.JOINED));
            if (this.players.size() == this.nrPlayers) {
              start();
            }
        }
    }

    public List<Client> getPlayers() {
        return players;
    }

    void leave(Client client){
        if (client == host){
            players.remove(client);
            host = players.get(0);
            if (!started) {
                if(players.isEmpty()){
                    Server.getSessionsList().remove(this);
                    for(Boot boot: bots){
                        bots.remove(boot);
                        boot = null;
                    }
                }else {
                    colorsTemporary.remove(client.getColor());
                    colorsTemporary.add(client.getColor());
                    for (Client clients : players) {
                        clients.write(new Command(Instruction.HOST_LEAVED));
                    }
                }
            } else {
                if(bots.size() + players.size() == 1){
                    for(Client client1 : players){
                        client1.write(new Command(Instruction.WIN));
                    }
                    endGame();

                }else if(players.isEmpty()){
                    for(Boot boot: bots){
                        boot = null;
                    }
                    endGame();
                }else {
                    if(turn==client.getColor()){
                        setTurn();
                    }
                    game.deleteMarbles(client.getColor());
                    colors.remove(client.getColor());
                    for (Client clients : players) {
                        clients.write(new Command(Instruction.HOST_LEAVED_IN_GAME));
                    }
                }
            }
        }
        else {
            players.remove(client);
            if(started){
                if(turn==client.getColor()){
                    setTurn();
                }
                game.deleteMarbles(client.getColor());
                colors.remove(client.getColor());
                for (Client clients : players) {
                    clients.write(new Command(Instruction.PLAYER_LEAVED_IN_GAME));
                }
            }
            else{
                colorsTemporary.remove(client.getColor());
                colorsTemporary.add(client.getColor());
                for (Client clients : players) {
                    clients.write(new Command(Instruction.PLAYER_LEAVED));
                }
            }
        }
    }

    private void start() {
        this.game = new Game(nrPlayers,17);
        this.started = true;
        turn = Color.randomColor(colors);
        game.setTurn(turn);
        for (Client receiver : players) {
            receiver.write(new Command(Instruction.START_GAME));
        }
    }

    void move(int prevX, int prevY, int nextX, int nextY,Client movingPlayer) {
        if(turn == movingPlayer.getColor()) {
            if(game.canMove(prevX,prevY,nextX,nextY,movingPlayer.getColor())) {
                game.makeMove(prevX,prevY,nextX,nextY, movingPlayer.getColor());
                setTurn();
                game.setTurn(turn);
                for (Client client : players) {
                    client.write(new Command(Instruction.MOVE_MADE));
                }
            }
            else if (game.canJump(prevX,prevY,nextX,nextY,movingPlayer.getColor())) {
                game.makeMove(prevX,prevY,nextX,nextY, movingPlayer.getColor());
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
        setTurn();
        game.setTurn(turn);
        for (Client clients : players) {
            clients.write(new Command(Instruction.PASS));
        }
    }

    private void endGame() {

    }

    Client getHost() {
        return host;
    }

    public Color getTurn() {
        return turn;
    }

    public int getNrPlayers() {
        return nrPlayers;
    }

    //todo determine winner and continue class
}