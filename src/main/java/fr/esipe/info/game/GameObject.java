package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;

public class GameObject {
    private VectorCoord pos;

    public GameObject(VectorCoord pos) {
        this.pos = pos;
    }

    public GameObject(int x, int y) {
        this.pos = new VectorCoord(x, y);
    }
}
