package fr.esipe.info.manager;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.BoardEntity;

import java.util.List;

public class LevelManager {
    private final String levelName;
    private final List<List<List<BoardEntity>>> board;
    private final EncryptionDecorator encoded;

    public LevelManager(String levelName, EncryptionDecorator encoded) {
        this.levelName = levelName;
        this.encoded = encoded;
        this.board = this.encoded.readData();
    }

    public void displayBoard() {
        var strRow = new StringBuilder();

        for (var row : board) {
            strRow.append("_____".repeat(row.size())).append("\n");
            for (var col : row) {
                strRow.append("|");
                if (col.isEmpty()) {
                    strRow.append("    ");
                    continue;
                }

                for (var entity : col) {
                    strRow.append(entity.displayNoun());
                }

            }
            strRow.append("|\n");
        }

        System.out.println(strRow);
    }

}
