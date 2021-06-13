package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionDefeat;

/**
 * Representation of the Defeat state
 */
public class DefeatState extends State {
    public DefeatState() {
        super(new ActionDefeat(), EnumProp.DEFEAT);
    }
}
