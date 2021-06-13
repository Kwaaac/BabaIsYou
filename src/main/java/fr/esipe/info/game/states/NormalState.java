package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionNone;

/**
 * Representation of the Normal state
 */
public class NormalState extends State {
    /**
     * Constructor
     */
    public NormalState() {
        super(new ActionNone(), EnumProp.NONE);
    }
}
