package fr.esipe.info.factories;

import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.words.Operator;
import fr.esipe.info.game.words.Word;

import java.util.Objects;

public class OperatorFactory extends WordFactory{
    @Override
    public Word createWord(String name) {
        Objects.requireNonNull(name);
        return new Operator(EnumOp.valueOf(name.toUpperCase()));
    }
}
