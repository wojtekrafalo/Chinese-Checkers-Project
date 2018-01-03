package common.model.game;

import java.util.ArrayList;
import java.awt.Point;

import static com.sun.javafx.geom.Vec2d.distance;

public class Boot {
    private Game game;
    private Color color;
    private Marble[] listOfMarbles = new Marble[10];
    private double[][] distance = new double[10][6];

    public Boot (Game game, Color color) {
        this.game = game;
        this.color = color;
//        for (int i=0; i<10; i++) System.out.println((listOfMarbles[i]!=null) + " " + listOfMarbles[i].getX() + " " + listOfMarbles[i].getY());
    }

    public Boot() {
    }

    public boolean makeMove () {
        Point help1;
        int[][] tabMove =
                {{1,0},{1,1},{0,1},{-1,0},{-1,-1},{0,-1}};

        Marble[][] board = game.getBoard();
        int help=0;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getColor() == this.color) {
                        listOfMarbles[help] = board[i][j];
                        help++;
                    }
                }
            }
        }

        Marble extremeMarble = game.getExtremePoint(listOfMarbles[0].getColor().getOpposite());
        Point help2 = new Point(extremeMarble.getX(), extremeMarble.getY());

        for (int i=0; i<10; i++) {
            Marble marble = listOfMarbles[i];
            for (int j=0; j<6; j++) {
                help1 = new Point(marble.getX() + tabMove[j][0], marble.getY() + tabMove[j][1]);
                if (    help1.getX()>=0 &&
                        help1.getY()>=0 &&
                        help1.getX()<=16 &&
                        help1.getY()<=16 &&
                        game.getBoard()[(int)help1.getX()][(int)help1.getY()] != null) {
                    distance[i][j] = distance(help1.getX(), help1.getY(), help2.getX(), help2.getY());
                }
            }
        }

        double helpMinDistance;
        int whichMove[] = new int[10];
        int helpMove = 0;

        for (int i=0; i<listOfMarbles.length; i++) {
            helpMinDistance = Double.MAX_VALUE;
            for (int j=0; j<distance[0].length; j++) {
                if (distance[i][j] < helpMinDistance && distance[i][j] > 0) {
                    helpMinDistance = distance[i][j];
                    helpMove = j;
                }
            }
            whichMove[i] = helpMove;
        }
                                                                        //now, for all Boot's Marbles, we have most profitable move
        boolean isMadeMove = false;
        int i=0, helpPrevX, helpPrevY;
        Marble marble;
        while (!isMadeMove && i < 10) {
            marble = listOfMarbles[i];
            helpPrevX = marble.getX();
            helpPrevY = marble.getY();

            game.makeMove(helpPrevX,
                          helpPrevY,
                    helpPrevX + tabMove[whichMove[i]][0],
                    helpPrevY + tabMove[whichMove[i]][1],
                          color);
            isMadeMove = (game.getBoard()[helpPrevX][helpPrevY].getColor() == Color.NONE && game.getBoard()[helpPrevX + tabMove[whichMove[i]][0]][helpPrevY + tabMove[whichMove[i]][1]].getColor() != Color.NONE);
            i++;
        }

        for (int k=0;k<17;k++) {
            for (int l=0;l<17;l++) {
                if (game.getBoard()[k][l] != null)
                System.out.print(game.getBoard()[k][l].getColor() + " ");
                else System.out.print("null ");
            }
            System.out.println("");
        }

        return isMadeMove;
    }
}