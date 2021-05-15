package fr.esipe.info.game.words;

import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;

import java.util.HashMap;
import java.util.Map;

public class Noun extends AbstractWord implements Word{
    private final Map<EnumOp, EnumProp> properies;
    public Noun(EnumEntity word) {
        super(word.name());
        this.properies = new HashMap<EnumOp, EnumProp>();
    }
}
