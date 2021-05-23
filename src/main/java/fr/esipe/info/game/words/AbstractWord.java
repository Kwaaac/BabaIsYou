package fr.esipe.info.game.words;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.GameObject;
import fr.esipe.info.game.enums.ColorPrint;

public abstract class AbstractWord extends GameObject implements Word {
    private final String word;

    public AbstractWord(String word, VectorCoord vc) {
        super(vc);
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }

    String textColor() {
        return "";
    }

    @Override
    public String printCommandLineEntity() {
        var res = word;

        res = textColor() + res;

        if (word.length() == 2) {
            res = " " + res + " ";
        } else if (word.length() == 3) {
            res += " ";
        }

        return res + ColorPrint.ANSI_RESET.getAsciiCode();
    }
}
