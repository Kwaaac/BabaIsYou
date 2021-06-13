package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionNone;

/**
 * Representation of the Stop state
 */
public class StopState extends State {
    /**
     * Constructor
     */
    public StopState() {
        super(new ActionNone(), EnumProp.STOP);
    }

    @Override
    public boolean isSteppable() {
        return false;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
