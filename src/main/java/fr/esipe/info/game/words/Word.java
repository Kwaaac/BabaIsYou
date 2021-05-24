package fr.esipe.info.game.words;

import fr.esipe.info.game.BoardEntity;

public interface Word extends BoardEntity {
    @Override
    default boolean isWord(){
        return true;
    }
}
