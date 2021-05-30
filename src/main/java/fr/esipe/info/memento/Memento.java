package fr.esipe.info.memento;

import fr.esipe.info.game.Board;
import fr.esipe.info.manager.LevelManager;

import java.util.Objects;

/**
 * The memento class stores the past state of the levelManager
 * */
public class Memento {
    private Board backupBoard;
    private LevelManager levelManager;

    public Memento(LevelManager levelManager){
        this.levelManager = Objects.requireNonNull(levelManager);
        this.backupBoard = Objects.requireNonNull(levelManager.backup());
    }

    /**
     *  At some point, a previous state of levelManager can be restored using a memento object.
     */
    public void restore(){
        System.out.println("************************** RESTORE **************************");
        System.out.println(this.backupBoard.getPlayerIsYou());
        this.levelManager.restore(this.backupBoard);
    }

    public Board getBackupBoard() {
        return backupBoard;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "backupBoard=" + backupBoard +
                '}';
    }
}
