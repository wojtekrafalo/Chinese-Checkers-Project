package common.model.game;

import java.util.List;

/**
 * Enum, responsible for determining the color of a marble/territory.
 */
public enum Color {
    /**
     * Enum const representing azure.
     */
    AZURE,
    /**
     * Enum const representing blue.
     */
    BLUE,
    /**
     * Enum const representing yellow.
     */
    YELLOW,
    /**
     * Enum const representing red.
     */
    RED,
    /**
     * Enum const representing pink.
     */
    PINK,
    /**
     * Enum const representing green.
     */
    GREEN,
    /**
     * Enum const representing blank.
     */
    NONE;

    /**
     * Variable for storing opposite color of given color
     */
    private Color opposite;

    static {
        AZURE.opposite = BLUE;
        PINK.opposite = RED;
        YELLOW.opposite = GREEN;
        BLUE.opposite = AZURE;
        RED.opposite = PINK;
        GREEN.opposite = YELLOW;
    }

    /**
     * Getter for opposite color
     * @return Color
     */
    public Color getOpposite() {
        return opposite;
    }

    /**
     * @param colors List of colors which from we want to get random color
     * @return random color
     */
    public static Color randomColor(List<Color> colors){

       return colors.get((int) (Math.random() * colors.size()));
    }
}
