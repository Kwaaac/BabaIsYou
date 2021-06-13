package fr.esipe.info.memento.commands;

import fr.esipe.info.manager.LevelManager;

import java.util.Objects;


public class MoveCommand {
    private LevelManager levelManager;
    private String direction;

    /**
     * Constructor
     *
     * @param levelManager actual level
     * @param direction    direction of the move
     */
    public MoveCommand(LevelManager levelManager, String direction) {
        this.levelManager = Objects.requireNonNull(levelManager);
        this.direction = Objects.requireNonNull(direction);
    }

    @Override
    public String toString() {
        return "MoveCommand{" +
                "direction='" + direction + '\'' +
                '}';
    }
}
