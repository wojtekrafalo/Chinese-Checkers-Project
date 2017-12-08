package common.model.game;

/**
 * Class responsible for creating and playing the game. It contains game board
 */
public class Game {

    private Marble[][] board;

    private Color turn;

    private static int nrPlayers;

    private final static int widths[] = {1,2,3,4,13,12,11,10,9,10,11,12,13,4,3,2,1};

    private final static int offsets[] = {4,4,4,4,0,1,2,3,4,4,4,4,4,9,10,11,12};

    Game(int nrPlayers,int size){
        this.board = new Marble[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(j < offsets[i] || j > offsets[i] + widths[i] ){
                    board[i][j] = null;
                }
                else{
                    board[i][j] = new Marble(i, j, Color.NONE, Color.NONE);
                }
            }
        }
    }

    public void makeMove(int prevX, int prevY, int nextX, int nextY){

    }

    public Marble[][] getBoard() {
        return board;
    }
}
