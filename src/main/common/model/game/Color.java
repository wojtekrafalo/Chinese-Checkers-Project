package common.model.game;

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
}
