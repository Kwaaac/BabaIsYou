package fr.esipe.info.memento.commands;

import fr.esipe.info.manager.LevelManager;

import java.util.Objects;

public class MoveCommand implements Command{
    private LevelManager levelManager;
    private String direction;

    public MoveCommand(LevelManager levelManager, String direction){
        this.levelManager = Objects.requireNonNull(levelManager);
        this.direction = Objects.requireNonNull(direction);
    }
    @Override
    public String getName() {
        return "Move: " + direction;
    }

    @Override
    public String toString() {
        return "MoveCommand{" +
                "direction='" + direction + '\'' +
                '}';
    }
}
