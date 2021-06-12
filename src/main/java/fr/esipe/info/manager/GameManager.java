package fr.esipe.info.manager;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;

public class GameManager {
    /**
     * Be declared volatile so that double check lock would work correctly
     */
    private static volatile GameManager instance;
    private LevelManager levelManager;
    private int width;
    private int height;

    private int cellSize;

    private Rules gameRules = new Rules();

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

    public void setRules(Rules rules) {
        this.gameRules = rules;
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

    public int getHeightDelta() {
        return (height - levelManager.getNumberCellHeight() * cellSize) / 2;
    }

    public int getWidthDelta() {
        return (width - levelManager.getNumberCellWidth() * cellSize) / 2;
    }

    public Rules getCheatRules() {
        return gameRules;
    }

    public void removeGameObject(BoardEntity boardEntity) {
        if (boardEntity != null) {
            levelManager.removeEntity(boardEntity);
        }
    }
}
