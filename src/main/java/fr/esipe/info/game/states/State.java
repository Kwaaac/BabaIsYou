package fr.esipe.info.game.states;

import fr.esipe.info.game.strategies.ActionStrategy;

import java.util.Objects;

public abstract class State {
    private ActionStrategy actionStrategy;

    public State(ActionStrategy actionStrategy){
        Objects.requireNonNull(actionStrategy);
        this.actionStrategy = actionStrategy;
    }

    public ActionStrategy getActionStrategy() {
        return actionStrategy;
    }

    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public boolean isSteppable(){
        return !this.isMovable() && !this.isStop();
    }

    public boolean isMovable(){
        return false;
    }
    public boolean isSink(){
        return false;
    }
    public boolean isStop(){
        return false;
    }
    public boolean isWin(){
        return false;
    }
    public boolean isYou(){
        return false;
    }
    public boolean isDefeat(){
        return false;
    }
    public boolean isHot(){
        return false;
    }
    public boolean isMelt(){
        return false;
    }

}
