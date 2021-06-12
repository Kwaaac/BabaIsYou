package fr.esipe.info;

import java.util.Objects;

/**
 * Class that represent a coordinate
 */
public class VectorCoord implements Comparable<VectorCoord> {
    private int xCoord;
    private int yCoord;

    /**
     * constructor
     *
     * @param xCoord x pos
     * @param yCoord y pos
     */
    public VectorCoord(int xCoord, int yCoord) {
        if (xCoord < -1 || yCoord < -1) {
            throw new IllegalArgumentException("Coordinates cannot be negative");
        }
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Secondary constructor based on a coordinate
     *
     * @param target
     */
    public VectorCoord(VectorCoord target) {
        if (target != null) {
            this.xCoord = target.xCoord;
            this.yCoord = target.yCoord;
        }
    }

    /**
     * Close a vector
     *
     * @return A new cloned vector
     */
    public VectorCoord clone() {
        return new VectorCoord(this);
    }

    /**
     * A Vector that cannot be accessed (Objects out of the bord like Text for example)
     *
     * @return a new vector
     */
    public static VectorCoord vectorOutOfTheLoop() {
        return new VectorCoord(-1, -1);
    }

    /*==================================*/
    /*====== MOVEMENT VECTORS ======*/
    /*==================================*/

    /**
     * Vector that represent the up move
     *
     * @return A new vector with the "up" reprensentation
     */
    public static VectorCoord vectorUP() {
        return new VectorCoord(-1, 0);
    }

    /**
     * Vector that represent the down move
     *
     * @return A new vector with the "up" reprensentation
     */
    public static VectorCoord vectorDOWN() {
        return new VectorCoord(1, 0);
    }

    /**
     * Vector that represent the left move
     *
     * @return A new vector with the "left" reprensentation
     */
    public static VectorCoord vectorLEFT() {
        return new VectorCoord(0, -1);
    }

    /**
     * Vector that represent the right move
     *
     * @return A new vector with the "right" reprensentation
     */
    public static VectorCoord vectorRIGHT() {
        return new VectorCoord(0, 1);
    }

    /*==================================*/
    /*======== Getters & Setters ========*/
    /*==================================*/

    /**
     * getter of the x coordiante
     *
     * @return x
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * getter of the x=y coordiante
     *
     * @return y
     */
    public int getyCoord() {
        return yCoord;
    }

    /*===================================*/
    /*======== Operations Methods ========*/
    /*===================================*/

    /**
     * Add two vectors and return a new vector being the addition between the two
     *
     * @param vc1 first vector
     * @param vc2 second vector
     * @return
     */
    public static VectorCoord addTwoVectors(VectorCoord vc1, VectorCoord vc2) {
        return new VectorCoord(vc1.xCoord + vc2.xCoord, vc1.yCoord + vc2.yCoord);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VectorCoord that = (VectorCoord) o;
        return xCoord == that.xCoord && yCoord == that.yCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }

    @Override
    public String toString() {
        return "VectorCoord{" +
                "xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }

    @Override
    public int compareTo(VectorCoord o) {
        return Integer.compare(xCoord, o.xCoord) - Integer.compare(yCoord, o.yCoord);
    }
}