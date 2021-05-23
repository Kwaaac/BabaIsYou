package fr.esipe.info.game.states;

public class StopState implements State{

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isSteppable() {
        return false;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
