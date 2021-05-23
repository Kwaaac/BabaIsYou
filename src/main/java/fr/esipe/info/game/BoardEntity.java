package fr.esipe.info.game;

import fr.esipe.info.game.states.State;

public interface BoardEntity {
    String printCommandLineEntity();
    void changeState(State state);
    State getState();
    boolean isWord();
}
