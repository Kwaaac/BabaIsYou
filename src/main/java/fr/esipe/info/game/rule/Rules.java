package fr.esipe.info.game.rule;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Rules {
    private Map<Legend, Set<State>> states;

    private Rules(Map<Legend, Set<State>> states) {
        this.states = states;
    }

    public Rules(Rules target) {
        if (target != null) {
            this.states = target.cloneMap();
        }
    }

    public Rules() {
        this.states = new HashMap<>();
    }

    private Map<Legend, Set<State>> cloneMap() {
        var newMap = new HashMap<Legend, Set<State>>();
        this.states.keySet().forEach(legend -> newMap.put(legend, cloneListState(this.states.get(legend))));
        return newMap;
    }

    private Set<State> cloneListState(Set<State> statesList) {
        var newListState = new TreeSet<State>();
        newListState.addAll(statesList);
        return newListState;
    }

    /**
     * Check if the given entity every properties
     *
     * @param legend the given entity
     * @param props  the given state
     * @return True is the state is assiociated with the entity, False otherwise
     */
    public boolean hasProperty(Legend legend, EnumProp... props) {
        Set<State> stateList = new TreeSet<>();
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
        return new Rules(this);
    }

    public Set<State> getStates(BoardEntity entity) {
        return states.getOrDefault(entity.getLegend(), new TreeSet<>());
    }

    /**
     * Return the first state or the given entity
     *
     * @param entity the given entity
     * @return The first state of the entity or NormalState if there is none.
     */
    public State getFirstState(Legend entity) {
        System.out.println(states);
        return states.getOrDefault(entity, new TreeSet<>()).stream().findFirst().orElse(new NormalState());
    }

    public boolean isMovable(BoardEntity entity) {
        return states.getOrDefault(entity.getLegend(), new TreeSet<>()).stream().anyMatch(State::isMovable);
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
            props = new TreeSet<>();
            props.add(new NormalState());
        }
        props.add(prop.getState());

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
        return states.keySet().stream().anyMatch(entity -> hasProperty(entity, EnumProp.WIN));
    }

    public boolean isDefeat() {
        return states.keySet().stream().anyMatch(entity -> hasProperty(entity, EnumProp.DEFEAT));
    }


    @Override
    public String toString() {
        return "Rules{" +
                "states=" + states +
                '}';
    }
}
