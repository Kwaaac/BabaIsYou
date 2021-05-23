package fr.esipe.info.factories;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.words.Noun;
import fr.esipe.info.game.words.Word;

import java.util.Objects;

public class NounFactory extends WordFactory {
    @Override
    public Word createWord(String name, VectorCoord vc) {
        Objects.requireNonNull(name);
        return new Noun(EnumEntity.valueOf(name.toUpperCase()), vc);
    }
}
