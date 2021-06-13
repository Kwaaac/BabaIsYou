package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionFire;

/**
 * Representation of the Fire state
 */
public class FireState extends State {
    /**
     * Constructor
     */
    public FireState() {
        super(new ActionFire(), EnumProp.FIRE);
    }
}
