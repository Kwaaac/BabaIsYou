package fr.esipe.info;

public class VectorCoord {
    private int xCoord;
    private int yCoord;

    public VectorCoord(int xCoord, int yCoord) {
        if (xCoord < 0 || yCoord < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative");
        }
        this.xCoord = xCoord;
        this.yCoord = yCoord;
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

}