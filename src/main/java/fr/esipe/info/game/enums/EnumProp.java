package fr.esipe.info.game.enums;

import fr.esipe.info.game.states.*;

/**
 * Enumeration of the property
 */
public enum EnumProp {
    /**
     * None property :  when an entity has no propriety
     */
    NONE(0),
    /**
     * You property : Entity that you play
     */
    YOU(11),
    /**
     * Win property : Winnable entity when stepped on
     */
    WIN(5),
    /**
     * STOP property : Bloc other entity to step on it
     */
    STOP(9),
    /**
     * PUSH property : Allow other entity to push it
     */
    PUSH(10),
    /**
     * Melt property : Is destroyed when encounter hot entity
     */
    MELT(7),
    /**
     * Hot property : Destroy melt entity when encountered
     */
    HOT(7),
    /**
     * Defeat property : Destroy You entity when encountered
     */
    DEFEAT(6),
    /**
     * Fire property : after 3 turns, the entity is destroyed
     */
    FIRE(1),
    /**
     * Sink property : When encounter any entity, destroy both
     */
    SINK(8);

    private final int weight;

    /**
     * Constructor
     *
     * @param weight weight of the property to compare it
     */
    EnumProp(int weight) {
        this.weight = weight;
    }

    /**
     * Getter of the weight
     *
     * @return the weight of the property
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Switch case that transform the property to it's state
     *
     * @return The property associated state
     */
    public State getState() {
        return switch (this) {
            case YOU -> new YouState();
            case SINK -> new SinkState();
            case MELT -> new MeltState();
            case HOT -> new HotState();
            case WIN -> new WinState();
            case DEFEAT -> new DefeatState();
            case PUSH -> new PushState();
            case STOP -> new StopState();
            case FIRE -> new FireState();
            default -> new NormalState();
        };
    }
}
