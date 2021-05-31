package fr.esipe.info.manager;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.Board;
import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.memento.History;
import fr.esipe.info.memento.Memento;
import fr.esipe.info.memento.commands.Command;
import fr.esipe.info.memento.commands.MoveCommand;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;

import java.awt.*;

public class LevelManager {
    private final History history;
    private String levelName;
    private Board board;
    private final EncryptionDecorator encoded;

    private static boolean win = false;
    private static boolean lose = false;

    public LevelManager(String levelName, EncryptionDecorator encoded) {
        this.levelName = levelName;
        this.encoded = encoded;
        this.board = new Board(this.encoded.readData());
        this.history = new History();
    }

    public void displayBoard() {
        System.out.println(board);
    }


    public void render(Graphics2D graphics) {
        graphics.clearRect(0, 0, GameManager.getInstance().getWidth(), GameManager.getInstance().getHeight());
        board.displayGraphic(graphics);
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
                case Z:
                    this.undo();
                    break;
            }

            context.renderFrame(this::render);
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
        return getRules();
    }
}
