package common.model.game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Marble {
    private int x;

    private int y;

    private Color color;

    private Color territory;

    private List<Pair<Integer, Integer>> neighbours;

    Marble(int x, int y, Color color, Color territory){
        this.setX(x);
        this.setY(y);
        this.setColor(color);
        this.setTerritory(territory);
        this.neighbours = new ArrayList<>();
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

    public List<Pair<Integer, Integer>> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(int x, int y) {
        this.neighbours.add(new Pair<>(x, y));
    }
}
