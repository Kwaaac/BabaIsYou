package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionNone;

public class WinState extends State{

    public WinState() {
        super(new ActionNone());
    }

    @Override
    public boolean isWin() {
        return true;
    }

}
