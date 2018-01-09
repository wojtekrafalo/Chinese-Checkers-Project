package common.model.game;

import common.model.game.Color;
import common.model.game.Game;
import common.model.game.Marble;
import server.Session;

import java.util.ArrayList;
import java.awt.Point;

import static java.awt.geom.Point2D.distance;


public class Boot extends Thread{
    private Game game;
    private Color color;
    private String nick;
    private Session session;
    private Marble[] listOfMarbles = new Marble[10];
    private double[][] distance = new double[10][6];

    public Boot (Session session, Color color, String nick) {
        this.session = session;
        this.game = session.getGame();
        this.color = color;
        this.nick = nick;
//        for (int i=0; i<10; i++) System.out.println((listOfMarbles[i]!=null) + " " + listOfMarbles[i].getX() + " " + listOfMarbles[i].getY());
    }

    public Boot (Game game, Color color) {
        this.game = game;
        this.color = color;
//        for (int i=0; i<10; i++) System.out.println((listOfMarbles[i]!=null) + " " + listOfMarbles[i].getX() + " " + listOfMarbles[i].getY());
    }

    public Boot() {
    }

    public void run() {
        while (true) {
            if (session.getTurn() == color){
                game = session.getGame();
                makeMove();
            }
            else try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        while (i < 10) {
            marble = listOfMarbles[i];
            helpPrevX = marble.getX();
            helpPrevY = marble.getY();

            if (game.canMove(helpPrevX,
                    helpPrevY,
                    helpPrevX + tabMove[whichMove[i]][0],
                    helpPrevY + tabMove[whichMove[i]][1],
                    color))
                game.makeMove(helpPrevX,
                        helpPrevY,
                        helpPrevX + tabMove[whichMove[i]][0],
                        helpPrevY + tabMove[whichMove[i]][1],
                        color);
            isMadeMove = (game.getBoard()[helpPrevX][helpPrevY].getColor() == Color.NONE && game.getBoard()[helpPrevX + tabMove[whichMove[i]][0]][helpPrevY + tabMove[whichMove[i]][1]].getColor() != Color.NONE);
            i++;
            if (isMadeMove) break;
        }
        if (!isMadeMove) {
            i=0;
            while (i < 10) {
                marble = listOfMarbles[i];
                helpPrevX = marble.getX();
                helpPrevY = marble.getY();

                for (int j=0; j<6; j++) {
                    if (game.canMove(helpPrevX,
                            helpPrevY,
                            helpPrevX + tabMove[j][0],
                            helpPrevY + tabMove[j][1],
                            color))
                    game.makeMove(helpPrevX,
                            helpPrevY,
                            helpPrevX + tabMove[j][0],
                            helpPrevY + tabMove[j][1],
                            color);
                    isMadeMove = (game.getBoard()[helpPrevX][helpPrevY].getColor() == Color.NONE && game.getBoard()[helpPrevX + tabMove[j][0]][helpPrevY + tabMove[j][1]].getColor() != Color.NONE);
                    if (isMadeMove) break;
                }

                i++;
                if (isMadeMove) break;
            }
        }
        return isMadeMove;
    }

    public Game getGame () {
        return game;
    }
    public Color getColor () {
        return color;
    }

    public String getNick() {
        return nick;
    }
}