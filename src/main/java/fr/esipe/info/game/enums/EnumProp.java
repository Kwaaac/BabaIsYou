package fr.esipe.info.game.enums;

public enum EnumProp {
    NONE(0),
    YOU(11),
    WIN(5),
    STOP(9),
    PUSH(10),
    MELT(7),
    HOT(7),
    DEFEAT(6),
    SINK(8);

    private int weight;

    private EnumProp(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
