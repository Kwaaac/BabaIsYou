package fr.esipe.info.game.enums;

import java.util.Objects;

public enum Legend {
    /**
     * Blank
     */
    BLANK('_', Type.ENTITY, "BLANK", ColorPrint.ANSI_RESET, -1),
    /**
     * Nouns
     */
    BABA_NOUN('B', Type.NOUN, "BABA", ColorPrint.ANSI_GREEN, 1),
    FLAG_NOUN('F', Type.NOUN, "FLAG", ColorPrint.ANSI_YELLOW, 1),
    WALL_NOUN('W', Type.NOUN, "WALL", ColorPrint.ANSI_GREEN, 1),
    ROCK_NOUN('R', Type.NOUN, "ROCK", ColorPrint.ANSI_GREEN, 1),
    SKULL_NOUN('K', Type.NOUN, "SKULL", ColorPrint.ANSI_GREEN, 1),

    /**
     * Entity
     */
    BABA_ENTITY('b', Type.ENTITY, "BABA", ColorPrint.ANSI_RED, 1),
    FLAG_ENTITY('f', Type.ENTITY, "FLAG", ColorPrint.ANSI_WHITE, 0),
    WALL_ENTITY('w', Type.ENTITY, "WALL", ColorPrint.ANSI_WHITE, 0),
    ROCK_ENTITY('r', Type.ENTITY, "ROCK", ColorPrint.ANSI_WHITE, 0),
    SKULL_ENTITY('k', Type.ENTITY, "SKULL", ColorPrint.ANSI_WHITE, 0),

    /**
     * Operator
     */
    IS('s', Type.OPERATOR, "IS", ColorPrint.ANSI_CYAN_BACKGROUND, 1),

    /**
     * Property
     */
    YOU('U', Type.PROPERTY, "YOU", ColorPrint.ANSI_YELLOW_BACKGROUND, 1),
    STOP('S', Type.PROPERTY, "STOP", ColorPrint.ANSI_YELLOW_BACKGROUND, 1),
    PUSH('P', Type.PROPERTY, "PUSH", ColorPrint.ANSI_YELLOW_BACKGROUND, 1),
    WIN('!', Type.PROPERTY, "WIN", ColorPrint.ANSI_YELLOW_BACKGROUND, 1),
    DEFEAT('X', Type.PROPERTY, "DEFEAT", ColorPrint.ANSI_YELLOW_BACKGROUND, 1);

    private final char aChar;
    private final Type type;
    private final String name;
    private final ColorPrint textColor;
    private final int weight;

    Legend(char aChar, Type type, String name, ColorPrint textColor, int weight) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        Objects.requireNonNull(textColor);
        this.aChar = aChar;
        this.type = type;
        this.name = name;
        this.textColor = textColor;
        this.weight = weight;
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
}
