package fr.esipe.info.game.words;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumOp;

public class Operator extends AbstractWord implements Word {
    public Operator(EnumOp op, VectorCoord vc) {
        super(op.name() ,vc);
    }


    String textColor() {
        return ColorPrint.ANSI_YELLOW.getAsciiCode();
    }
}
