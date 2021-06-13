package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionHot;

/**
 * Representation of the hot state
 */
public class HotState extends State {
    /**
     * Constructor
     */
    public HotState() {
        super(new ActionHot(), EnumProp.HOT);
    }
}
