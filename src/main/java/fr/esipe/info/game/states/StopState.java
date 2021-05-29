package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionNone;

public class StopState extends State {
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
