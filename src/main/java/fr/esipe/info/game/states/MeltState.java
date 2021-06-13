package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionMelt;

/**
 * Representation of the Melt state
 */
public class MeltState extends State {
    /**
     * Constructor
     */
    public MeltState() {
        super(new ActionMelt(), EnumProp.MELT);
    }
}
