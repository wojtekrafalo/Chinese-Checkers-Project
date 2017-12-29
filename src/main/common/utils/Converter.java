package common.utils;

import common.model.game.Color;

public class Converter {

    public static Color parseColor(String color) {
        if (color.equalsIgnoreCase("azure")) {
            return Color.AZURE;
        } else if (color.equalsIgnoreCase("blue")) {
            return Color.BLUE;
        } else if (color.equalsIgnoreCase("yellow")) {
            return Color.YELLOW;
        } else if (color.equalsIgnoreCase("red")) {
            return Color.RED;
        } else if (color.equalsIgnoreCase("pink")) {
            return Color.PINK;
        } else if (color.equalsIgnoreCase("green")) {
            return Color.GREEN;
        } else {
            return Color.NONE;
        }
    }
}
