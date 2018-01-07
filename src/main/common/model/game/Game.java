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

    private int size;

    /**
     * Widths of each row
     */
    private final static int[] WIDTHS =
            {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};

    /**
     * Starting column in each row
     */
    private final static int[] OFFSETS =
            {4, 4, 4, 4, 0, 1, 2, 3, 4, 4, 4, 4, 4, 9, 10, 11, 12};

    private final static int[][] EXTREMEPOINTS =
            {{0,4}, {4,0}, {4,12}, {12,4}, {12,16}, {16,12}};

    /**
     * @param nrPlayers Number of players in game
     * @param size Size of board
     */
    public Game(final int nrPlayers, final int size) {
        this.board = new Marble[size][size];
        this.nrPlayers = nrPlayers;
        this.size = size;

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (j < OFFSETS[i] || j >= OFFSETS[i] + WIDTHS[i]) {
                    board[i][j] = null;
                    board[j][i] = null;
                } else {
                    board[i][j] = new Marble(i, j, Color.NONE, Color.NONE);
                    board[j][i] = new Marble(j, i, Color.NONE, Color.NONE);
                }
            }
        }
        setNeighbours();
        setJumps();
        setTerritories();
        setMarbles();
    }

    /**
     *
     */
    private void setNeighbours() {
        for (int i = 0; i < size; i++){
            for (int j = i; j < size; j++){
                if (j == i) {
                    if (board[i][j] != null) {

                        if (i - 1 >= 0) {
                            if (j - 1 >= 0 && board[i - 1][j - 1] != null) {
                                board[i][j].addNeighbour(i - 1, j - 1,Direction.NORTH_WEST);
                            }
                            if (board[i - 1][j] != null) {
                                board[i][j].addNeighbour(i - 1, j,Direction.NORTH);
                            }
                        }
                        if (j - 1 >= 0 && board[i][j - 1] != null) {
                            board[i][j].addNeighbour(i, j - 1,Direction.WEST);
                        }
                        if (i + 1 < size) {
                            if (j + 1 < size && board[i + 1][j + 1] != null) {
                                board[i][j].addNeighbour(i + 1, j + 1,Direction.SOUTH_EAST);
                            }
                            if (board[i + 1][j] != null) {
                                board[i][j].addNeighbour(i + 1, j,Direction.SOUTH);
                            }
                        }
                        if (j + 1 < size && board[i][j + 1] != null) {
                            board[i][j].addNeighbour(i, j + 1,Direction.EAST);
                        }
                    }
                }
                else {
                    if (board[i][j] != null) {

                        if (i - 1 >= 0) {
                            if (j - 1 >= 0 && board[i - 1][j - 1] != null) {
                                board[i][j].addNeighbour(i - 1, j - 1,Direction.NORTH_WEST);
                                board[j][i].addNeighbour(j - 1, i - 1,Direction.NORTH_WEST);
                            }
                            if (board[i - 1][j] != null) {
                                board[i][j].addNeighbour(i - 1, j,Direction.NORTH);
                                board[j][i].addNeighbour(j, i - 1,Direction.WEST);
                            }
                        }
                        if (j - 1 >= 0 && board[i][j - 1] != null) {
                            board[i][j].addNeighbour(i, j - 1,Direction.WEST);
                            board[j][i].addNeighbour(j - 1, i,Direction.NORTH);
                        }
                        if (i + 1 < size) {
                            if (j + 1 < size && board[i + 1][j + 1] != null) {
                                board[i][j].addNeighbour(i + 1, j + 1,Direction.SOUTH_EAST);
                                board[j][i].addNeighbour(j + 1, i + 1,Direction.SOUTH_EAST);
                            }
                            if (board[i + 1][j] != null) {
                                board[i][j].addNeighbour(i + 1, j,Direction.SOUTH);
                                board[j][i].addNeighbour(j, i + 1, Direction.EAST);
                            }
                        }
                        if (j + 1 < size && board[i][j + 1] != null) {
                            board[i][j].addNeighbour(i, j + 1,Direction.EAST);
                            board[j][i].addNeighbour(j + 1, i,Direction.SOUTH);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     */
    private void setJumps() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    for (Pair<Pair<Integer,Integer>,Direction> neighbour : board[i][j].getNeighbours()){
                        Pair<Integer, Integer> coords = neighbour.getKey();
                        switch (neighbour.getValue()){
                            case NORTH_WEST:
                                if(coords.getKey()> 0 && coords.getValue() > 0) {
                                    if(board[coords.getKey() - 1][coords.getValue() - 1] != null){
                                        board[i][j].addJump(coords.getKey() - 1,coords.getValue() - 1, neighbour.getValue());
                                    }
                                }
                                break;
                            case NORTH:
                                if(coords.getKey() > 0) {
                                    if(board[coords.getKey() - 1][coords.getValue()] != null){
                                        board[i][j].addJump(coords.getKey() - 1, coords.getValue() , neighbour.getValue());
                                    }
                                }
                                break;
                            case WEST:
                                if(coords.getValue() > 0){
                                    if(board[coords.getKey()][coords.getValue() - 1] != null){
                                        board[i][j].addJump(coords.getKey(), coords.getValue() - 1, neighbour.getValue());
                                    }
                                }
                                break;
                            case SOUTH_EAST:
                                if(coords.getKey() < size - 1 && coords.getValue() < size - 1) {
                                    if(board[coords.getKey() + 1][coords.getValue() + 1] != null){
                                        board[i][j].addJump(coords.getKey() + 1, coords.getValue() + 1 , neighbour.getValue());
                                    }
                                }
                                break;
                            case EAST:
                                if(coords.getValue() < size - 1) {
                                    if(board[coords.getKey()][coords.getValue() + 1] != null){
                                        board[i][j].addJump(coords.getKey(), coords.getValue() + 1, neighbour.getValue());
                                    }
                                }
                                break;
                            case SOUTH:
                                if (coords.getKey() < size - 1){
                                    if(board[coords.getKey() + 1][coords.getValue()] != null){
                                        board[i][j].addJump(coords.getKey() + 1, coords.getValue(), neighbour.getValue());
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     *
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

    /**
     *
     */
    private void setMarbles() {
        switch (this.nrPlayers){
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

    public boolean canMove(int prevX, int prevY, int nextX, int nextY, Color color) {
        if (validateCords(nextX, nextY)){
            if (colorMatches(prevX, prevY, color)) {
                if (isInTerritory(prevX, prevY, color)) {
                    if (isInTerritory(nextX, nextY, color)) {
                        if (canMakeMove(prevX, prevY, nextX, nextY)){
                            return true;
                        }
                    }
                }
                else {
                    if (canMakeMove(prevX, prevY, nextX, nextY)){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean canJump(int prevX, int prevY, int nextX, int nextY, Color color) {
        if (validateCords(nextX, nextY)){
            if (colorMatches(prevX, prevY, color)) {
                if (isInTerritory(prevX, prevY, color)) {
                    if (isInTerritory(nextX, nextY, color)) {
                        if (canMakeJump(prevX, prevY, nextX, nextY)){
                            return true;
                        }
                    }
                }
                else {
                    if (canMakeJump(prevX, prevY, nextX, nextY)){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    private boolean validateCords(int x, int y){
        return board[x][y] != null;
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
    private boolean isInTerritory(int x, int y, Color color) {
        return board[x][y].getTerritory() == color.getOpposite();
    }

    /**
     * @param prevX
     * @param prevY
     * @param nextX
     * @param nextY
     * @return
     */
    private boolean canMakeMove(int prevX, int prevY, int nextX, int nextY) {
        if (board[nextX][nextY].getColor() == Color.NONE) {
            Pair<Integer, Integer> coords = new Pair<>(nextX, nextY);
            for (Pair<Pair<Integer,Integer>,Direction> neighbour : board[prevX][prevY].getNeighbours()) {
                if (coords.equals(neighbour.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMakeJump(int prevX, int prevY, int nextX, int nextY) {
        if (board[nextX][nextY].getColor() == Color.NONE) {
            Pair<Integer, Integer> coords = new Pair<>(nextX, nextY);
            for (Pair<Pair<Integer,Integer>,Direction> jump : board[prevX][prevY].getJumps()) {
                if (coords.equals(jump.getKey())) {
                   switch (jump.getValue()){
                       case NORTH_WEST:
                           return board[nextX + 1][nextY + 1].getColor() != Color.NONE;
                       case NORTH:
                           return board[nextX + 1][nextY].getColor() != Color.NONE;
                       case WEST:
                           return board[nextX][nextY + 1].getColor() != Color.NONE;
                       case SOUTH_EAST:
                           return board[nextX - 1][nextY - 1].getColor() != Color.NONE;
                       case SOUTH:
                           return board[nextX - 1][nextY].getColor() != Color.NONE;
                       case EAST:
                           return board[nextX - 1][nextY + 1].getColor() != Color.NONE;
                       default:
                           break;
                   }
                }
            }
        }
        return false;
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
        board[prevX][prevY].setColor(Color.NONE);
        board[nextX][nextY].setColor(color);
    }



    /**
     * @return
     */
    public Marble[][] getBoard() {
        return board;
    }

    public void deleteMarbles(Color color){
        for (int i = 0; i < size; i ++){
            for ( int j = 0; j < size; j++){
                if(board[i][j] != null){
                    if(board[i][j].getColor() == color){
                        board[i][j].setColor(Color.NONE);
                    }
                }
            }
        }

    }

    public boolean isWinner(Color color){
        return true;
    }

    /**
     * @return
     */
    public Color getTurn() {
        return turn;
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }

    public Marble getExtremePoint (Color color) {
        Marble marble = null;
        for (int[] e : EXTREMEPOINTS) {
            if (board[e[0]][e[1]].getColor() == color) {
                marble = board[e[0]][e[1]];
            }
        }
        return marble;
    }
}