package fr.esipe.info.game;

import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;

public interface BoardEntity {
    String printCommandLineEntity();

    default boolean usesProperties(EnumOp op, EnumProp prop) {
        return false;
    }

    default void executeAllActions(BoardEntity to){}
    boolean isWord();
}
