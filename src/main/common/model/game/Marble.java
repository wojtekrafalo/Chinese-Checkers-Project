package common.model.game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Marble {
    private int x;

    private int y;

    private Color color;

    private Color territory;

    private List<Pair<Pair<Integer,Integer>,Direction>> neighbours;

    private List<Pair<Pair<Integer,Integer>,Direction>> jumps;

    Marble(int x, int y, Color color, Color territory){
        this.setX(x);
        this.setY(y);
        this.setColor(color);
        this.setTerritory(territory);
        this.neighbours = new ArrayList<>();
        this.jumps = new ArrayList<>();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public Color getTerritory() {
        return territory;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTerritory(Color territory) {
        this.territory = territory;
    }

    public List<Pair<Pair<Integer,Integer>,Direction>> getNeighbours() {
        return neighbours;
    }

    public List<Pair<Pair<Integer, Integer>, Direction>> getJumps() {
        return jumps;
    }

    public void addNeighbour(int x, int y, Direction direction) {
        this.neighbours.add(new Pair<>(new Pair<>(x, y),direction));
    }

    public void addJump(int x, int y, Direction direction) {
        this.jumps.add(new Pair<>(new Pair<>(x, y),direction));
    }
}
