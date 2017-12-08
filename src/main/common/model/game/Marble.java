package common.model.game;

public class Marble {
    private int x;

    private int y;

    private Color color;

    private Color territory;

    Marble(int x, int y, Color color, Color territory){
        this.setX(x);
        this.setY(y);
        this.setColor(color);
        this.setTerritory(territory);
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
}
