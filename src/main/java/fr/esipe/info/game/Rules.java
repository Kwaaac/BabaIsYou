package fr.esipe.info.game;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {
    private static final Map<Legend, List<State>> states = new HashMap<>();

    /**
     * Check if the given entity has the given state
     *
     * @param legend the given entity
     * @param prop   the given state
     * @return True is the state is assiociated with the entity, False otherwise
     */
    public static boolean hasState(Legend legend, EnumProp prop) {
        return states.get(legend).contains(prop.getState());
    }

    public static List<State> getStates(Legend entity) {
        return states.getOrDefault(entity, new ArrayList<>());
    }

    /**
     * Return the first state or the given entity
     *
     * @param entity the given entity
     * @return The first state of the entity or NormalState if there is none.
     */
    public static State getFirstState(Legend entity) {
        return states.getOrDefault(entity, new ArrayList<>()).stream().findFirst().orElse(new NormalState());
    }

    public static boolean isSteppable(Legend entity) {
        return states.getOrDefault(entity, new ArrayList<>()).stream().anyMatch(State::isSteppable);
    }

    public static boolean isMovable(Legend entity) {
        return states.getOrDefault(entity, new ArrayList<>()).stream().anyMatch(State::isMovable);
    }

    /**
     * Add a state to an entity
     *
     * @param entity An entity's noun
     * @param prop   an new propriety
     */
    public static void add(Legend entity, EnumProp prop) {
        var props = states.get(entity);

        if (props == null) {
            props = new ArrayList<>();
        }
        props.add(prop.getState());
        states.put(entity, props);
    }

    /**
     * Clear every states
     */
    public static void clearStates() {
        states.forEach((k, v) -> v.clear());
    }
}
