package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionMelt;

public class MeltState extends State{
    public MeltState() {
        super(new ActionMelt());
    }

    @Override
    public boolean isMelt() {
        return true;
    }
}
