package fr.esipe.info.game.rule;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;

import java.util.*;

public class Rules {
    private final Map<Legend, List<State>> states;

    private Rules(Map<Legend, List<State>> states) {
        this.states = states;
    }

    public Rules() {
        this(new HashMap<>());
    }

    /**
     * Check if the given entity every properties
     *
     * @param legend the given entity
     * @param props  the given state
     * @return True is the state is assiociated with the entity, False otherwise
     */
    public boolean hasProperty(Legend legend, EnumProp... props) {
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

    public void displayRules() {
        System.out.println(states);
    }

    public Rules clone() {
        var newMap = new HashMap<Legend, List<State>>();
        return new Rules(new HashMap<>(states));
    }

    public List<State> getStates(BoardEntity entity) {
        return states.getOrDefault(entity.getLegend(), new ArrayList<>());
    }

    /**
     * Return the first state or the given entity
     *
     * @param entity the given entity
     * @return The first state of the entity or NormalState if there is none.
     */
    public State getFirstState(Legend entity) {
        System.out.println(states);
        return states.getOrDefault(entity, new ArrayList<>()).stream().findFirst().orElse(new NormalState());
    }

    public boolean isMovable(BoardEntity entity) {
        return states.getOrDefault(entity.getLegend(), new ArrayList<>()).stream().anyMatch(State::isMovable);
    }

    /**
     * Add a state to an entity
     *
     * @param entity An entity's noun
     * @param prop   an new propriety
     */
    public void add(Legend entity, EnumProp prop) {
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
    public void clearStates() {
        states.forEach((k, v) -> {
            v.clear();
            v.add(new NormalState());
        });
    }

    public boolean isWin() {
        return states.keySet().stream().anyMatch(entity -> hasProperty(entity, EnumProp.WIN, EnumProp.YOU));
    }

    public boolean isDefeat() {
        return states.keySet().stream().anyMatch(entity -> hasProperty(entity, EnumProp.DEFEAT, EnumProp.YOU));
    }


    @Override
    public String toString() {
        return "Rules{" +
                "states=" + states +
                '}';
    }
}
