package fr.esipe.info.game.enums;

import fr.esipe.info.game.states.*;

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

    EnumProp(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public State getState() {
        return switch (this) {
            case SINK -> new SinkState();
            case MELT -> new MeltState();
            case HOT -> new HotState();
            case DEFEAT -> new DefeatState();
            case PUSH -> new PushState();
            case STOP -> new StopState();
            default -> new NormalState();
        };
    }
}
