package fr.esipe.info.game.enums;

import java.util.Objects;

public enum Legend {
    /**
     * Blank
     */
    BLANK('_', Type.ENTITY, "blank"),
    /**
     * Nouns
     */
    BABA_NOUN('B', Type.NOUN, "baba"),
    FLAG_NOUN('F', Type.NOUN, "flag"),
    WALL_NOUN('W', Type.NOUN, "wall"),
    ROCK_NOUN('R', Type.NOUN, "rock"),
    SKULL_NOUN('K', Type.NOUN, "skull"),

    /**
     * Entity
     */
    BABA_ENTITY('b', Type.ENTITY, "baba"),
    FLAG_ENTITY('f', Type.ENTITY, "flag"),
    WALL_ENTITY('w', Type.ENTITY, "wall"),
    ROCK_ENTITY('r', Type.ENTITY, "rock"),
    SKULL_ENTITY('k', Type.ENTITY, "skull"),

    /**
     * Operator
     */
    IS('s', Type.OPERATOR, "is"),

    /**
     * Property
     */
    YOU('U', Type.PROPERTY, "you"),
    STOP('S', Type.PROPERTY, "stop"),
    PUSH('P', Type.PROPERTY, "push"),
    WIN('!', Type.PROPERTY, "win"),
    DEFEAT('X', Type.PROPERTY, "defeat");

    private final char aChar;
    private final Type type;
    private final String name;

    Legend(char aChar, Type type, String name) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        this.aChar = aChar;
        this.type = type;
        this.name = name;
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
}
