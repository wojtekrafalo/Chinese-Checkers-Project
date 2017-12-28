package client.view;

import common.model.game.Game;
import common.model.game.Marble;

import javax.swing.*;
import java.awt.*;

import static com.sun.javafx.geom.Point2D.distance;
import static java.lang.StrictMath.sqrt;

public class GamePanel extends JPanel {

    /**
     * R - promien of circle
     * H - height between middles of circles
     * tabAdd - table, which shows, how many fields you must add to coords of circles in current row
     */
    private static final double R = 16, H = 3*R*sqrt(3)/2;
    private static final int[] tabAdd = {2, 1, 1, 0, 0, -1, -1, -2, -2, -3, -3, -4, -4, -5, -5, -6, -6};

    private static int size = 4;
    private Game game;

    public GamePanel (int defWIDTH, int defHEIGHT, Game game, int size){
        setBounds(0, 0, defWIDTH, defHEIGHT);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        this.game = game;
        this.size = size;
    }

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

    public static Point changeMarbleCoordsToPanelCoords (int x, int y) {
        x++; y++;
        double Y = (y-1) * H + R;
        double X;

        if (y%2 == 0) X = (3*x + 2.5)*R;
        else X = (3*x + 1)*R;

        if (size%2==1) X=X+R;

        return new Point((int)X, (int)Y);
    }

    public static Point changeTableCoordsToPanelCoords (Marble marble) {                //do poprawy
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

    public static Point changePanelCoordsToTableCoords (Point point) {
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

    private static java.awt.Color switchColor (Marble marble) {
        common.model.game.Color color = marble.getColor();
        return (switchColor(color));
    }

    private static java.awt.Color switchColor (common.model.game.Color color) {
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

    public static boolean contains (Marble marble, int xPoint, int yPoint) {
        if (marble == null) return false;
        Point panelPoint = changeTableCoordsToPanelCoords(marble);
        return distance((float)panelPoint.getX(), (float)panelPoint.getY(), xPoint, yPoint) <= R;
    }
}