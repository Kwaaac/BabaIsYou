package fr.esipe.info.manager;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.Board;
import fr.umlv.zen5.Event;

public class LevelManager {
    private final String levelName;
    private final Board board;
    private final EncryptionDecorator encoded;

    private static boolean win = false;
    private static boolean lose = false;

    public LevelManager(String levelName, EncryptionDecorator encoded) {
        this.levelName = levelName;
        this.encoded = encoded;
        this.board = new Board(this.encoded.readData());


    }

    public void displayBoard() {
        System.out.println(board);
    }


    public boolean processEvent(Event event) {
        if (event == null) {
            return true;
        }

        if (event.getAction().equals(Event.Action.KEY_PRESSED)) {
            switch (event.getKey()) {
                case UP:
                    board.move(VectorCoord.vectorUP());
                    break;

                case DOWN:
                    board.move(VectorCoord.vectorDOWN());
                    break;

                case LEFT:
                    board.move(VectorCoord.vectorLEFT());
                    break;

                case RIGHT:
                    board.move(VectorCoord.vectorRIGHT());
                    break;

                case UNDEFINED:
                    return false;

                case S:
                    /*TODO: Sauvegarde*/
                    break;
            }
        }


        return true;
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
}
