package fr.esipe.info.game.words;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumProp;

public class Property extends AbstractWord {
    public Property(EnumProp prop, VectorCoord vc) {
        super(prop.name(), vc);
    }

    String textColor() {
        return ColorPrint.ANSI_BLUE_BACKGROUND.getAsciiCode();
    }
}
