package fr.esipe.info.game.words;

import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumProp;

public class Property extends AbstractWord {
    public Property(EnumProp prop) {
        super(prop.name());
    }

    String textColor() {
        return ColorPrint.ANSI_BLUE_BACKGROUND.getAsciiCode();
    }
}
