package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;

import java.util.Objects;

public abstract class AbstractGameObject implements GameObject {
    private VectorCoord pos;


    public AbstractGameObject(VectorCoord pos) {
        this.pos = Objects.requireNonNull(pos);
    }

    public AbstractGameObject(int x, int y) {
        this.pos = new VectorCoord(x, y);
    }

    public VectorCoord getPos() {
        return pos;
    }

    public void addPos(VectorCoord pos) {
        this.pos.addVector(pos);
    }

    public void setPos(VectorCoord pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractGameObject that = (AbstractGameObject) o;
        return Objects.equals(pos, that.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }


}
