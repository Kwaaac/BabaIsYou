package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionNone;

public class StopState extends State{

    public StopState() {
        super(new ActionNone());
    }

    @Override
    public boolean isStop() {
        return true;
    }
}
