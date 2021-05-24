package fr.esipe.info.manager;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.Board;
import fr.esipe.info.game.BoardEntity;
import fr.umlv.zen5.Event;

public class LevelManager {
    private final String levelName;
    private final Board board;
    private final EncryptionDecorator encoded;

    private boolean win = false;
    private boolean lose = false;

    public LevelManager(String levelName, EncryptionDecorator encoded) {
        this.levelName = levelName;
        this.encoded = encoded;
        this.board = new Board(this.encoded.readData());


    }

    public void displayBoard() {
        System.out.println(board);
    }


    public void processEvent(Event event) {
        if (event == null) {
            return;
        }

        if (event.getAction().equals(Event.Action.KEY_PRESSED)) {
            switch (event.getKey()) {
                case UP -> board.move(VectorCoord.vectorUP());
                case DOWN -> board.move(VectorCoord.vectorDOWN());
                case LEFT -> board.move(VectorCoord.vectorLEFT());
                case RIGHT -> board.move(VectorCoord.vectorRIGHT());
            }
        }

    }

    public static void removeEntity(BoardEntity boardEntity){
        Board.removeBoardEntityFromBoard(boardEntity);
    }


    public boolean isWin() {
        return win;
    }

    public boolean isLose() {
        return lose;
    }
}
