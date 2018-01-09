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

    private int nrBoots;

    private List<Client> players;

    private List<Boot> boots;

    private Game game;

    private boolean started;

    private Color turn;

    private List<Color> colors;

    private List<Color> colorsTemporary;

    private List<Boolean> continues;

    Session(String name, String nrPlayers, String nrBoots, Client host) {
        players = new ArrayList<>();
        boots = new ArrayList<>();
        continues = new ArrayList<>();
        this.name = name;
        this.nrPlayers = Integer.parseInt(nrPlayers);
        this.nrBoots = Integer.parseInt(nrBoots);
        this.host = host;
        this.started = false;
        players.add(host);
        setColors();
        host.setColor(colors.get(0));
        host.write(new Command(Instruction.CREATED,name,nrPlayers,nrBoots,String.valueOf(host.getColor())));
        System.out.println("New session created");
        if((players.size() + this.nrBoots) == this.nrPlayers){
            this.start();
        }
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
        if (this.players.size() + nrBoots < this.nrPlayers) {
            List<Client> receivers = this.players;
            this.players.add(client);
            client.setColor(colors.get(players.size() - 1));
            for (Client receiver : receivers) {
                receiver.write(new Command(
                        Instruction.PLAYER_JOINED,
                        String.valueOf(client.getClientID()),
                        String.valueOf(client.getNickname()),
                        String.valueOf(client.getColor())
                        ));
            }
            client.write(new Command(
                    Instruction.JOINED,
                    getSessionName(),
                    String.valueOf(getHost().getClientID()),
                    String.valueOf(nrPlayers),
                    String.valueOf(nrBoots),
                    String.valueOf(client.getColor()),
                    getClientsInfo())
            );
            if (this.players.size() + nrBoots == this.nrPlayers) {
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
                    for(Boot boot: boots){
                        boots.remove(boot);
                        boot = null;
                    }
                }else {
                    colorsTemporary.remove(client.getColor());
                    colorsTemporary.add(client.getColor());
                    for (Client clients : players) {
                        clients.write(new Command(Instruction.HOST_LEAVED, String.valueOf(host.getClientID())));
                    }
                }
            } else {
                if(boots.size() + players.size() == 1){
                    for(Client client1 : players){
                        client1.write(new Command(Instruction.INSTANT_WIN));
                    }
                    endGame();

                }else if(players.isEmpty()){
                    for(Boot boot: boots){
                        boots.remove(boot);
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
                    clients.write(new Command(Instruction.PLAYER_LEAVED, String.valueOf(client.getClientID())));
                }
            }
        }
    }

    private void start() {
        this.game = new Game(nrPlayers,17);
        this.started = true;
        turn = Color.randomColor(colors);
        game.setTurn(turn);
        for ( int i = 0 ; i < nrBoots; i++){
            Boot boot = new Boot(this, colors.get(players.size() + i),"Boot" + i + 1);
            boots.add(boot);
            for (Client receiver : players) {
                receiver.write(new Command(Instruction.BOOT_ADD,boot.getNick(),String.valueOf(boot.getColor())));
            }
        }
        for (Client receiver : players) {
            receiver.write(new Command(Instruction.START_GAME,String.valueOf(turn)));
        }
        for (Boot boot : boots){
            boot.run();
        }
        System.out.println("Session: " + name + " - Game Started , Turn: " + turn);
    }

    void move(int prevX, int prevY, int nextX, int nextY, Client movingPlayer) {
        if(turn == movingPlayer.getColor()) {
            if(game.canMove(prevX,prevY,nextX,nextY,movingPlayer.getColor())) {
                game.makeMove(prevX,prevY,nextX,nextY, movingPlayer.getColor());
                setTurn();
                game.setTurn(turn);
                for (Client client : players) {
                    client.write(new Command(
                            Instruction.MOVE_MADE,
                            String.valueOf(prevX),
                            String.valueOf(prevY),
                            String.valueOf(nextX),
                            String.valueOf(nextY),
                            String.valueOf(turn)
                    ));
                }
                if (!game.isWinner(movingPlayer.getColor())) {
                    return;
                }
                movingPlayer.write(new Command(Instruction.WIN));
                players.remove(movingPlayer);
                game.deleteMarbles(movingPlayer.getColor());
                colors.remove(movingPlayer.getColor());
                if(boots.size() + players.size() == 1) {
                    for(Client client1 : players){
                        client1.write(new Command(Instruction.LOST,String.valueOf(movingPlayer.getClientID())));
                    }
                    endGame();

                } else if(players.isEmpty()) {
                    for (Boot boot : boots) {
                        boots.remove(boot);
                        boot = null;
                    }
                    endGame();
                } else {
                    for (Client receiver : players) {
                        receiver.write(new Command(Instruction.LOST_CONTINUE,String.valueOf(movingPlayer.getClientID())));
                    }
                }
            }
            else if (game.canJump(prevX,prevY,nextX,nextY,movingPlayer.getColor())) {
                game.makeMove(prevX,prevY,nextX,nextY, movingPlayer.getColor());
                for (Client client : players) {
                    client.write(new Command(
                            Instruction.MOVE_MADE,
                            String.valueOf(prevX),
                            String.valueOf(prevY),
                            String.valueOf(nextX),
                            String.valueOf(nextY),
                            String.valueOf(turn)
                    ));
                }
                if (game.isWinner(movingPlayer.getColor())){
                    movingPlayer.write(new Command(Instruction.WIN));
                    players.remove(movingPlayer);
                    game.deleteMarbles(movingPlayer.getColor());
                    colors.remove(movingPlayer.getColor());
                    if(boots.size() + players.size() == 1) {
                        for(Client client1 : players){
                            client1.write(new Command(Instruction.LOST,String.valueOf(movingPlayer.getClientID())));
                        }
                        endGame();

                    } else if(players.isEmpty()) {
                        for (Boot boot : boots) {
                            boots.remove(boot);
                            boot = null;
                        }
                        endGame();
                    } else {
                        for (Client receiver : players) {
                            receiver.write(new Command(Instruction.LOST_CONTINUE,String.valueOf(movingPlayer.getClientID())));
                        }
                    }
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
            clients.write(new Command(Instruction.PASSED, String.valueOf(turn)));
        }
    }

    public void continuer(boolean decision){
        if(decision) {
            continues.add(true);
            if(continues.size() == players.size()) {
                for (Client client : players) {
                    client.write(new Command(Instruction.CONTINUE,String.valueOf(turn)));
                }
            }
        } else {
            for (Client client : players) {
                client.write(new Command(Instruction.NOT_CONTINUE));
            }
        }
    }

    private void endGame() {

    }

    public String getClientsInfo(){
        StringBuilder info = new StringBuilder();
        for ( Client client : players){
            info.append(client.getClientID())
                    .append(",")
                    .append(client.getNickname())
                    .append(",")
                    .append(client.getColor())
                    .append(",");
        }
        return info.toString();
    }

    public Game getGame() {
        return game;
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

    public int getNrBoots(){
        return nrBoots;
    }

}