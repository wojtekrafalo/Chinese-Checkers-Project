package common.model.game;

import java.util.List;

public enum Color {
    AZURE,
    BLUE,
    YELLOW,
    RED,
    PINK,
    GREEN,
    NONE;

    private Color opposite;

    static {
        AZURE.opposite = BLUE;
        PINK.opposite = RED;
        YELLOW.opposite = GREEN;
        BLUE.opposite = AZURE;
        RED.opposite = PINK;
        GREEN.opposite = YELLOW;
    }

    public Color getOpposite() {
        return opposite;
    }

    public static Color randomColor(List<Color> colors){

       return colors.get((int) (Math.random() * colors.size()));
    }
}
