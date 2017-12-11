package common.model.game;

import javafx.util.Pair;

/**
 * Class responsible for creating and playing the game. It contains game board
 */
public class Game {

    /**
     * Game board representation.
     */
    private Marble[][] board;

    /**
     * Determines whose player should move.
     */
    private Color turn = Color.NONE;

    /**
     * Number of players.
     */
    private int nrPlayers;

    /**
     *
     */
    private final static int[] WIDTHS =
            {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};

    /**
     *
     */
    private final static int[] OFFSETS =
            {4, 4, 4, 4, 0, 1, 2, 3, 4, 4, 4, 4, 4, 9, 10, 11, 12};

    /**
     * @param nrPlayers Number of players in game
     * @param size size of board
     */
    Game(final int nrPlayers, final int size) {
        this.board = new Marble[size][size];
        this.nrPlayers = nrPlayers;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j < OFFSETS[i] || j >= OFFSETS[i] + WIDTHS[i]) {
                    board[i][j] = null;
                } else {
                    board[i][j] = new Marble(i, j, Color.NONE, Color.NONE);
                }
            }
        }
        setNeighbours(size);
        setTerritories();
        setMarbles();
        startingPlayer();
    }

    private void setNeighbours(int size) {
        for (int i = 0; i < size; i++){
            for (int j = i; j < size; j++){
                if(board[i][j] != null){

                    if (i - 1 >= 0){
                        if (j - 1 >= 0 && board[i - 1][j - 1] != null) {
                            board[i][j].addNeighbour(i - 1, j - 1);
                            board[j][i].addNeighbour(j - 1, i - 1);
                        }
                        if (board[i - 1][j] != null) {
                            board[i][j].addNeighbour(i - 1, j);
                            board[j][i].addNeighbour(j, i - 1);
                        }
                    }
                    if (j - 1 >= 0 && board[i][j - 1] != null){
                        board[i][j].addNeighbour(i,j - 1);
                        board[j][i].addNeighbour(j - 1, i);
                    }
                    if (i + 1 < size){
                        if (j + 1 < size && board[i + 1][j + 1] != null){
                            board[i][j].addNeighbour(i + 1, j + 1);
                            board[j][i].addNeighbour(j + 1, i + 1);
                        }
                        if (board[i + 1][j] != null){
                            board[i][j].addNeighbour(i + 1, j);
                            board[j][i].addNeighbour(j , i + 1);
                        }
                    }
                    if (j + 1 < size && board[i][j + 1] != null){
                        board[i][j].addNeighbour(i,j + 1);
                        board[j][i].addNeighbour(j + 1, i);
                    }
                }
            }
        }
    }

    /**
     * Function that handles moving marbles.
     * @param prevX X position of marble which we want to move.
     * @param prevY Y position of marble which we want to move.
     * @param nextX X position where we want to move marble.
     * @param nextY Y position where we want to move marble.
     * @param color color of player what makes move.
     */
    public void makeMove(int prevX, int prevY, int nextX, int nextY, Color color) {
        if (validateCords(nextX, nextY)) {
            if (colorMatches(prevX, prevY, color)) {
                if (isInOppositeTerritory(prevX, prevY, color)) {
                    if (isMoveInTerritory(nextX, nextY, color)) {
                        if (canMakeMove(prevX, prevY, nextX, nextY)){
                            board[prevX][prevY].setColor(Color.NONE);
                            board[nextX][nextY].setColor(color);
                        }
                    }
                } else {
                    if (canMakeMove(prevX, prevY, nextX, nextY)) {
                        board[prevX][prevY].setColor(Color.NONE);
                        board[nextX][nextY].setColor(color);
                    }
                }
            }
        }
    }

    /**
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean isMoveInTerritory(int x, int y, Color color) {
        return board[x][y].getTerritory() == color.getOpposite();
    }

    /**
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean colorMatches(int x, int y, Color color) {
        return board[x][y].getColor() == color;
    }

    /**
     * @param x
     * @param y
     * @param color
     * @return
     */
    private boolean isInOppositeTerritory(int x, int y, Color color) {
        return board[x][y].getTerritory() == color.getOpposite();
    }


    /**
     * @return board at current game
     */

    private void setTerritories() {
        for (int i = 0; i < 4; i++) {
            for (int j = 4; j < i + 5; j++) {
                board[i][j].setTerritory(Color.AZURE);
            }
        }
        for (int i = 4; i < 8; i++) {
            for (int j = i - 4; j < 4; j++) {
                board[i][j].setTerritory(Color.GREEN);
                board[i][j + 9].setTerritory(Color.RED);
            }
        }
        for (int i = 9; i < 13; i++) {
            for (int j = 4; j < i - 4; j++) {
                board[i][j].setTerritory(Color.PINK);
                board[i][j + 9].setTerritory(Color.YELLOW);
            }
        }
        for (int i = 13; i < 17; i++) {
            for (int j = i - 4; j < 13; j++) {
                board[i][j].setTerritory(Color.BLUE);
            }
        }
    }

    private void setMarbles() {
        switch (nrPlayers){
            case 2:
                for (int i = 0; i < 4; i++) {
                    for (int j = 4; j < i + 5; j++) {
                        board[i][j].setColor(Color.AZURE);
                    }
                }
                for (int i = 13; i < 17; i++) {
                    for (int j = i - 4; j < 13; j++) {
                        board[i][j].setColor(Color.BLUE);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    for (int j = 4; j < i + 5; j++) {
                        board[i][j].setColor(Color.AZURE);
                    }
                }
                for (int i = 9; i < 13; i++) {
                    for (int j = 4; j < i - 4; j++) {
                        board[i][j].setColor(Color.PINK);
                        board[i][j + 9].setColor(Color.YELLOW);
                    }
                }
                break;
            case 4:
                for (int i = 4; i < 8; i++) {
                    for (int j = i - 4; j < 4; j++) {
                        board[i][j].setColor(Color.GREEN);
                        board[i][j + 9].setColor(Color.RED);
                    }
                }
                for (int i = 9; i < 13; i++) {
                    for (int j = 4; j < i - 4; j++) {
                        board[i][j].setColor(Color.PINK);
                        board[i][j + 9].setColor(Color.YELLOW);
                    }
                }
                break;
            case 6:
                for (int i = 0; i < 4; i++) {
                    for (int j = 4; j < i + 5; j++) {
                        board[i][j].setColor(Color.AZURE);
                    }
                }
                for (int i = 4; i < 8; i++) {
                    for (int j = i - 4; j < 4; j++) {
                        board[i][j].setColor(Color.GREEN);
                        board[i][j + 9].setColor(Color.RED);
                    }
                }
                for (int i = 9; i < 13; i++) {
                    for (int j = 4; j < i - 4; j++) {
                        board[i][j].setColor(Color.PINK);
                        board[i][j + 9].setColor(Color.YELLOW);
                    }
                }
                for (int i = 13; i < 17; i++) {
                    for (int j = i - 4; j < 13; j++) {
                        board[i][j].setColor(Color.BLUE);
                    }
                }
                break;
            default:
                break;
        }
    }
    private void startingPlayer(){
        while (turn == Color.NONE){
            turn = Color.randomColor();
        }
    }

    private boolean canMakeMove(int prevX, int prevY, int nextX, int nextY) {
        if (board[nextX][nextY].getColor() == Color.NONE) {
            Pair<Integer, Integer> coords = new Pair<>(nextX, nextY);
            for (Pair<Integer, Integer> pair : board[prevX][prevY].getNeighbours()) {
                if (coords.equals(pair)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateCords(int x, int y){
        return board[x][y] != null;
    }

    public Marble[][] getBoard() {
        return board;
    }

    public Color getTurn() {
        return turn;
    }

}
