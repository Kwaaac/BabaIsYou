package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionWin;

public class WinState extends State {
    public WinState() {
        super(new ActionWin(), EnumProp.WIN);
    }
}
