package fr.esipe.info.game.enums;

import java.io.InputStream;
import java.util.Objects;

/**
 * Enum which save all path to each level file
 */
public enum Level {
    LEVEL_00("/levels/level00.txt"),
    LEVEL_01("resources/levels/level01.txt"),
    LEVEL_02("resources/levels/level02.txt"),
    LEVEL_03("resources/levels/level03.txt"),
    LEVEL_04("resources/levels/level04.txt"),
    LEVEL_05("resources/levels/level05.txt");


    private String fileName;

    Level(String fileName) {
        Objects.requireNonNull(fileName);
        this.fileName = fileName;
    }

    public InputStream getFileStream() {
        return Level.class.getResourceAsStream(fileName);
    }
}
