package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionNone;

public class NormalState extends State{
    public NormalState() {
        super(new ActionNone(), EnumProp.NONE);
    }
}
