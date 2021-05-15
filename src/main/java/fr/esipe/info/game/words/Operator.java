package fr.esipe.info.game.words;

import fr.esipe.info.game.enums.EnumOp;

public class Operator extends AbstractWord implements Word{
    public Operator(EnumOp op) {
        super(op.name());
    }
}
