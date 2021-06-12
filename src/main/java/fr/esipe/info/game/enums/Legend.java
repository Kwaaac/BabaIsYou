package fr.esipe.info.game.enums;

import java.awt.*;
import java.util.Objects;

public enum Legend {
    /**
     * Blank
     */
    BLANK('_', Type.ENTITY, "BLANK", ColorPrint.ANSI_RESET, new Color(1f, 1f, 1f), -1, "/Sprites/PLACEHOLDER/placeholder_"),
    /**
     * Nouns
     */
    BABA_NOUN('B', Type.NOUN, "BABA", ColorPrint.ANSI_GREEN, new Color(193, 5, 79), 1, "/Sprites/BABA/noun/text_baba_"),
    FLAG_NOUN('F', Type.NOUN, "FLAG", ColorPrint.ANSI_YELLOW, new Color(255, 240, 0), 1, "/Sprites/FLAG/noun/text_flag_"),
    WALL_NOUN('W', Type.NOUN, "WALL", ColorPrint.ANSI_GREEN, new Color(78, 76, 64), 1, "/Sprites/WALL/noun/text_wall_"),
    ROCK_NOUN('R', Type.NOUN, "ROCK", ColorPrint.ANSI_GREEN, new Color(134, 81, 41), 1, "/Sprites/ROCK/noun/text_rock_"),
    SKULL_NOUN('K', Type.NOUN, "SKULL", ColorPrint.ANSI_GREEN, new Color(183, 57, 24), 1, "/Sprites/SKULL/noun/text_skull_"),
    LAVA_NOUN('L', Type.NOUN, "LAVA", ColorPrint.ANSI_GREEN, new Color(241, 98, 35, 232), 1, "/Sprites/LAVA/noun/text_lava_"),
    WATER_NOUN('T', Type.NOUN, "WATER", ColorPrint.ANSI_BLUE, new Color(17, 159, 236), 1, "/Sprites/WATER/noun/text_water_"),

    /**
     * Entity
     */
    BABA_ENTITY('b', Type.ENTITY, "BABA", ColorPrint.ANSI_RED, Color.WHITE, 1, "/Sprites/BABA/entity/baba_"),
    FLAG_ENTITY('f', Type.ENTITY, "FLAG", ColorPrint.ANSI_WHITE, new Color(255, 240, 0), 0, "/Sprites/FLAG/entity/flag_"),
    WALL_ENTITY('w', Type.ENTITY, "WALL", ColorPrint.ANSI_WHITE, new Color(42, 46, 76, 255), 0, "/Sprites/WALL/entity/wall_"),
    ROCK_ENTITY('r', Type.ENTITY, "ROCK", ColorPrint.ANSI_WHITE, new Color(177, 150, 15), 0, "/Sprites/ROCK/entity/rock_"),
    SKULL_ENTITY('k', Type.ENTITY, "SKULL", ColorPrint.ANSI_WHITE, new Color(183, 57, 24), 0, "/Sprites/SKULL/entity/skull_"),
    WATER_ENTITY('t', Type.ENTITY, "WATER", ColorPrint.ANSI_BLUE, new Color(17, 159, 236), 0, "/Sprites/WATER/entity/water_"),
    LAVA_ENTITY('l', Type.ENTITY, "LAVA", ColorPrint.ANSI_GREEN, new Color(227, 143, 12), 1, "/Sprites/LAVA/entity/lava_"),

    /**
     * Operator
     */
    IS('s', Type.OPERATOR, "IS", ColorPrint.ANSI_CYAN_BACKGROUND, Color.WHITE, 1, "/Sprites/OPERATOR/IS/text_is_"),

    /**
     * Property
     */
    YOU('U', Type.PROPERTY, "YOU", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(193, 5, 79), 1, "/Sprites/PROPERTY/YOU/text_you_"),
    STOP('S', Type.PROPERTY, "STOP", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(20, 90, 50), 1, "/Sprites/PROPERTY/STOP/text_stop_"),
    PUSH('P', Type.PROPERTY, "PUSH", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(134, 81, 41), 1, "/Sprites/PROPERTY/PUSH/text_push_"),
    WIN('!', Type.PROPERTY, "WIN", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(255, 240, 0), 1, "/Sprites/PROPERTY/WIN/text_win_"),
    DEFEAT('X', Type.PROPERTY, "DEFEAT", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(183, 57, 24), 1, "/Sprites/PROPERTY/DEFEAT/text_defeat_"),
    SINK('N', Type.PROPERTY, "SINK", ColorPrint.ANSI_BLUE_BACKGROUND, new Color(17, 159, 236), 1, "/Sprites/PROPERTY/SINK/text_sink_"),
    MELT('M', Type.PROPERTY, "MELT", ColorPrint.ANSI_BLUE_BACKGROUND, new Color(8, 114, 141), 1, "/Sprites/PROPERTY/MELT/text_melt_"),
    HOT('H', Type.PROPERTY, "HOT", ColorPrint.ANSI_BLUE_BACKGROUND, new Color(205, 68, 12), 1, "/Sprites/PROPERTY/HOT/text_hot_");

    private final char aChar;
    private final Type type;
    private final String name;
    private final ColorPrint textColor;
    private final Color graphicColor;
    private final int weight;
    private final String imagePath;

    Legend(char aChar, Type type, String name, ColorPrint textColor, Color graphicColor, int weight, String imagePath) {
        Objects.requireNonNull(graphicColor);
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        Objects.requireNonNull(textColor);
        Objects.requireNonNull(imagePath);
        this.aChar = aChar;
        this.type = type;
        this.name = name;
        this.textColor = textColor;
        this.weight = weight;
        this.imagePath = imagePath;
        this.graphicColor = graphicColor;
    }

    public char getaChar() {
        return aChar;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getTextColor() {
        return textColor.getAsciiCode();
    }

    public int getWeight() {
        return weight;
    }

    public Legend getEntity() {
        return Legend.valueOf(name + "_ENTITY");
    }

    public static Legend getEntity(String name) {
        return Legend.valueOf(name + "_ENTITY");
    }

    public static Legend getLegendFromString(String name) {
        for (var property : EnumProp.values()) {
            if (property.name().equals(name)) {
                return Legend.valueOf(name + "_PROPERTY");
            }
        }

        for (var property : EnumProp.values()) {
            if (property.name().equals(name)) {
                return Legend.valueOf(name + "_ENTITY");
            }
        }

        throw new IllegalArgumentException("No such Enum");
    }

    public String getImagePath() {
        return imagePath;
    }

    public Color getGraphicColor() {
        return graphicColor;
    }
}
