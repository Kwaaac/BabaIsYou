package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.State;

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
        /* TODO: remove this: Temporary adding you prop to Babas */

        playerIsYou.add(board.get(4).get(1).get(0));
        // setPlayable();

        Rules.add(Legend.WALL_ENTITY, EnumProp.STOP);
        Rules.add(Legend.BABA_ENTITY, EnumProp.YOU);
        Rules.add(Legend.FLAG_ENTITY, EnumProp.WIN);
        Rules.add(Legend.ROCK_ENTITY, EnumProp.PUSH);

    }

    /**
     * Method that fecth the entity with the "YOU" property
     * <p>
     * Prevent to iterate over the board every time the player moves.
     */
    private void setPlayable() {
        playerIsYou.clear();
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {

        })));
    }

    private VectorCoord checkVector(VectorCoord vc) {
        Objects.requireNonNull(vc);
        if (!isInsideBoard(vc)) {
            throw new IllegalArgumentException("The given vector is not acceptable. It fetch outside the board");
        }

        return vc;
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

    public boolean removeEntity(BoardEntity entity) {
        Objects.requireNonNull(entity);
        var pos = entity.getPos();
        return board.get(pos.getxCoord()).get(pos.getyCoord()).remove(entity);
    }

    private boolean addEntity(BoardEntity entity) {
        Objects.requireNonNull(entity);
        var pos = entity.getPos();
        return board.get(pos.getxCoord()).get(pos.getyCoord()).add(entity);
    }

    /**
     * @param entityPos
     * @param mvtVector
     * @return
     */
    private VectorCoord normalizeMovementVector(VectorCoord entityPos, VectorCoord mvtVector) {
        VectorCoord newPos;

        try {
            newPos = checkVector(VectorCoord.addTwoVectors(entityPos, mvtVector));
        } catch (IllegalArgumentException e) {
            newPos = entityPos;
        }

        return newPos;
    }


    private boolean isInsideBoard(VectorCoord vc) {
        var x = vc.getxCoord();
        var y = vc.getyCoord();

        return x >= 0 && x < height && y >= 0 && y < width;
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

    private boolean moveEntity(BoardEntity entity, VectorCoord vc) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(vc);
        System.out.println(entity.getStates());
        var newPos = normalizeMovementVector(entity.getPos(), vc);
        var nextEntity = this.getFirstEntityFromList(newPos);
        if (nextEntity != null && nextEntity.isMovable() && !newPos.equals(entity.getPos())) {
            this.moveEntity(nextEntity, vc);
        }
        if (!this.isMoveAuthorized(newPos)) {
            return false;
        }
        if (!removeEntity(entity)) {
            return false;
        }
        entity.setPos(newPos);
        return addEntity(entity);
    }

    private BoardEntity getFirstEntityFromList(VectorCoord vc) {
        return this.getEntitiesFromVector(vc).stream().findFirst().orElse(null);
    }

    private boolean isMoveAuthorized(VectorCoord vectorCoord) {
        var entitiesInNewCoord = this.getEntitiesFromVector(vectorCoord);
        if (entitiesInNewCoord.isEmpty()) {
            return true;
        }

        return entitiesInNewCoord.stream().anyMatch(boardEntity -> boardEntity.getStates().stream().anyMatch(State::isSteppable));
    }

    public void move(VectorCoord vc) {
        for (BoardEntity entity : playerIsYou) {
            entity.executeAction(entity);
            var to = this.getEntitiesFromVector(this.normalizeMovementVector(entity.getPos(), vc)).stream().findFirst().orElse(null);
            if (moveEntity(entity, vc)) {
                entity.executeAction(to);
            }
        }
        System.out.println(this);
    }

    public List<BoardEntity> getPlayerIsYou() {
        return playerIsYou;
    }
}
