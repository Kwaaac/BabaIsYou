package fr.esipe.info.factories;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.words.Word;

public abstract class WordFactory {
    public abstract Word createWord(String name, VectorCoord vectorCoord);

}
