package fr.esipe.info.game.words;

import fr.esipe.info.game.enums.EnumProp;

public class Property extends AbstractWord {
    public Property(EnumProp prop) {
        super(prop.name());
    }
}
