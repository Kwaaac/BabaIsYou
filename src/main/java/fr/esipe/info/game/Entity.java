package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.enums.Type;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.game.states.State;
import fr.esipe.info.manager.GameManager;

import java.awt.*;
import java.util.Objects;

public class Entity extends AbstractGameObject implements BoardEntity {
    private Legend entity;
    private Sprite[][] sprite;

    private int direction = 0;
    private int anim = 0;

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

    @Override
    public void changeEntity(Legend newLegend) {
        entity = newLegend;
        updateSprite();
    }


    public Entity(Entity target) {
        super(target.getPos().clone());
        this.entity = target.entity;
        this.sprite = target.sprite;
    }

    @Override
    public Entity clone() {
        return new Entity(this);
    }

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

    @Override
    public Legend getLegend() {
        return entity;
    }

    @Override
    public boolean isNoun() {
        return entity.getType() == Type.NOUN;
    }

    @Override
    public boolean isOperator() {
        return entity.getType() == Type.OPERATOR;
    }

    @Override
    public boolean isProperty() {
        return entity.getType() == Type.PROPERTY;
    }

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

    @Override
    public void draw(Graphics2D graphics) {
        sprite[direction][anim].draw(graphics, getPos().getxCoord(), getPos().getyCoord());
    }

    public void nextAnim() {
        anim = (anim + 1) % 3;
    }

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
    public boolean isWord() {
        return entity.getType() != Type.ENTITY;
    }


    @Override
    public String toString() {
        return "Entity{" +
                entity +
                '}';
    }

    @Override
    public String printCommandLineEntity() {
        var res = entity.getName().toUpperCase();

        if (res.length() == 2) {
            res = " " + res + " ";
        } else if (res.length() == 3) {
            res += " ";
        }

        return entity.getTextColor() + res + ColorPrint.ANSI_RESET.getAsciiCode();
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
