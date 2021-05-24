package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.manager.GameManager;

import java.util.Objects;

public class GameObject {
    private VectorCoord pos;

    public GameObject(VectorCoord pos) {
        this.pos = Objects.requireNonNull(pos);
    }

    public GameObject(int x, int y) {
        this.pos = new VectorCoord(x, y);
    }

    public VectorCoord getPos() {
        return pos;
    }

    public void addPos(VectorCoord pos) {
        this.pos.addVector(pos);
    }

    public void destroy(){
        GameManager.getInstance().removeGameObject((BoardEntity) this);
        System.out.println("Destroyed: " + this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Objects.equals(pos, that.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }
}
