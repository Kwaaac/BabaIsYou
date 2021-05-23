package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.states.State;
import fr.esipe.info.game.words.Noun;

import java.util.Objects;

public class Entity implements BoardEntity {
    private static State state;
    private Noun noun;
    private VectorCoord coord;
    private EnumEntity entity;

    public Entity(EnumEntity entity) {
        Objects.requireNonNull(entity);
        this.entity = entity;
        this.coord = new VectorCoord(0,0);
    }

    @Override
    public void changeState(State state){
        this.state = state;
    }

    @Override
    public State getState(){
        return this.state;
    }

    public EnumEntity getEntity(){
        return this.entity;
    }

    public VectorCoord getCoord(){
        return this.coord;
    }

    public void setCoord(VectorCoord coord){
        this.coord.setxCoord(coord.getxCoord());
        this.coord.setyCoord(coord.getyCoord());
    }

    public Noun getNoun() {
        return noun;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return entity.noun == noun;
    }

    @Override
    public String toString() {
        return "Entity{" +
                noun +
                '}';
    }

    @Override
    public String printCommandLineEntity() {
        return entity.toString();
    }

    @Override
    public boolean isWord() {
        return false;
    }
}
