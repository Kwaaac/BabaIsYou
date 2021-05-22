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
        for (var row : board) {
            System.out.print("_".repeat(row.size() *5) + "\n");
            for (var col : row) {
                System.out.print("|");
                if(col.isEmpty()){
                    System.out.print("    ");
                    continue;
                }

                for (var entity : col) {
                    System.out.print(entity.displayNoun());
                }

            }
            System.out.print("|");
            System.out.println();
        }
    }
}
