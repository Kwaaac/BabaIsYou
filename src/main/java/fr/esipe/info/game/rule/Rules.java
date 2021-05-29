package fr.esipe.info.game.rule;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;

import java.util.*;

public class Rules {
    private static final Map<Legend, List<State>> states = new HashMap<>();

    /**
     * Check if the given entity every properties
     *
     * @param legend the given entity
     * @param props  the given state
     * @return True is the state is assiociated with the entity, False otherwise
     */
    public static boolean hasProperty(Legend legend, EnumProp... props) {
        List<State> stateList = new ArrayList<>();
        for (var prop : props) {
            stateList.add(prop.getState());
        }

        var entity = states.get(legend);

        if (entity == null) {
            return false;
        }

        return entity.containsAll(stateList);
    }

    public static void displayRules() {
        System.out.println(states);
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
        System.out.println(states);
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
            props.add(new NormalState());
        }
        props.add(prop.getState());

        Collections.sort(props);

        states.put(entity, props);
    }

    /**
     * Clear every states
     */
    public static void clearStates() {
        states.forEach((k, v) -> {
            v.clear();
            v.add(new NormalState());
        });
    }

    public static boolean isWin() {
        return states.keySet().stream().anyMatch(entity -> hasProperty(entity, EnumProp.WIN));
    }

    public static boolean isDefeat() {
        return states.keySet().stream().anyMatch(entity -> hasProperty(entity, EnumProp.DEFEAT));
    }
}
