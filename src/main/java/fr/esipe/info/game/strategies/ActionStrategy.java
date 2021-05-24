package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;

public interface ActionStrategy {
    void execute(BoardEntity from, BoardEntity to);
    default void displayMessage(String message){
        System.out.println(message);
    }
}
