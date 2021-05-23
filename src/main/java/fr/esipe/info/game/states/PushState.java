package fr.esipe.info.game.states;

public class PushState implements State{

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isSteppable() {
        return false;
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
