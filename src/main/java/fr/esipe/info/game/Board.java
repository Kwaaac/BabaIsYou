package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.manager.GameManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
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

        var winHeight = GameManager.getInstance().getHeight();
        var winWidth = GameManager.getInstance().getWidth();

        GameManager.getInstance().setCellSize(Math.min(winWidth / width, winHeight / height));

        updateRules();

        Rules.displayRules();
    }

    /**
     * Method that fecth the entity with the "YOU" property
     * <p>
     * Prevent to iterate over the board every time the player moves.
     */
    private void setPlayable() {
        playerIsYou.clear();
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {
            if (entity != null && !entity.isWord()) {
                if (Rules.hasProperty(entity.getLegend(), EnumProp.YOU)) {
                    playerIsYou.add(entity);
                    Rules.add(entity.getLegend(), EnumProp.PUSH);
                }
            }
        })));
        System.out.println(playerIsYou);
    }

    private void swapEntities(BoardEntity from, BoardEntity to) {

    }

    private void applyRuleOrSwap(BoardEntity nounEntity, BoardEntity operatorEntity, BoardEntity thirdEntity) {
        if (operatorEntity.isOperator()) {
            if (thirdEntity.isProperty()) {
                Rules.add(nounEntity.getLegend().getEntity(), EnumProp.valueOf(thirdEntity.getLegend().getName()));
            }
            if (thirdEntity.isNoun()) {
                swapEntities(nounEntity, thirdEntity);
            }
        }
    }

    /**
     * add a new Rule to the game or swap every entity
     *
     * @param nounEntity the noun
     * @param dir        the direction (Normally either DOWN or RIGHT)
     */
    private void createRuleOrSwapEntity(BoardEntity nounEntity, VectorCoord dir) {
        var vec = VectorCoord.addTwoVectors(nounEntity.getPos(), dir);
        BoardEntity operatorEntity;
        BoardEntity thirdEntity;
        try {
            operatorEntity = getFirstEntityFromList(VectorCoord.addTwoVectors(nounEntity.getPos(), dir));
            thirdEntity = getFirstEntityFromList(VectorCoord.addTwoVectors(vec, dir));
            Objects.requireNonNull(operatorEntity);
            Objects.requireNonNull(thirdEntity);
        } catch (IllegalArgumentException | NullPointerException e) {
            return;
        }

        applyRuleOrSwap(nounEntity, operatorEntity, thirdEntity);
    }

    private void updateRules() {
        Rules.clearStates();
        board.forEach(row -> row.forEach(cell -> cell.stream().findFirst().ifPresent(entity -> {
            if (entity.isNoun()) {
                createRuleOrSwapEntity(entity, VectorCoord.vectorDOWN());
                createRuleOrSwapEntity(entity, VectorCoord.vectorRIGHT());
            }

            if (entity.isWord()) {
                Rules.add(entity.getLegend(), EnumProp.PUSH);
            }
        })));
        setPlayable();
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

    private boolean moveEntity(Iterator<BoardEntity> playerIterator, BoardEntity entity, VectorCoord vc) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(vc);
        var newPos = normalizeMovementVector(entity.getPos(), vc);
        var nextEntity = this.getFirstEntityFromList(newPos);

        if (nextEntity != null && Rules.hasProperty(entity.getLegend(), EnumProp.YOU) && Rules.hasProperty(nextEntity.getLegend(), EnumProp.YOU)) {
            playerIterator.next();
        }

        if (nextEntity != null && nextEntity.isMovable() && !newPos.equals(entity.getPos())) {
            this.moveEntity(playerIterator, nextEntity, vc);
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

        return entitiesInNewCoord.stream().anyMatch(boardEntity -> boardEntity.getStates().stream().findFirst().orElse(new NormalState()).isSteppable());
    }

    public void move(VectorCoord vc) {
        var playerIterator = playerIsYou.iterator();
        while (playerIterator.hasNext()) {
            var entity = playerIterator.next();
            entity.executeAction(entity);
            var to = this.getEntitiesFromVector(this.normalizeMovementVector(entity.getPos(), vc)).stream().findFirst().orElse(new Entity(Legend.BLANK, VectorCoord.vectorOutOfTheLoop()));
            if (moveEntity(playerIterator, entity, vc)) {
                entity.executeAction(to);
                if (to.isWord()) {
                    updateRules();
                }
            }
        }
        System.out.println(this);
    }

    public void displayGraphic(Graphics2D graphics) {
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> entity.draw(graphics))));
    }
}
