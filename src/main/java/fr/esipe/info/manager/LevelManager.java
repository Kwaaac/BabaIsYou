package fr.esipe.info.manager;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.words.Noun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelManager {
    private final String levelName;
    private final List<List<List<BoardEntity>>> board;

    public LevelManager(String levelName, int width, int height) {
        this.levelName = levelName;

        this.board = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            var arrayList = new ArrayList<List<BoardEntity>>();
            for(int j = 0; j < width; j++){

                var linked = new LinkedList<BoardEntity>();
                linked.add(new Noun(EnumEntity.BABA));

                arrayList.add(linked);

            }
            board.add(arrayList);
        }

        System.out.println(board);
    }
}
