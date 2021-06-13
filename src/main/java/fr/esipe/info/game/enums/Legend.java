package fr.esipe.info.game.enums;

import java.awt.*;
import java.util.Objects;

/**
 * Enumeration of an entity which can be placed on a board
 */
public enum Legend {
    /*
     * Blank
     */
    /**
     * blank or null entity, represent an empty entity
     */
    BLANK('_', Type.ENTITY, "BLANK", new Color(1f, 1f, 1f), "/Sprites/PLACEHOLDER/placeholder_"),
    /*
     * Nouns
     */
    /**
     * Represent the noun Baba
     */
    BABA_NOUN('B', Type.NOUN, "BABA", new Color(193, 5, 79), "/Sprites/BABA/noun/text_baba_"),
    /**
     * Represent the noun Flag
     */
    FLAG_NOUN('F', Type.NOUN, "FLAG", new Color(255, 240, 0), "/Sprites/FLAG/noun/text_flag_"),
    /**
     * Represent the noun WALL
     */
    WALL_NOUN('W', Type.NOUN, "WALL", new Color(78, 76, 64), "/Sprites/WALL/noun/text_wall_"),
    /**
     * Represent the noun ROCK
     */
    ROCK_NOUN('R', Type.NOUN, "ROCK", new Color(134, 81, 41), "/Sprites/ROCK/noun/text_rock_"),
    /**
     * Represent the noun SKULL
     */
    SKULL_NOUN('K', Type.NOUN, "SKULL", new Color(183, 57, 24), "/Sprites/SKULL/noun/text_skull_"),
    /**
     * Represent the noun LAVA
     */
    LAVA_NOUN('L', Type.NOUN, "LAVA", new Color(241, 98, 35, 232), "/Sprites/LAVA/noun/text_lava_"),
    /**
     * Represent the noun WATER
     */
    WATER_NOUN('T', Type.NOUN, "WATER", new Color(17, 159, 236), "/Sprites/WATER/noun/text_water_"),

    /*
     * Entity
     */
    /**
     * Represent the Entity BABA
     */
    BABA_ENTITY('b', Type.ENTITY, "BABA", Color.WHITE, "/Sprites/BABA/entity/baba_"),
    /**
     * Represent the Entity FLAG
     */
    FLAG_ENTITY('f', Type.ENTITY, "FLAG", new Color(255, 240, 0), "/Sprites/FLAG/entity/flag_"),
    /**
     * Represent the Entity WALL
     */
    WALL_ENTITY('w', Type.ENTITY, "WALL", new Color(42, 46, 76, 255), "/Sprites/WALL/entity/wall_"),
    /**
     * Represent the Entity ROCK
     */
    ROCK_ENTITY('r', Type.ENTITY, "ROCK", new Color(177, 150, 15), "/Sprites/ROCK/entity/rock_"),
    /**
     * Represent the Entity SKULL
     */
    SKULL_ENTITY('k', Type.ENTITY, "SKULL", new Color(183, 57, 24), "/Sprites/SKULL/entity/skull_"),
    /**
     * Represent the Entity WATER
     */
    WATER_ENTITY('t', Type.ENTITY, "WATER", new Color(17, 159, 236), "/Sprites/WATER/entity/water_"),
    /**
     * Represent the Entity LAVA
     */
    LAVA_ENTITY('l', Type.ENTITY, "LAVA", new Color(227, 143, 12), "/Sprites/LAVA/entity/lava_"),

    /*
     * Operator
     */
    /**
     * Represent the operator IS
     */
    IS('s', Type.OPERATOR, "IS", Color.WHITE, "/Sprites/OPERATOR/IS/text_is_"),

    /*
     * Property
     */
    /**
     * Represent the property YOU
     */
    YOU('U', Type.PROPERTY, "YOU", new Color(193, 5, 79), "/Sprites/PROPERTY/YOU/text_you_"),
    /**
     * Represent the property STOP
     */
    STOP('S', Type.PROPERTY, "STOP", new Color(20, 90, 50), "/Sprites/PROPERTY/STOP/text_stop_"),

    /**
     * Represent the property PUSH
     */
    PUSH('P', Type.PROPERTY, "PUSH", new Color(134, 81, 41), "/Sprites/PROPERTY/PUSH/text_push_"),
    /**
     * Represent the property WIN
     */
    WIN('!', Type.PROPERTY, "WIN", new Color(255, 240, 0), "/Sprites/PROPERTY/WIN/text_win_"),
    /**
     * Represent the property DEFEAT
     */
    DEFEAT('X', Type.PROPERTY, "DEFEAT", new Color(183, 57, 24), "/Sprites/PROPERTY/DEFEAT/text_defeat_"),
    /**
     * Represent the property SINK
     */
    SINK('N', Type.PROPERTY, "SINK", new Color(17, 159, 236), "/Sprites/PROPERTY/SINK/text_sink_"),
    /**
     * Represent the property MELT
     */
    MELT('M', Type.PROPERTY, "MELT", new Color(8, 114, 141), "/Sprites/PROPERTY/MELT/text_melt_"),

    /**
     * Represent the property HOT
     */
    HOT('H', Type.PROPERTY, "HOT", new Color(205, 68, 12), "/Sprites/PROPERTY/HOT/text_hot_"),
    /**
     * Represent the property FIRE
     */
    FIRE('E', Type.PROPERTY, "FIRE", new Color(205, 68, 12), "/Sprites/PROPERTY/FIRE/text_fire_");

    private final char aChar;
    private final Type type;
    private final String name;
    private final Color graphicColor;
    private final String imagePath;

    /**
     * Legend constructor
     *
     * @param aChar        Char that represent the legend in a text level file
     * @param type         Type of the legend
     * @param name         Simple name of the legend
     * @param graphicColor Color of the legend to be painted in the graphics
     * @param imagePath    Path to the image corresponding to the legend
     */
    Legend(char aChar, Type type, String name, Color graphicColor, String imagePath) {
        Objects.requireNonNull(graphicColor);
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        Objects.requireNonNull(imagePath);
        this.aChar = aChar;
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
        this.graphicColor = graphicColor;
    }

    /**
     * Getter of the char version of the legend
     *
     * @return the char version of the legend
     */
    public char getaChar() {
        return aChar;
    }

    /**
     * Getter of the type of the legend
     *
     * @return the type of the legend
     */
    public Type getType() {
        return type;
    }

    /**
     * getter of the name of the legend
     *
     * @return the name of the legend
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Entity version of a legend
     *
     * @return the entity version of this legend
     */
    public Legend getEntity() {
        return Legend.valueOf(name + "_ENTITY");
    }

    /**
     * Get the Entity version of a legend based on the given name
     *
     * @param name the name of the entity
     * @return the entity version of the given name legend
     */
    public static Legend getEntity(String name) {
        return Legend.valueOf(name + "_ENTITY");
    }

    /**
     * Get the path of the image of this legend
     *
     * @return the path of the image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Getter of the color of the legend
     *
     * @return the color of the legend
     */
    public Color getGraphicColor() {
        return graphicColor;
    }
}
