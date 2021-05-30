package fr.esipe.info.game.enums;

import java.awt.*;
import java.util.Objects;

public enum Legend {
    /**
     * Blank
     */
    BLANK('_', Type.ENTITY, "BLANK", ColorPrint.ANSI_RESET, new Color(1f, 1f, 1f), -1, ""),
    /**
     * Nouns
     */
    BABA_NOUN('B', Type.NOUN, "BABA", ColorPrint.ANSI_GREEN, new Color(193, 5, 79), 1, "/Sprites/BABA/noun/text_baba_0_1.png"),
    FLAG_NOUN('F', Type.NOUN, "FLAG", ColorPrint.ANSI_YELLOW, new Color(255, 240, 0), 1, "/Sprites/FLAG/noun/text_flag_0_1.png"),
    WALL_NOUN('W', Type.NOUN, "WALL", ColorPrint.ANSI_GREEN, new Color(78, 76, 64), 1, "/Sprites/WALL/noun/text_wall_0_1.png"),
    ROCK_NOUN('R', Type.NOUN, "ROCK", ColorPrint.ANSI_GREEN, new Color(134, 81, 41), 1, "/Sprites/ROCK/noun/text_rock_0_1.png"),
    SKULL_NOUN('K', Type.NOUN, "SKULL", ColorPrint.ANSI_GREEN, new Color(183, 57, 24), 1, "/Sprites/SKULL/noun/text_skull_0_1.png"),

    /**
     * Entity
     */
    BABA_ENTITY('b', Type.ENTITY, "BABA", ColorPrint.ANSI_RED, Color.WHITE, 1, "/Sprites/BABA/entity/baba_0_1.png"),
    FLAG_ENTITY('f', Type.ENTITY, "FLAG", ColorPrint.ANSI_WHITE, new Color(255, 240, 0), 0, "/Sprites/FLAG/entity/flag_0_1.png"),
    WALL_ENTITY('w', Type.ENTITY, "WALL", ColorPrint.ANSI_WHITE, new Color(42, 46, 76, 255), 0, "/Sprites/WALL/entity/wall_0_1.png"),
    ROCK_ENTITY('r', Type.ENTITY, "ROCK", ColorPrint.ANSI_WHITE, new Color(177, 150, 15), 0, "/Sprites/ROCK/entity/rock_0_1.png"),
    SKULL_ENTITY('k', Type.ENTITY, "SKULL", ColorPrint.ANSI_WHITE, new Color(183, 57, 24), 0, "/Sprites/SKULL/entity/skull_24_1.png"),

    /**
     * Operator
     */
    IS('s', Type.OPERATOR, "IS", ColorPrint.ANSI_CYAN_BACKGROUND, Color.WHITE, 1, "/Sprites/OPERATOR/IS/text_is_0_1.png"),

    /**
     * Property
     */
    YOU('U', Type.PROPERTY, "YOU", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(193, 5, 79), 1, "/Sprites/PROPERTY/YOU/text_you_0_1.png"),
    STOP('S', Type.PROPERTY, "STOP", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(20, 90, 50), 1, "/Sprites/PROPERTY/STOP/text_stop_0_1.png"),
    PUSH('P', Type.PROPERTY, "PUSH", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(134, 81, 41), 1, "/Sprites/PROPERTY/PUSH/text_push_0_1.png"),
    WIN('!', Type.PROPERTY, "WIN", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(255, 240, 0), 1, "/Sprites/PROPERTY/WIN/text_win_0_1.png"),
    DEFEAT('X', Type.PROPERTY, "DEFEAT", ColorPrint.ANSI_YELLOW_BACKGROUND, new Color(183, 57, 24), 1, "/Sprites/PROPERTY/DEFEAT/text_defeat_0_1.png");

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

    public String getImageStream() {
        return imagePath;
    }

    public Color getGraphicColor() {
        return graphicColor;
    }
}
