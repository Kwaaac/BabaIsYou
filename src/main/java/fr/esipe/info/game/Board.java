package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final List<List<List<BoardEntity>>> board;
    private final List<BoardEntity> playerIsYou = new ArrayList<>();
    private final int height;
    private final int width;

    public Board(List<List<List<BoardEntity>>> board) {
        Objects.requireNonNull(board);
        if (board.isEmpty() || board.get(0).isEmpty()) {
            throw new IllegalArgumentException("The board must be not empty");
        }

        this.board = board;
        this.height = board.size();
        this.width = board.get(0).size();
    }

    /**
     * Method that fecth the entity with the "YOU" property
     * <p>
     * Prevent to iterate over the board every time the player moves.
     */
    private void setPlayable() {
        playerIsYou.clear();
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {
            if (entity.usesProperties(EnumOp.IS, EnumProp.YOU)) {
                playerIsYou.add(entity);
            }
        })));
    }

    private void checkVector(VectorCoord vc) {
        Objects.requireNonNull(vc);
        if (vc.getxCoord() > height || vc.getyCoord() > width) {
            throw new IllegalArgumentException("The given vector is not acceptable. It fetch outside the board");
        }
    }

    /**
     * Return the list of entities in the cell with the given coordinates
     *
     * @param vc vector of coordinates in the board
     * @return Empty List or List containing BordEntity
     */
    private List<BoardEntity> getEntitiesFromVector(VectorCoord vc) {
        checkVector(vc);
        return board.get(vc.getxCoord()).get(vc.getyCoord());
    }
    
    @Override

    public String toString() {
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
                    strRow.append(entity.printCommandLineEntity());
                }

            }
            strRow.append("|\n");
        }

        return strRow.toString();
    }

    private boolean isMoveAuthorized() {
        return true;
    }

    public void move(VectorCoord vc) {

    }

}
