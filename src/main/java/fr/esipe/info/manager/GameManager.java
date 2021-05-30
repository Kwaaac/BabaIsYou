package fr.esipe.info.manager;

import fr.esipe.info.game.BoardEntity;

public class GameManager {
    /**
     * Be declared volatile so that double check lock would work correctly
     */
    private static volatile GameManager instance;
    private LevelManager levelManager;
    private int width;
    private int height;

    private int cellSize;

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

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void removeGameObject(BoardEntity boardEntity) {
        if (boardEntity != null) {
            levelManager.removeEntity(boardEntity);
        }
    }
}
