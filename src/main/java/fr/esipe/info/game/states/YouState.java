package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionNone;

public class YouState extends State{

    public YouState() {
        super(new ActionNone());
    }

    @Override
    public boolean isYou() {
        return true;
    }

}
