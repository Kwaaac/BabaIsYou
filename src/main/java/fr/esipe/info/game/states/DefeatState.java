package fr.esipe.info.game.states;

public class DefeatState implements State{
    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isSteppable() {
        return true;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
