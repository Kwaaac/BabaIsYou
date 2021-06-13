package fr.esipe.info.files;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.Entity;
import fr.esipe.info.game.enums.Legend;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is used to translate an input stream into a new board
 */
public class EncryptionDecorator {

    /**
     * Read the data from the given path and return an associated list en entity corresponding to a Board
     *
     * @param file Level file to be read
     * @return A new list of entity corresponding to a Board
     * @throws IOException Can throw IOException if the file cannot be read
     */
    public static List<List<List<BoardEntity>>> readData(Path file) throws IOException {
        try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            return decode(reader.lines().collect(Collectors.toList()));
        }
    }

    /**
     * Decode the whole file line per line to create a new board
     *
     * @param data The list of the lines of the file
     * @return a new board
     */
    private static List<List<List<BoardEntity>>> decode(List<String> data) {
        int i = 0;
        List<Legend> legend = Arrays.asList(Legend.values());
        List<List<List<BoardEntity>>> result = initBoard(data);
        Iterator<String> iterator = data.iterator();

        for (List<List<BoardEntity>> lists : result) {
            String line = iterator.next();
            decodeLine(lists, line, legend, i);

            i++;
        }

        return result;
    }

    /**
     * Decode a line of the file to translate to a 2D list of entities
     *
     * @param line      The empty line of the future board
     * @param dataLine  The line of the file to be translated
     * @param legend    A list of every legends available
     * @param lineIndex Index of the line
     */
    private static void decodeLine(List<List<BoardEntity>> line, String dataLine, List<Legend> legend, int lineIndex) {
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

    /**
     * Return the number of lines of the given data
     *
     * @param data file data
     * @return height of the board
     */
    private static int heightBoard(List<String> data) {
        Objects.requireNonNull(data);
        return data.size();
    }

    /**
     * Return the number of column of the given data
     *
     * @param data file data
     * @return width of the board
     */
    private static int widthBoard(List<String> data) {
        Objects.requireNonNull(data);
        return data.get(0).length();
    }

    /**
     * @param data file data
     * @return board in 3D from this file data
     */
    private static List<List<List<BoardEntity>>> initBoard(List<String> data) {
        int height = heightBoard(data);
        int width = widthBoard(data);
        List<List<List<BoardEntity>>> board = new ArrayList<>(height);
        for (int line = 0; line < height; line++) {
            var arrayList = new ArrayList<List<BoardEntity>>();
            for (int column = 0; column < width; column++) {
                var linked = new ArrayList<BoardEntity>();
                arrayList.add(linked);
            }
            board.add(arrayList);
        }
        return board;
    }

}
