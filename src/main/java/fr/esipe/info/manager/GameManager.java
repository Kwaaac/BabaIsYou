package fr.esipe.info.manager;

import fr.esipe.info.game.BoardEntity;

public class GameManager {
    /**
     * Be declared volatile so that double check lock would work correctly
     */
    private static volatile GameManager instance;
    private LevelManager levelManager;

    private GameManager() {

    }

    public static GameManager getInstance() {
        GameManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (GameManager.class) {
            if (instance == null) {
                instance = new GameManager();
            }
            return instance;
        }
    }


    public static void removeGameObject(BoardEntity boardEntity) {
        if(boardEntity != null){
            LevelManager.removeEntity(boardEntity);
        }
    }
}
