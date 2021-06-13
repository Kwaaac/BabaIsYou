package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionWin;

/**
 * Representation of the Win state
 */
public class WinState extends State {
    public WinState() {
        super(new ActionWin(), EnumProp.WIN);
    }
}
