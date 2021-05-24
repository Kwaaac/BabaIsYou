package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.states.*;
import fr.esipe.info.game.words.Noun;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entity extends GameObject implements BoardEntity {
    private final Noun noun;
    private final List<State> states;

    public Entity(Noun noun, VectorCoord vc) {
        super(Objects.requireNonNull(vc));
        this.noun = Objects.requireNonNull(noun);
        this.states = new LinkedList<>();
    }

    public Entity(Noun noun){
        this(noun, new VectorCoord(0,0));
    }

    public Entity(Entity target) {
        this(target.noun, target.getPos());
    }

    public Entity clone() {
        return new Entity(this);
    }

    public boolean hasProperty(EnumProp prop){
        return this.states.stream().anyMatch(state -> state.getProp().equals(prop));
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
    public boolean usesProperties(EnumOp op, EnumProp prop) {
        return noun.hasProperty(op, prop);
    }

    @Override
    public boolean isWord() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return entity.noun == noun;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noun);
    }

    @Override
    public String toString() {
        return "Entity{" +
                noun +
                '}';
    }

    @Override
    public String printCommandLineEntity() {
        return noun.toString();
    }
}
