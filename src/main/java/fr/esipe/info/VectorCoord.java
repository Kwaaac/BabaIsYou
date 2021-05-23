package fr.esipe.info;

public class VectorCoord {
    private int xCoord;
    private int yCoord;

    public VectorCoord(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

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

    @Override
    public String toString() {
        return "VectorCoord{" +
                "xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}