package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.enums.Type;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.game.states.State;

import java.awt.*;
import java.util.List;
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

        updateSprite();
    }

    @Override
    public void changeEntity(Legend newLegend) {
        entity = newLegend;
        updateSprite();
    }

    private void updateSprite() {
        for (int i = 0; i < sprite.length; i++) {
            for (int j = 1; j <= sprite[0].length; j++) {
                System.out.println(this.entity.getImagePath() + i + "_" + j + ".png");
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
    public void executeAction(BoardEntity to) {
        if (to.getLegend().equals(Legend.BLANK)) {
            return;
        }
        State state = Rules.getFirstState(to.getLegend());
        state.getActionStrategy().execute(this, to);
    }

    @Override
    public boolean isSteppable() {
        return Rules.isSteppable(entity);
    }

    @Override
    public boolean isMovable() {
        return Rules.isMovable(entity);
    }

    @Override
    public List<State> getStates() {
        return Rules.getStates(entity);
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
    public int compareTo(Entity o) {
        return Integer.compare(entity.getWeight(), o.entity.getWeight());
    }
}
