package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.enums.Type;
import fr.esipe.info.game.states.State;

import java.util.List;
import java.util.Objects;

public class Entity extends AbstractGameObject implements BoardEntity {
    private Legend entity;

    public Entity(Legend enumEntity, VectorCoord vc) {
        super(Objects.requireNonNull(vc));
        this.entity = Objects.requireNonNull(enumEntity);
    }

    @Override
    public Legend getLegend() {
        return entity;
    }

    public void changeNoun(Legend noun) {
        this.entity = noun;
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
