package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionSink;

public class SinkState extends State{
    public SinkState() {
        super(new ActionSink(), EnumProp.SINK);
    }
}
