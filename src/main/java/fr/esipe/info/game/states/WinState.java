package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionWin;

/**
 * Representation of the Win state
 */
public class WinState extends State {
    /**
     * Constructor
     */
    public WinState() {
        super(new ActionWin(), EnumProp.WIN);
    }
}
