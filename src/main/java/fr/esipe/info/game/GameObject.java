package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;

/**
 * Interface that reprensent an Object being inside the window
 */
public interface GameObject {
    /**
     * Return the vector of coordinates of the object
     *
     * @return vectorCoord position
     */
    VectorCoord getPos();

    /**
     * Set a new position to the object
     *
     * @param pos new position
     */
    void setPos(VectorCoord pos);
}
