package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionNone;

/**
 * Representation of the You state
 */
public class YouState extends State {
    public YouState() {
        super(new ActionNone(), EnumProp.YOU);
    }

    @Override
    public boolean isSteppable() {
        return false;
    }

    @Override
    public boolean isMovable(){
        return true;
    }
}
