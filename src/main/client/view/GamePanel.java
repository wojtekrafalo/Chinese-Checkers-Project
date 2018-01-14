package client.view;

import common.model.game.Game;
import client.model.LocalSession;
import common.model.game.Marble;

import javax.swing.*;
import java.awt.*;


import static java.awt.geom.Point2D.distance;
import static java.lang.StrictMath.sqrt;

/**
 * This PanelClass is used to draw whole game Component including background and all Marbles
 */
public class GamePanel extends JPanel {
    /**
     * There we have final variables. R is radius of circle at Component and H is actually only for comfortable using height between neighbour circles.
     */
    private static final double R = 16, H = 3*R*sqrt(3)/2;

    /**
     * Table of numbers which you must add to every row of Marbles in board, to get coords in hexagonal board.
     */
    private static final int[] tabAdd = {2, 1, 1, 0, 0, -1, -1, -2, -2, -3, -3, -4, -4, -5, -5, -6, -6};
    /**
     * Size of board. It means length of star
     */
    private static int size = 4;
    private Game game;
    private LocalSession localSession;

    /**
     * @param defWIDTH default WIDTH of Panel
     * @param defHEIGHT default HEIGHT of Panel
     * @param game Game, which would be painted at Panel
     * @param size Size of board
     */
    public GamePanel (int defWIDTH, int defHEIGHT, Game game, int size){
        setBounds(0, 0, defWIDTH, defHEIGHT);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        this.game = game;
        this.size = size;
    }

    public GamePanel (int defWIDTH, int defHEIGHT, LocalSession localSession, int size){
        setBounds(0, 0, defWIDTH, defHEIGHT);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        this.localSession = localSession;
        this.game = localSession.getGame();                                         //jak sie zacznie wysypywac, to skomentowac ta linijke
        this.size = size;
    }

    /**
     * @param g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
//        g2d.fillOval(300-100,300-100,30,30);                //testowy okrag
        draw(g2d);
    }

    /**
     * @param g2d
     */
    private void draw(Graphics2D g2d) {
        for (int i=0; i<game.getBoard().length; i++) {
            for (int j=0; j<game.getBoard().length; j++) {
                Marble marble = game.getBoard()[i][j];
                if (marble != null) {
                    Point point = changeTableCoordsToPanelCoords(marble);
                    g2d.setColor(switchColor(marble));
                    g2d.fillOval((int)(point.getX() - R), (int)(point.getY() - R), (int)R*2, (int)R*2);
                }
            }
        }
    }



    /**
     * Changes Table Coords, which are saved at board table at Game class, to Panel Coords of circles. It returns 2D Point on Panel
     * @param marble marble, which coords you want to change
     */
    private static Point changeTableCoordsToPanelCoords (Marble marble) {
        int y = marble.getY();
        int x = marble.getX() + tabAdd[y];

        x++; y++;
        double Y = (y-1) * H + R;
        double X;

        if (y%2 == 0) X = (3*x + 2.5)*R;
        else X = (3*x + 1)*R;

        if (size%2==1) X=X+R;

        return new Point((int)X, (int)Y);
    }

    /**
     * Changes Panel Coords of Circle to Table Coords of marbles. It returns Point on Table
     * @param point 2D point, which coords you want to change
     */
    private static Point changePanelCoordsToTableCoords (Point point) {
        double X = point.getX();
        double Y = point.getY();
        if (GamePanel.size%2==1) X = X - R;
        Y = (Y - R)/H - 1;

        int y = (int)(Math.round(Y));
        if (y%2 == 0) X = (X/R - 2.5)/3;
        else X = (X/R - 1)/3;
        X--;
        y++;

        return new Point((int)Math.round(X) - tabAdd[y], y);
    }

    /**
     * Changes Enum Color from Game class to awt Color
     * @param marble marble, which Color you want to change
     */
    private static java.awt.Color switchColor (Marble marble) {
        common.model.game.Color color = marble.getColor();
        return (switchColor(color));
    }

    /**
     * Changes Enum Color from Game class to awt Color
     * @param color color, which you want to change
     */
    public static java.awt.Color switchColor (common.model.game.Color color) {
        switch (color) {
            case AZURE: return Color.CYAN;
            case BLUE: return Color.BLUE;
            case YELLOW: return Color.YELLOW;
            case RED: return Color.RED;
            case PINK: return Color.PINK;
            case GREEN: return Color.GREEN;
            case NONE: return Color.GRAY;
            default: return Color.GRAY;
        }
    }

    /**
     * Returns, if 2D point is contained by marble at Panel
     * @param marble
     * @param xPoint x Coord of point
     * @param yPoint y Coord of point
     */
    public static boolean contains (Marble marble, int xPoint, int yPoint) {
        if (marble == null) return false;
        Point panelPoint = changeTableCoordsToPanelCoords(marble);
        return distance((float)panelPoint.getX(), (float)panelPoint.getY(), xPoint, yPoint) <= R;
    }
}