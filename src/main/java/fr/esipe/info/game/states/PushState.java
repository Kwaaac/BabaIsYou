package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionNone;

public class PushState extends State {
    public PushState() {
        super(new ActionNone(), EnumProp.PUSH);
    }

    @Override
    public boolean isMovable(){
        return true;
    }
}
