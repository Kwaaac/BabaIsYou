package fr.esipe.info.game.words;

public class Operator extends AbstractWord implements Word{
    public Operator(EnumOp op) {
        super(op.name());
    }
}
