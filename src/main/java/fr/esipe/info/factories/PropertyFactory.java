package fr.esipe.info.factories;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.words.Property;
import fr.esipe.info.game.words.Word;

import java.util.Objects;

public class PropertyFactory extends WordFactory{
    @Override
    public Word createWord(String name) {
        Objects.requireNonNull(name);
        return new Property(EnumProp.valueOf(name.toUpperCase()));
    }
}
