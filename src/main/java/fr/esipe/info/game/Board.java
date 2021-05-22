package fr.esipe.info.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Board {
    private List<List<List<BoardEntity>>> board;
    private final int height;
    private final int width;

    public Board(int height, int width){
        if(height < 0 || width < 0){
            throw new IllegalArgumentException("Size cannot be negative");
        }
        this.board = new ArrayList<>(height);
        this.height = height;
        this.width = width;
    }

    public List<List<List<BoardEntity>>> getBoard() {
        return board;
    }

    private void initBoard(){
        Objects.requireNonNull(this.board);
        for(int line = 0; line < height; line++){
            var arrayList = new ArrayList<List<BoardEntity>>();
            for(int column = 0; column < width; column++){
                var linked = new LinkedList<BoardEntity>();
                arrayList.add(linked);
            }
            board.add(arrayList);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.board.forEach(line -> stringBuilder.append(line));
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
