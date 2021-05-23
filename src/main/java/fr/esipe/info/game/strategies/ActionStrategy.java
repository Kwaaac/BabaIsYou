package fr.esipe.info.game.strategies;

import fr.esipe.info.game.Entity;

public interface ActionStrategy {
    void execute(Entity from, Entity to);
    default void displayMessage(String message){
        System.out.println(message);
    }
}
