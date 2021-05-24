package fr.esipe.info.game;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.states.State;

import java.util.List;

public interface BoardEntity extends GameObject, Comparable<Entity> {
    String printCommandLineEntity();

    boolean hasProperty(EnumProp prop);

    boolean isWord();

    boolean isNoun();

    boolean isOperator();

    boolean isProperty();

    void executeAllActions(BoardEntity entity);

    boolean isSteppable();

    boolean isMovable();

    List<State> getStates();
}
