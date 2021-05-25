package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.enums.Type;
import fr.esipe.info.game.states.*;

import java.util.Collections;
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
        this.states.add(new SinkState());
        this.states.add(new NormalState());
        Collections.sort(this.states);
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
    public void executeAction(BoardEntity to) {
        State state = this.states.stream().findFirst().orElse(new NormalState());
        if(this.iAmWin()){
            state = new WinState();
        }
        else if(this.iAmDefeat()){
            state = new DefeatState();
        }
        state.getActionStrategy().execute(this,to);
    }

    @Override
    public boolean isSteppable() {
        return this.states.stream().anyMatch(State::isSteppable);
    }

    @Override
    public boolean isMovable() {
        return this.states.stream().anyMatch(State::isMovable);
    }

    @Override
    public List<State> getStates() {
        return this.states;
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

    @Override
    public boolean iAmWin(){
        return this.states.stream().anyMatch(state -> state.getProp().equals(EnumProp.WIN));
    }

    @Override
    public boolean iAmDefeat(){
        return this.states.stream().anyMatch(state -> state.getProp().equals(EnumProp.DEFEAT));
    }
}
