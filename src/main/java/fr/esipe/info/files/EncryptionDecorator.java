package fr.esipe.info.files;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.Entity;
import fr.esipe.info.game.enums.Legend;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class EncryptionDecorator {

    private final BufferedReader file;

    public EncryptionDecorator(InputStream file) {
        Objects.requireNonNull(file);
        this.file = new BufferedReader(
                new InputStreamReader(file));
    }

    public List<List<List<BoardEntity>>> readData() {
        return this.decode(file.lines().collect(Collectors.joining("\r")));
    }

    private List<List<List<BoardEntity>>> decode(String data) {
        int i = 0;
        List<Legend> legend = Arrays.asList(Legend.values());
        List<List<List<BoardEntity>>> result = initBoard(data);
        Iterator<String> iterator = data.lines().iterator();

        for (List<List<BoardEntity>> lists : result) {
            String line = iterator.next();
            decodeLine(lists, line, legend, i);

            i++;
        }
        
        return result;
    }


    private void decodeLine(List<List<BoardEntity>> line, String dataLine, List<Legend> legend, int lineIndex) {
        int indexString = 0;
        for (int i = 0; i < line.size(); i++) {
            var vectorCoord = new VectorCoord(lineIndex, i);
            int finalIndexString = indexString;
            Legend temp = legend.stream().filter(l -> l.getaChar() == dataLine.charAt(finalIndexString)).findFirst().get();
            if (!temp.equals(Legend.BLANK)) {
                line.get(i).add(new Entity(temp, vectorCoord));
            }
            indexString++;
        }
    }

    /***
     *
     * @param data file data
     * @return height of the board
     */
    private int heightBoard(String data) {
        Objects.requireNonNull(data);
        return (int) data.lines().count();
    }

    /***
     *
     * @param data file data
     * @return width of the board
     */
    private int widthBoard(String data) {
        Objects.requireNonNull(data);
        int count;
        for (count = 0; data.charAt(count) != '\r'; count++) ;
        return count;
    }

    /**
     * @param data file data
     * @return board in 3D from this file data
     */
    private List<List<List<BoardEntity>>> initBoard(String data) {
        int height = heightBoard(data);
        int width = widthBoard(data);
        List<List<List<BoardEntity>>> board = new ArrayList<>(height);
        for (int line = 0; line < height; line++) {
            var arrayList = new ArrayList<List<BoardEntity>>();
            for (int column = 0; column < width; column++) {
                var linked = new LinkedList<BoardEntity>();
                arrayList.add(linked);
            }
            board.add(arrayList);
        }
        return board;
    }

}
