package fr.esipe.info.manager;

public class GameManager {
    /**
     * Be declared volatile so that double check lock would work correctly
     */
    private static volatile GameManager instance;
    private LevelManager levelManager;

    private GameManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public static GameManager getInstance(LevelManager levelManager) {
        GameManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (GameManager.class) {
            if (instance == null) {
                instance = new GameManager(levelManager);
            }
            return instance;
        }
    }
}
