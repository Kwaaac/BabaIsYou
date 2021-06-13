package fr.esipe.info.game.rule;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;

import java.util.*;

/**
 * Class that contains the implementation of the rules of the game
 * <p>
 * The rules are based on a map where the keys are an entity and the value is a set of states
 */
public class Rules {
    private Map<Legend, Set<State>> states = new HashMap<>();


    /**
     * Constructor base on another rule
     *
     * @param target the other Rule instance
     */
    public Rules(Rules target) {
        if (target != null) {
            this.states = target.cloneMap();
        }
    }

    /**
     * Default constructor
     */
    public Rules() {
    }

    /**
     * Clone the map of rules
     *
     * @return A new Cloned map
     */
    private Map<Legend, Set<State>> cloneMap() {
        var newMap = new HashMap<Legend, Set<State>>();
        this.states.keySet().forEach(legend -> newMap.put(legend, cloneSetState(this.states.get(legend))));
        return newMap;
    }

    /**
     * Clone the set of states inside the map
     *
     * @param statesSet The given set to be cloned
     * @return
     */
    private Set<State> cloneSetState(Set<State> statesSet) {
        return new TreeSet<>(statesSet);
    }

    /**
     * Check if the given entity every properties
     *
     * @param legend the given entity
     * @param props  the given state
     * @return True is the state is assiociated with the entity, False otherwise
     */
    private boolean hasProperty(Legend legend, EnumProp props) {
        Objects.requireNonNull(props);

        var entityPropreties = states.getOrDefault(legend, new TreeSet<>());

        var state = props.getState();
        return entityPropreties.contains(state);
    }

    /**
     * Check if the given entity every properties
     *
     * @param entity the given entity
     * @param props  the given state
     * @return True is the state is assiociated with the entity, False otherwise
     */
    public boolean hasProperty(BoardEntity entity, EnumProp props) {
        return hasProperty(entity.getLegend(), props);
    }

    /**
     * Clone this instance of rule
     *
     * @return a new cloned rule
     */
    public Rules clone() {
        return new Rules(this);
    }

    /**
     * Returns the states of the given entity
     *
     * @param entity Given entity to fetch the states
     * @return The set of state of the entity
     */
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
        return states.getOrDefault(entity, new TreeSet<>()).stream().findFirst().orElse(new NormalState());
    }

    /**
     * Is the given entity is movable
     *
     * @param entity The given entity
     * @return True is the entity is movable,  false otherwise
     */
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

        var cheatRules = GameManager.getInstance().getCheatRules();

        cheatRules.states.forEach((entity, properties) -> properties.forEach(property -> add(entity, property.getProp())));
    }

    /**
     * Verify is the given list of you has the win property.
     * Declare the level has won if so.
     *
     * @param playerIsYou List of you entities
     */
    public void isWin(List<BoardEntity> playerIsYou) {
        if (playerIsYou.stream().anyMatch(entity -> hasProperty(entity, EnumProp.WIN))) {
            LevelManager.win();
        }
    }

    /**
     * Verify is the given list of you has the Defeat property.
     * Declare the level has lost if so.
     *
     * @param playerIsYou List of you entities
     */
    public void isDefeat(List<BoardEntity> playerIsYou) {
        if (playerIsYou.stream().anyMatch(entity -> hasProperty(entity, EnumProp.DEFEAT))) {
            LevelManager.lose();
        }
    }

    @Override
    public String toString() {
        return "Rules{" +
                "states=" + states +
                '}';
    }
}
