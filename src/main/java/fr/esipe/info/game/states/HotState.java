package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionHot;

public class HotState extends State {
    public HotState() {
        super(new ActionHot(), EnumProp.HOT);
    }
}
