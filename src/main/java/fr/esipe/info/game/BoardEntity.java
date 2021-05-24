package fr.esipe.info.game;

public interface BoardEntity extends GameObject, Comparable<Entity> {
    String printCommandLineEntity();

    boolean isWord();

    boolean isNoun();

    boolean isOperator();

    boolean isProperty();
}
