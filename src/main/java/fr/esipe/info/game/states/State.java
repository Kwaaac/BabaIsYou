package fr.esipe.info.game.states;

public interface State {
    boolean isPushable();
    boolean isSteppable();
    boolean isMovable();
}
