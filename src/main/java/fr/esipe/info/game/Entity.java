package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.enums.Type;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;
import fr.esipe.info.manager.GameManager;

import java.awt.*;
import java.util.Objects;
import java.util.Set;

/**
 * Class that reprensent an entity of the board
 */
public class Entity extends AbstractGameObject implements BoardEntity {
    private Legend entity;
    private Sprite[][] sprite;
    private int turnsBeforeActive = -1;

    private int direction = 0;
    private int anim = 0;

    /**
     * Constructor
     *
     * @param enumEntity
     * @param vc
     */
    public Entity(Legend enumEntity, VectorCoord vc) {
        super(Objects.requireNonNull(vc));
        this.entity = Objects.requireNonNull(enumEntity);
        this.sprite = new Sprite[4][3];

        for (int i = 0; i < sprite.length; i++) {
            for (int j = 1; j <= sprite[0].length; j++) {
                sprite[i][j - 1] = new Sprite(enumEntity.getImagePath() + i + "_" + j + ".png", enumEntity.getGraphicColor());
            }
            if (!entity.equals(Legend.BABA_ENTITY)) {
                break;
            }
        }
    }

    /**
     * Secondary constructor using a created entity
     *
     * @param target
     */
    public Entity(Entity target) {
        super(target.getPos().clone());
        this.entity = target.entity;
        this.sprite = target.sprite;
    }

    /**
     * Change the legend contained in the entity
     *
     * @param newLegend the new legend
     */
    @Override
    public void changeEntity(Legend newLegend) {
        entity = newLegend;
        updateSprite();
    }

    /**
     * Getter of the number of turn before execute the action.
     *
     * @return the number of turn
     */
    @Override
    public int turnsBeforeActive() {
        return turnsBeforeActive;
    }

    /**
     * Setter of the number of turn before execute the action.
     *
     * @param count the number of turn
     */
    @Override
    public void setTurnBeforeActive(int count) {
        turnsBeforeActive = count;
    }

    /**
     * Clone an Entity
     *
     * @return cloned entity
     */
    @Override
    public Entity clone() {
        return new Entity(this);
    }

    /**
     * Update the sprite of the entity, intervene when changing legend
     */
    private void updateSprite() {
        for (int i = 0; i < sprite.length; i++) {
            for (int j = 1; j <= sprite[0].length; j++) {
                sprite[i][j - 1] = new Sprite(this.entity.getImagePath() + i + "_" + j + ".png", this.entity.getGraphicColor());
            }
            if (!entity.equals(Legend.BABA_ENTITY)) {
                break;
            }
        }
    }

    /**
     * Getter of the legend of the entity
     *
     * @return Legend
     */
    @Override
    public Legend getLegend() {
        return entity;
    }

    /**
     * Check if the entity is a noun
     *
     * @return True if the entity is a noun, false otherwise
     */
    @Override
    public boolean isNoun() {
        return entity.getType() == Type.NOUN;
    }

    /**
     * Check if the entity is an operator
     *
     * @return True if the entity is a operator, false otherwise
     */
    @Override
    public boolean isOperator() {
        return entity.getType() == Type.OPERATOR;
    }


    /**
     * Check if the entity is a word
     *
     * @return True if the entity is a word, false otherwise
     */
    @Override
    public boolean isWord() {
        return entity.getType() != Type.ENTITY;
    }


    /**
     * Check if the entity is a property
     *
     * @return True if the entity is a property, false otherwise
     */
    @Override
    public boolean isProperty() {
        return entity.getType() == Type.PROPERTY;
    }

    /**
     * Execute the action from the entity to an other entity based on it's first state
     *
     * @param to    the entity applying the action
     * @param rules Rules of the level
     */
    @Override
    public void executeAction(BoardEntity to, Rules rules) {
        if (to == null) {
            return;
        }

        if (to.getLegend().equals(Legend.BLANK)) {
            return;
        }

        State state = rules.getFirstState(to.getLegend());
        state.getActionStrategy().execute(rules, this, to);
    }

    /**
     * Execute the action from the entity to an other entity based on the given property
     *
     * @param to    the entity applying the action
     * @param rules Rules of the level
     * @param prop  the given property
     */
    @Override
    public void executePreciceAction(BoardEntity to, Rules rules, EnumProp prop) {
        if (to == null) {
            return;
        }

        if (to.getLegend().equals(Legend.BLANK)) {
            return;
        }

        Set<State> states = rules.getStates(this);
        State state = prop.getState();

        if (states.contains(state)) {
            state.getActionStrategy().execute(rules, this, to);
        }
    }

    /**
     * Draw an entity calling the sprite to draw itself
     *
     * @param graphics graphics
     */
    @Override
    public void draw(Graphics2D graphics) {
        sprite[direction][anim].draw(graphics, getPos().getxCoord(), getPos().getyCoord());
    }

    /**
     * Give the index of the next animations
     */
    public void nextAnim() {
        anim = (anim + 1) % 3;
    }

    /**
     * Change the direction of the Baba is it's you
     *
     * @param dir direction
     */
    public void changeDirAnim(VectorCoord dir) {
        // For now only the entity BABA has direction animation
        if (entity.equals(Legend.BABA_ENTITY)) {
            if (dir.equals(VectorCoord.vectorDOWN())) {
                direction = 1;
            } else if (dir.equals(VectorCoord.vectorLEFT())) {
                direction = 2;
            } else if (dir.equals(VectorCoord.vectorUP())) {
                direction = 3;
            } else {
                direction = 0;
            }
        }
    }


    @Override
    public String toString() {
        return "Entity{" +
                entity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entity entity1 = (Entity) o;
        return entity == entity1.entity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entity);
    }

    @Override
    public int compareTo(BoardEntity o) {
        var rules = GameManager.getInstance().getLevelManager().getRules();

        var oState = rules.getStates(o).stream().findFirst().orElse(new NormalState()).getProp().getWeight();
        var thisState = rules.getStates(this).stream().findFirst().orElse(new NormalState()).getProp().getWeight();

        return oState - thisState;
    }
}
