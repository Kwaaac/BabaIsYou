package fr.esipe.info.memento.commands;

import java.util.Objects;


/**
 * Class that represent the direction taken by the player to save it
 */
public class MoveCommand {
    private final String direction;

    /**
     * Constructor
     *
     * @param direction direction of the move
     */
    public MoveCommand(String direction) {
        this.direction = Objects.requireNonNull(direction);
    }

    @Override
    public String toString() {
        return "MoveCommand{" +
                "direction='" + direction + '\'' +
                '}';
    }
}
