package fr.esipe.info;

import java.util.Objects;

public class VectorCoord {
    private int xCoord;
    private int yCoord;

    public VectorCoord(int xCoord, int yCoord) {
        if (xCoord < -1 || yCoord < -1) {
            throw new IllegalArgumentException("Coordinates cannot be negative");
        }
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public static VectorCoord vectorOutOfTheLoop() {
        return new VectorCoord(-1, -1);
    }

    /*==================================*/
    /*====== MOVEMENT VECTORS ======*/
    /*==================================*/

    public static VectorCoord vectorUP() {
        return new VectorCoord(-1, 0);
    }

    public static VectorCoord vectorDOWN() {
        return new VectorCoord(1, 0);
    }

    public static VectorCoord vectorLEFT() {
        return new VectorCoord(0, -1);
    }

    public static VectorCoord vectorRIGHT() {
        return new VectorCoord(0, 1);
    }

    /*==================================*/
    /*======== Getters & Setters ========*/
    /*==================================*/

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /*===================================*/
    /*======== Operations Methods ========*/
    /*===================================*/

    public void addVector(VectorCoord vc) {
        this.xCoord += vc.getxCoord();
        this.yCoord += vc.getyCoord();
    }

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
}