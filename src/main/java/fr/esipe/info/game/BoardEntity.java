package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.rule.Rules;

import java.awt.*;

/**
 * Interface that reprensent a entity of the board
 */
public interface BoardEntity extends GameObject, Comparable<BoardEntity> {

    /**
     * Getter of the legend of the entity
     *
     * @return Legend
     */
    Legend getLegend();

    /**
     * Change the legend contained in the entity
     *
     * @param newLegend the new legend
     */
    void changeEntity(Legend newLegend);

    /**
     * Check if the entity is a word
     *
     * @return True if the entity is a word, false otherwise
     */
    boolean isWord();

    /**
     * Check if the entity is a noun
     *
     * @return True if the entity is a noun, false otherwise
     */
    boolean isNoun();

    /**
     * Check if the entity is an operator
     *
     * @return True if the entity is a operator, false otherwise
     */
    boolean isOperator();

    /**
     * Check if the entity is a property
     *
     * @return True if the entity is a property, false otherwise
     */
    boolean isProperty();

    /**
     * Execute the action from the entity to an other entity based on it's first state
     *
     * @param entity the entity applying the action
     * @param rules  Rules of the level
     */
    void executeAction(BoardEntity entity, Rules rules);

    /**
     * Execute the action from the entity to an other entity based on the given property
     *
     * @param entity the entity applying the action
     * @param rules  Rules of the level
     * @param prop   the given property
     */
    void executePreciceAction(BoardEntity entity, Rules rules, EnumProp prop);

    /**
     * Clone an Entity
     *
     * @return cloned entity
     */
    Entity clone();

    /**
     * Draw an entity calling the sprite to draw itself
     *
     * @param graphics graphics
     */
    void draw(Graphics2D graphics);

    /**
     * Give the index of the next animations
     */
    void nextAnim();

    /**
     * Change the direction of the Baba is it's you
     *
     * @param dir direction
     */
    void changeDirAnim(VectorCoord dir);

    /**
     * Getter of the number of turn before execute the action.
     *
     * @return the number of turn
     */
    int turnsBeforeActive();

    /**
     * Set the cooldown of the turn
     *
     * @param count number of turns
     */
    void setTurnBeforeActive(int count);
}
