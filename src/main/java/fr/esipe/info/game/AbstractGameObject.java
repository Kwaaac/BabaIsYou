package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;

import java.util.Objects;

public abstract class AbstractGameObject implements GameObject {
    private VectorCoord pos;

    /**
     * Constructor
     *
     * @param pos
     */
    public AbstractGameObject(VectorCoord pos) {
        this.pos = Objects.requireNonNull(pos);
    }

    /**
     * Return the vector of coordinates of the object
     *
     * @return vectorCoord position
     */
    public VectorCoord getPos() {
        return pos;
    }

    /**
     * Set a new position to the object
     *
     * @param pos
     */
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
