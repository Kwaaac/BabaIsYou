package fr.esipe.info.game.states;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.strategies.ActionStrategy;

import java.util.Objects;

/**
 * Class that associate a property to an action to execute
 */
public abstract class State implements Comparable<State> {
    private final EnumProp prop;
    private final ActionStrategy actionStrategy;

    /**
     * Constructor
     *
     * @param actionStrategy Action of the state
     * @param prop           The property of the state
     */
    public State(ActionStrategy actionStrategy, EnumProp prop) {
        this.actionStrategy = Objects.requireNonNull(actionStrategy);
        this.prop = Objects.requireNonNull(prop);
    }


    /**
     * Getter for the action
     *
     * @return The action of the state
     */
    public ActionStrategy getActionStrategy() {
        return actionStrategy;
    }

    /**
     * Getter for the property of the state
     *
     * @return The property of the state
     */
    public EnumProp getProp() {
        return prop;
    }

    /**
     * Is the property allow entities to step over it
     *
     * @return True is an entity can step on the property, false otherwise
     */
    public boolean isSteppable() {
        return !this.isMovable();
    }

    /**
     * Is the property allow entities to step over it
     *
     * @return True is an entity can step on the property, false otherwise
     */
    public boolean isMovable() {
        return false;
    }

    @Override
    public int compareTo(State state) {
        return state.prop.getWeight() - this.prop.getWeight();
    }

    @Override
    public String toString() {
        return prop.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return prop == state.prop;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prop);
    }
}
