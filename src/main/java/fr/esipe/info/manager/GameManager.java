package fr.esipe.info.manager;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;

/**
 * Class that represent the game and the window itself
 * <p>
 * The class is a singleton as there is only one instance of the game
 */
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

    /**
     * Default constructor
     */
    private GameManager() {
    }

    /**
     * Return the singleton instance of the GameManager
     *
     * @return the game manager instance
     */
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

    /**
     * Set the games rules applied to cheat or debug
     *
     * @param rules Set of given rules
     */
    public void setRules(Rules rules) {
        this.gameRules = rules;
    }

    /**
     * getter of the current level manager
     *
     * @return The current level manager
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Setter of a new level manage
     *
     * @param levelManager New level manager
     */
    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    /**
     * Getter of the width of the window
     *
     * @return The width of the window
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter of the width of the window
     *
     * @param width The width of the window
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter of the height of the window
     *
     * @return The height of the window
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter of the height of the window
     *
     * @param height The height of the window
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter of the size of a cell
     *
     * @return the size of a Cell
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Setter for the size of a cell
     *
     * @param cellSize Size of the cell
     */
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * Return the delta between the board and the limits of the screens for the height
     *
     * @return Return the delta between the board and the limits of the screens
     */
    public int getHeightDelta() {
        return (height - levelManager.getNumberCellHeight() * cellSize) / 2;
    }

    /**
     * Return the delta between the board and the limits of the screens for the width
     *
     * @return Return the delta between the board and the limits of the screens
     */
    public int getWidthDelta() {
        return (width - levelManager.getNumberCellWidth() * cellSize) / 2;
    }

    /**
     * Getter of the cheat rules
     *
     * @return The cheat Rules
     */
    public Rules getCheatRules() {
        return gameRules;
    }

    /**
     * Request the suppression of a specific entity inside the game
     *
     * @param boardEntity The entity to be removed
     */
    public void removeGameObject(BoardEntity boardEntity) {
        if (boardEntity != null) {
            levelManager.removeEntity(boardEntity);
        }
    }
}
