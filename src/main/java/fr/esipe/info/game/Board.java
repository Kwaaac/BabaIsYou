package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.game.states.NormalState;
import fr.esipe.info.manager.GameManager;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Board {
    private List<List<List<BoardEntity>>> board;
    private List<BoardEntity> playerIsYou = new ArrayList<>();
    private Map<BoardEntity, Boolean> mapPlayerMove = new HashMap<>();
    private final Set<Legend> entitySet = new HashSet<>();
    private Rules rules = new Rules();

    private int height;
    private int width;


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
    }

    public Board(Board target) {
        if (target != null) {
            this.board = target.cloneBoard();
            this.playerIsYou = target.clonePlayerYou();
            this.rules = target.getRules();
            this.width = target.width;
            this.height = target.height;
        }
    }

    public Board clone() {
        return new Board(this);
    }

    public List<BoardEntity> getPlayerIsYou() {
        return playerIsYou;
    }

    public Rules getRules() {
        return rules.clone();
    }

    private List<BoardEntity> clonePlayerYou(){
        var isYou = new ArrayList<BoardEntity>();
        this.playerIsYou.forEach(player -> isYou.add(player.clone()));
        return isYou;
    }

    private List<List<List<BoardEntity>>> cloneBoard() {
        List<List<List<BoardEntity>>> result = new ArrayList<>();
        for (int line = 0; line < this.height; line++) {
            var lineBoard = new ArrayList<List<BoardEntity>>();
            for (int column = 0; column < this.width; column++) {
                var linked = new LinkedList<BoardEntity>();
                this.board.get(line).get(column).forEach(cell -> linked.add(cell.clone()));
                lineBoard.add(column, linked);
            }
            result.add(line, lineBoard);
        }
        return result;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Method that fecth the entity with the "YOU" property
     * <p>
     * Prevent to iterate over the board every time the player moves.
     */
    private void setPlayable() {
        playerIsYou.clear();
        mapPlayerMove.clear();
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {
            if (entity != null && !entity.isWord()) {
                if (rules.hasProperty(entity.getLegend(), EnumProp.YOU)) {
                    playerIsYou.add(entity);
                    mapPlayerMove.put(entity, false);

                    rules.add(entity.getLegend(), EnumProp.PUSH);
                }
            }
        })));
    }

    private void swapEntities(BoardEntity from, BoardEntity to) {

    }

    private void applyRuleOrSwap(BoardEntity nounEntity, BoardEntity operatorEntity, BoardEntity thirdEntity) {
        if (operatorEntity.isOperator()) {
            if (thirdEntity.isProperty()) {
                rules.add(nounEntity.getLegend().getEntity(), EnumProp.valueOf(thirdEntity.getLegend().getName()));
            }
            if (thirdEntity.isNoun()) {
                swapEntities(nounEntity, thirdEntity);
            }
        }
    }

    /**
     * add a new Rule to the game or swap every entity
     *
     * @param entity the noun
     * @param dir    the direction (Normally either DOWN or RIGHT)
     */
    private void createRuleOrSwapEntity(BoardEntity entity, VectorCoord dir) {
        var vectorn1 = VectorCoord.addTwoVectors(entity.getPos(), dir);
        var vectorn2 = VectorCoord.addTwoVectors(vectorn1, dir);
        BoardEntity entityOperator;
        BoardEntity thirdEntity;
        try {
            entityOperator = getFirstEntityFromList(vectorn1);
            thirdEntity = getFirstEntityFromList(vectorn2);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (entityOperator != null && thirdEntity != null && entityOperator.isOperator()) {
            if (thirdEntity.isProperty()) {
                rules.add(entity.getLegend().getEntity(), EnumProp.valueOf(thirdEntity.getLegend().getName()));
            }
            if (thirdEntity.isNoun()) {
                swapEntities(entity, thirdEntity);
            }
        }
    }

    private void updateRules() {
        rules.clearStates();
        board.forEach(row -> row.forEach(cell -> cell.stream().forEach(entity -> {
            if (entity.isNoun()) {
                createRuleOrSwapEntity(entity, VectorCoord.vectorDOWN());
                createRuleOrSwapEntity(entity, VectorCoord.vectorRIGHT());
            }

            if (entity.isWord()) {
                rules.add(entity.getLegend(), EnumProp.PUSH);
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

    private boolean moveEntity(BoardEntity entity, VectorCoord vc) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(vc);
        mapPlayerMove.remove(entity);
        var newPos = normalizeMovementVector(entity.getPos(), vc);
        var nextEntity = this.getFirstEntityFromList(newPos);
        entity.executeAction(nextEntity, rules);
        if (nextEntity != null && rules.isMovable(nextEntity) && !newPos.equals(entity.getPos())) {
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
        var entities = this.getEntitiesFromVector(vc);
        Collections.sort(entities);
        return entities.stream().findFirst().orElse(null);
    }

    /**
     *
     */
    private boolean isMoveAuthorized(VectorCoord vectorCoord) {
        var entitiesInNewCoord = this.getEntitiesFromVector(vectorCoord);
        if (entitiesInNewCoord.isEmpty()) {
            return true;
        }

        return entitiesInNewCoord.stream().anyMatch(boardEntity -> rules.getStates(boardEntity).stream().findFirst().orElse(new NormalState()).isSteppable());
    }

    public void move(VectorCoord vc) {
        var flagUpdate = false;

        setPlayable();
        for (var entity : playerIsYou) {
            /* The entity has already moved */
            if (mapPlayerMove.getOrDefault(entity, true)) {
                continue;
            }

            entity.executeAction(entity, rules);
            var entitiesFromTo = this.getEntitiesFromVector(this.normalizeMovementVector(entity.getPos(), vc));
            Collections.sort(entitiesFromTo);
            var to = entitiesFromTo.stream().findFirst().orElse(new Entity(Legend.BLANK, VectorCoord.vectorOutOfTheLoop()));
            if (moveEntity(entity, vc)) {
                entity.executeAction(to, rules);
                if (to.isWord()) {
                    flagUpdate = true;
                }
            }
        }
        if (flagUpdate) {
            updateRules();
        }
        System.out.println(this);
    }


    public void displayGraphic(Graphics2D graphics) {
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> entity.draw(graphics))));
    }
}
