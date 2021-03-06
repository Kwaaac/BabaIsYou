package fr.esipe.info.memento;

import fr.esipe.info.game.Board;
import fr.esipe.info.manager.LevelManager;

import java.util.Objects;

/**
 * The memento class stores the past state of the levelManager
 */
public class Memento {
    private final Board backupBoard;
    private final LevelManager levelManager;

    /**
     * Constructor
     *
     * @param levelManager Level that is saved
     */
    public Memento(LevelManager levelManager) {
        this.levelManager = Objects.requireNonNull(levelManager);
        this.backupBoard = Objects.requireNonNull(levelManager.backup());
    }

    /**
     * At some point, a previous state of levelManager can be restored using a memento object.
     */
    public void restore() {
        this.levelManager.restore(this.backupBoard);
    }

    @Override
    public String toString() {
        return "Memento{" +
                "backupBoard=" + backupBoard +
                '}';
    }
}
