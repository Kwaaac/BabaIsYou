package fr.esipe.info.manager;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.Board;
import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.main.Main;
import fr.esipe.info.memento.History;
import fr.esipe.info.memento.Memento;
import fr.esipe.info.memento.commands.MoveCommand;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Class that reprensente a level of the game
 * <p>
 * Contains the music to be played and the history of every movements
 */
public class LevelManager {
    private final History history;
    private Board board;

    private Clip music;

    private static boolean win = false;
    private static boolean lose = false;
    private static boolean quit = false;
    private static boolean next = false;

    /**
     * Constructor
     *
     * @param file Path to the level to be played
     * @throws IOException throw IOException if the file is not readable
     */
    public LevelManager(Path file) throws IOException {
        win = false;
        lose = false;
        quit = false;
        next = false;

        this.history = new History();
        this.board = new Board(EncryptionDecorator.readData(file));

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(Main.class.getResourceAsStream("/Music/baba.wav"))));
            this.music = AudioSystem.getClip();
            music.open(audio);
            music.loop(Clip.LOOP_CONTINUOUSLY);
            music.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Return the number of cells for the width
     *
     * @return number of width cell
     */
    public int getNumberCellWidth() {
        return board.getWidth();
    }

    /**
     * Return the number of cells for the height
     *
     * @return number of height cell
     */
    public int getNumberCellHeight() {
        return board.getHeight();
    }

    /**
     * Render the level
     *
     * @param graphics   graphics
     * @param updateAnim True if the animation has to be updated, false otherwise
     */
    public void render(Graphics2D graphics, boolean updateAnim) {
        graphics.clearRect(0, 0, GameManager.getInstance().getWidth(), GameManager.getInstance().getHeight());
        board.displayGraphic(graphics, updateAnim);
    }

    /**
     * Event input handler
     * <p>
     * Render the board if an event has been detected
     *
     * @param context Application context of the game
     * @return True if there is an event, false if an event makes the game to stop
     */
    public boolean processEvent(ApplicationContext context) {
        var event = context.pollEvent();

        if (event == null) {
            return true;
        }

        if (event.getAction().equals(Event.Action.KEY_RELEASED)) {
            switch (event.getKey()) {
                case UP:
                    board.move(VectorCoord.vectorUP());
                    this.pushCommand(new MoveCommand("up"));
                    break;

                case DOWN:
                    board.move(VectorCoord.vectorDOWN());
                    this.pushCommand(new MoveCommand("down"));
                    break;

                case LEFT:
                    board.move(VectorCoord.vectorLEFT());
                    this.pushCommand(new MoveCommand("left"));
                    break;

                case RIGHT:
                    board.move(VectorCoord.vectorRIGHT());
                    this.pushCommand(new MoveCommand("right"));
                    break;

                case UNDEFINED:
                    quit();
                    return false;

                case S:
                    break;

                case M:
                    if (music.isActive()) {
                        music.stop();
                    } else {
                        music.start();
                    }
                    break;

                case SPACE:
                    next();
                    break;

                case Z:
                    this.undo();
                    break;
            }

            context.renderFrame(graphics -> render(graphics, false));
        }

        return true;
    }

    /**
     * Stop the music of the level
     */
    public void stopMusic() {
        music.stop();
    }

    /**
     * Request the suppresion of an entity in the board
     *
     * @param boardEntity The entity to be removed
     */
    public void removeEntity(BoardEntity boardEntity) {
        this.board.removeEntity(boardEntity);
    }

    /**
     * Tell if the level is won
     *
     * @return True is the level is won, false otherwise
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Tell if the player wants to quit
     *
     * @return True is the player quits, false otherwise
     */
    public boolean isQuit() {
        return quit;
    }



    /**
     * Tell if the player wants to pass to the next level
     *
     * @return True is the player pass the level, false otherwise
     */
    public boolean isNext() {
        return next;
    }


    /**
     * Is the Level lost
     *
     * @return True is the level is lost, false otherwise
     */
    public boolean isLose() {
        return lose;
    }

    /**
     * Set the level to lost
     */
    public static void lose() {
        LevelManager.lose = true;
    }

    /**
     * Apply the level to won
     */
    public static void win() {
        LevelManager.win = true;
    }

    /**
     * Apply the game to quitting
     */
    public static void quit() {
        LevelManager.quit = true;
    }

    /**
     * Apply the game to pass the current level
     */
    public static void next() {
        LevelManager.next = true;
    }

    /**
     * Get a clone of the board to make a backup
     *
     * @return A new cloned board
     */
    public Board backup() {
        return this.board.clone();
    }

    /**
     * Restore a backup
     *
     * @param backupBoard The given backup board
     */
    public void restore(Board backupBoard) {
        this.board = backupBoard;
    }

    /**
     * Add a new save of the bord to the history
     *
     * @param command The command the player did
     */
    public void pushCommand(MoveCommand command) {
        this.history.push(command, new Memento(this));
    }

    /**
     * Apply a undo to the game, goes back to the last position.
     */
    public void undo() {
        this.history.undo();
    }

    /**
     * Get the rules of the board
     *
     * @return The rules in the level
     */
    public Rules getRules() {
        return board.getRules();
    }
}
