package fr.esipe.info.game;

import fr.esipe.info.game.enums.EnumProp;

public interface BoardEntity extends GameObject, Comparable<Entity> {
    String printCommandLineEntity();

    boolean hasProperty(EnumProp prop);

    boolean isWord();

    boolean isNoun();

    boolean isOperator();

    boolean isProperty();
}
