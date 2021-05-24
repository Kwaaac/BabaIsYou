package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.enums.Type;
import fr.esipe.info.game.states.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entity extends AbstractGameObject implements BoardEntity {
    private Legend entity;
    private final List<State> states;

    public Entity(Legend enumEntity, VectorCoord vc) {
        super(Objects.requireNonNull(vc));
        this.entity = Objects.requireNonNull(enumEntity);
        this.states = new LinkedList<>();
    }

    public Legend getNoun() {
        return entity;
    }

    @Override
    public boolean hasProperty(EnumProp prop){
        return this.states.stream().anyMatch(state -> state.getProp().equals(prop));
    }

    public void changeNoun(Legend noun) {
        this.entity = noun;
    }
    public void addState(EnumProp enumProp){
        switch (enumProp){
            case SINK -> this.states.add(new SinkState());
            case MELT -> this.states.add(new MeltState());
            case HOT -> this.states.add(new HotState());
            case DEFEAT -> this.states.add(new DefeatState());
            case PUSH -> this.states.add(new PushState());
            case STOP -> this.states.add(new StopState());
        }
    }

    public void removeState(EnumProp enumProp){
        this.states.removeIf(state -> state.getProp().equals(enumProp));
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
