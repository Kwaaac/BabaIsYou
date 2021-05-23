package fr.esipe.info.game.words;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Noun extends AbstractWord implements Word {
    private static final Map<EnumOp, List<EnumProp>> properties = new HashMap<>();

    public Noun(EnumEntity word, VectorCoord vc) {
        super(word.name(), vc);
    }

    public void addProperty(EnumOp op, EnumProp prop) {
        if (!properties.containsKey(op)) {
            properties.put(op, new ArrayList<>());
        }
        properties.get(op).add(prop);
    }

    public boolean hasProperty(EnumOp op, EnumProp prop) {
        var props = properties.get(op);

        if (props == null) {
            return false;
        }

        return props.contains(prop);
    }


    String textColor() {
        return ColorPrint.ANSI_RED.getAsciiCode();
    }

    public Map<EnumOp, List<EnumProp>> getProperties() {
        return properties;
    }

}
