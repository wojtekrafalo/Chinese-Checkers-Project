package common.model.game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Marble class with color of marble, territory on that is placed, marble neighbours, possible jumps from that marble and coordinates
 */
public class Marble {
    /**
     * x coordinate of the marble.
     */
    private int x;

    /**
     * y coordinate of the marble.
     */
    private int y;

    /**
     * color of the marble
     */
    private Color color;

    /**
     * territory which the marble belongs to
     */
    private Color territory;

    /**
     * List of the marble neighbours
     */
    private List<Pair<Pair<Integer,Integer>,Direction>> neighbours;

    /**
     * List of possible jumps from the marble
     */
    private List<Pair<Pair<Integer,Integer>,Direction>> jumps;

    /**
     * Constructor of Marble class
     * @param x x coord
     * @param y y coord
     * @param color color of marble
     * @param territory territory
     */
    Marble(int x, int y, Color color, Color territory){
        this.setX(x);
        this.setY(y);
        this.setColor(color);
        this.setTerritory(territory);
        this.neighbours = new ArrayList<>();
        this.jumps = new ArrayList<>();
    }


    /**
     * Getter for x coord
     * @return x coord
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y coord
     * @return y coord
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for marble color
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for marble territory
     * @return territory
     */
    public Color getTerritory() {
        return territory;
    }

    /**
     * Setter for marble color
     * @param color color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Setter for marble x coord
     * @param x x coord
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for marble y coord
     * @param y y coord
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setter for marble territory
     * @param territory territory
     */
    public void setTerritory(Color territory) {
        this.territory = territory;
    }

    /**
     * Getter for marble neighbours
     * @return list
     */
    public List<Pair<Pair<Integer,Integer>,Direction>> getNeighbours() {
        return neighbours;
    }

    /**
     * Getter for marble possible jumps
     * @return list
     */
    public List<Pair<Pair<Integer, Integer>, Direction>> getJumps() {
        return jumps;
    }

    /**
     * @param x x coord of neighbour
     * @param y y coord of neighbour
     * @param direction Direction of neighbour
     */
    public void addNeighbour(int x, int y, Direction direction) {
        this.neighbours.add(new Pair<>(new Pair<>(x, y),direction));
    }

    /**
     * @param x x coord of jump
     * @param y y coord of jump
     * @param direction Direction of jump
     */
    public void addJump(int x, int y, Direction direction) {
        this.jumps.add(new Pair<>(new Pair<>(x, y),direction));
    }
}
