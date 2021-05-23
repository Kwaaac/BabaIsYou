package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionNone;

public class PushState extends State{

    public PushState() {
        super(new ActionNone());
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
