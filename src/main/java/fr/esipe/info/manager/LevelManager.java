package fr.esipe.info.manager;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.Board;
import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.main.Main;
import fr.esipe.info.memento.History;
import fr.esipe.info.memento.Memento;
import fr.esipe.info.memento.commands.Command;
import fr.esipe.info.memento.commands.MoveCommand;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

public class LevelManager {
    private final History history;
    private String levelName;
    private Board board;
    private final EncryptionDecorator encoded;

    private Clip music;

    private static boolean win = false;
    private static boolean lose = false;

    public LevelManager(String levelName, EncryptionDecorator encoded, String musicPath) {
        this.levelName = levelName;
        this.encoded = encoded;
        this.history = new History();
        this.board = new Board(encoded.readData());

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(Main.class.getResourceAsStream(musicPath))));
            this.music = AudioSystem.getClip();
            music.open(audio);
            music.loop(Clip.LOOP_CONTINUOUSLY);
            music.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

    }

    public int getNumberCellWidth() {
        return board.getWidth();
    }

    public int getNumberCellHeight() {
        return board.getHeight();
    }

    public void displayBoard() {
        System.out.println(board);
    }

    public void render(Graphics2D graphics, boolean updateAnim) {
        graphics.clearRect(0, 0, GameManager.getInstance().getWidth(), GameManager.getInstance().getHeight());
        board.displayGraphic(graphics, updateAnim);
    }

    public boolean processEvent(ApplicationContext context) {
        var event = context.pollEvent();

        if (event == null) {
            return true;
        }

        if (event.getAction().equals(Event.Action.KEY_PRESSED)) {
            switch (event.getKey()) {
                case UP:
                    board.move(VectorCoord.vectorUP());
                    this.pushCommand(new MoveCommand(this, "up"));
                    break;

                case DOWN:
                    board.move(VectorCoord.vectorDOWN());
                    this.pushCommand(new MoveCommand(this, "down"));
                    break;

                case LEFT:
                    board.move(VectorCoord.vectorLEFT());
                    this.pushCommand(new MoveCommand(this, "left"));
                    break;

                case RIGHT:
                    board.move(VectorCoord.vectorRIGHT());
                    this.pushCommand(new MoveCommand(this, "right"));
                    break;

                case UNDEFINED:
                    return false;

                case S:
                    /*TODO: Sauvegarde*/
                    break;

                case M:
                    music.stop();
                    break;

                case Z:
                    this.undo();
                    break;
            }

            context.renderFrame(graphics -> render(graphics, false));
        }

        return true;
    }

    public void removeEntity(BoardEntity boardEntity) {
        this.board.removeEntity(boardEntity);
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLose() {
        return lose;
    }

    public static void win() {
        LevelManager.win = true;
    }

    public static void lose() {
        LevelManager.lose = true;
    }

    public Board backup() {
        return this.board.clone();
    }

    public void restore(Board backupBoard) {
        this.board = backupBoard;
    }

    public void pushCommand(Command command) {
        this.history.push(command, new Memento(this));
    }

    public void undo() {
        this.history.undo();
    }

    public Rules getRules() {
        return board.getRules();
    }
}
