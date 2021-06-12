package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionFire;

public class FireState extends State {
    public FireState() {
        super(new ActionFire(), EnumProp.FIRE);
    }
}
