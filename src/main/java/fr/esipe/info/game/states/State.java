package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionStrategy;

import java.util.Objects;

public abstract class State {
    private final EnumProp prop;
    private final ActionStrategy actionStrategy;

    public State(ActionStrategy actionStrategy, EnumProp prop){
        this.actionStrategy = Objects.requireNonNull(actionStrategy);
        this.prop = Objects.requireNonNull(prop);
    }

    public ActionStrategy getActionStrategy() {
        return actionStrategy;
    }

    public EnumProp getProp() {
        return prop;
    }

    public boolean isSteppable(){
        return !this.isMovable();
    }

    public boolean isMovable(){
        return false;
    }
}
