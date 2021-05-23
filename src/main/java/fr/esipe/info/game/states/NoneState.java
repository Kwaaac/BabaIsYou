package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionNone;

public class NoneState extends State{
    public NoneState() {
        super(new ActionNone());
    }
}
