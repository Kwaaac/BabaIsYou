package fr.esipe.info.game.words;

import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Noun extends AbstractWord implements Word {
    private final Map<EnumOp, List<EnumProp>> properties;

    public Noun(EnumEntity word) {
        super(word.name());
        this.properties = new HashMap<>();
    }

    public void addProperty(EnumOp op, EnumProp prop) {
        if (!this.properties.containsKey(op)) {
            this.properties.put(op, new ArrayList<>());
        }
        this.properties.get(op).add(prop);
    }


    String textColor() {
        return ColorPrint.ANSI_RED.getAsciiCode();
    }

    public Map<EnumOp, List<EnumProp>> getProperties() {
        return properties;
    }

}
