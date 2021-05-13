package fr.esipe.info.game.words;

import fr.esipe.info.game.EnumEntity;

import java.util.HashMap;
import java.util.Map;

public class Name extends AbstractWord implements Word{
    private final Map<EnumOp, EnumProp> properies;
    public Name(EnumEntity word) {
        super(word.name());
        this.properies = new HashMap<EnumOp, EnumProp>();
    }
}
