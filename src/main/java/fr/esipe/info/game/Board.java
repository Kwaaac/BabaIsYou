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

/**
 * This class reprensents the board of the game
 */
public class Board {
    private List<List<List<BoardEntity>>> board;
    private List<BoardEntity> playerIsYou = new ArrayList<>();
    private final Map<BoardEntity, Boolean> mapPlayerMove = new HashMap<>();

    private final List<BoardEntity> turnAction = new ArrayList<>();

    private final Set<Legend> entitySet = new HashSet<>();

    private Rules rules = new Rules();

    private int height;
    private int width;

    /**
     * Constructor
     *
     * @param board
     */
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

    /**
     * Secondary constructor based on a created board
     *
     * @param target board
     */
    public Board(Board target) {
        if (target != null) {
            this.board = target.cloneBoard();
            this.playerIsYou = target.clonePlayerYou();
            this.rules = target.getRules();
            this.width = target.width;
            this.height = target.height;
        }
        addPresentEntity();
    }

    /**
     * Clone the board
     *
     * @return new cloned board
     */
    public Board clone() {
        return new Board(this);
    }

    /**
     * Clone of the rules
     *
     * @return new cloned rules
     */
    public Rules getRules() {
        return rules.clone();
    }

    /**
     * clone the list of player
     *
     * @return new list of player
     */
    private List<BoardEntity> clonePlayerYou() {
        return new ArrayList<>(this.playerIsYou);
    }

    /**
     * Deep Clone of the board
     *
     * @return new list containing a board
     */
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

    /**
     * getter of the height of the board
     *
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter of the width of the board
     *
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method that fecth the entity with the "YOU" property
     * <p>
     * Prevent to iterate over the board every time the player moves.
     * <p>
     * Also set up the turns based entity list
     */
    private void setPlayable() {
        playerIsYou.clear();
        mapPlayerMove.clear();
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {
            if (!entity.isWord()) {
                if (rules.hasProperty(entity, EnumProp.YOU)) {
                    playerIsYou.add(entity);
                    mapPlayerMove.put(entity, false);
                }

                if (rules.hasProperty(entity, EnumProp.FIRE)) {
                    turnAction.add(entity);
                }

            }
        })));
    }

    /**
     * TODO
     */
    private void addPresentEntity() {
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {
            if (!entity.isWord()) {
                entitySet.add(entity.getLegend());
            }
        })));
    }

    /**
     * Change every "from" legend to the "to" legend.
     *
     * @param from the legend to be replaced
     * @param to   the legend that will replace
     */
    private void swapEntities(Legend from, Legend to) {
        if (!entitySet.contains(from)) {
            return;
        }
        entitySet.remove(from);
        entitySet.add(to);
        board.forEach(row -> row.forEach(cell -> cell.forEach(entity -> {
            if (entity.getLegend().equals(from)) {
                entity.changeEntity(to);
            }
        })));
    }

    /**
     * Method that create a rule or apply a swap between the entities
     *
     * @param nounEntity     the nouns of the sentence
     * @param operatorEntity the operator of the sentence
     * @param thirdEntity    the right entity of the sentence (either noun or property)
     */
    private void applyRuleOrSwap(BoardEntity nounEntity, BoardEntity operatorEntity, BoardEntity thirdEntity) {
        if (operatorEntity.isOperator()) {
            if (thirdEntity.isProperty()) {
                rules.add(nounEntity.getLegend().getEntity(), EnumProp.valueOf(thirdEntity.getLegend().getName()));
            }
            if (thirdEntity.isNoun()) {
                swapEntities(nounEntity.getLegend().getEntity(), thirdEntity.getLegend().getEntity());
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

    /**
     * Update the rules of the level by reading every valid sentences in the level and translating them into rules
     */
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
        rules.isWin(playerIsYou);
    }

    /**
     * Verify if the vector is inside the board
     *
     * @return the vector if nothing is wrong
     */
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

    /**
     * Remove an entity from the board
     *
     * @param entity the entity to be removed
     * @return true if this list contained the specified element
     */
    public boolean removeEntity(BoardEntity entity) {
        Objects.requireNonNull(entity);
        var pos = entity.getPos();
        return board.get(pos.getxCoord()).get(pos.getyCoord()).remove(entity);
    }

    /**
     * Add the given entity to the board based on this coodinates
     *
     * @param entity the given entity
     * @return true (as specified by Collection.add)
     */
    private boolean addEntity(BoardEntity entity) {
        Objects.requireNonNull(entity);
        var pos = entity.getPos();
        return board.get(pos.getxCoord()).get(pos.getyCoord()).add(entity);
    }

    /**
     * Normalize the movement
     * <p>
     * Move the entity with the given movement direction.
     * If the new position is not allowed then the previous position of the entity is returned
     *
     * @param entityPos The entity to be moved
     * @param mvtVector the movement direction
     * @return The new position if the movement is authorized, the previous position of the entity otherwise.
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

    /**
     * Check is the given coodinates are inside the board;
     *
     * @param vc the given coordiantes
     * @return True is the coordinates are inside the board, false otherwise.
     */
    private boolean isInsideBoard(VectorCoord vc) {
        var x = vc.getxCoord();
        var y = vc.getyCoord();

        return x >= 0 && x < height && y >= 0 && y < width;
    }

    /**
     * Check if the entity can move and if so move an entity to the given direction
     *
     * @param entity entity to be moved
     * @param vc     the direction to move
     * @return true of the entity was moved, false otherwise
     */
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
        entity.changeDirAnim(vc);
        return addEntity(entity);
    }

    /**
     * fetch the first entity from a cell
     *
     * @param vc coordinates of the cell
     * @return the corresponding entity
     */
    private BoardEntity getFirstEntityFromList(VectorCoord vc) {
        var entities = this.getEntitiesFromVector(vc);
        Collections.sort(entities);
        return entities.stream().findFirst().orElse(null);
    }

    /**
     * Method that check if the move is authorized
     *
     * @param vectorCoord
     * @return
     */
    private boolean isMoveAuthorized(VectorCoord vectorCoord) {
        var entitiesInNewCoord = this.getEntitiesFromVector(vectorCoord);
        if (entitiesInNewCoord.isEmpty()) {
            return true;
        }

        return entitiesInNewCoord.stream().anyMatch(boardEntity -> rules.getStates(boardEntity).stream().findFirst().orElse(new NormalState()).isSteppable());
    }

    /**
     * Move every "you" entities
     *
     * @param vc direction of the movement
     */
    public void move(VectorCoord vc) {
        var flag = false;

        mapPlayerMove.clear();
        playerIsYou.forEach(you -> mapPlayerMove.put(you, false));
        System.out.println(turnAction);
        turnAction.forEach(entity -> entity.executePreciceAction(entity, rules, EnumProp.FIRE));
        for (var entity : playerIsYou) {
            if (!mapPlayerMove.containsKey(entity)) {
                continue;
            }
            entity.executeAction(entity, this.rules);
            var entitiesFromTo = this.getEntitiesFromVector(this.normalizeMovementVector(entity.getPos(), vc));
            Collections.sort(entitiesFromTo);
            var to = entitiesFromTo.stream().findFirst().orElse(new Entity(Legend.BLANK, VectorCoord.vectorOutOfTheLoop()));
            if (moveEntity(entity, vc)) {
                entity.executeAction(to, this.rules);
                if (to.isWord()) {
                    flag = true;
                }
            }
        }
        if (flag) {
            updateRules();
        }
        System.out.println(this);
    }

    /**
     * Display the board on the window
     *
     * @param graphics   graphics
     * @param updateAnim does the animations needs to be updated
     */
    public void displayGraphic(Graphics2D graphics, boolean updateAnim) {
        var gm = GameManager.getInstance();
        var cellSize = gm.getCellSize();
        var widthDelta = gm.getWidthDelta();
        var heightDelta = gm.getHeightDelta();

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(0).size(); j++) {
                graphics.setColor(Color.BLACK);
                graphics.fillRect(j * cellSize + widthDelta, i * cellSize + heightDelta, cellSize, cellSize);
                for (var entity : board.get(i).get(j)) {
                    entity.draw(graphics);
                    if (updateAnim) {
                        entity.nextAnim();
                    }
                }
            }
        }
    }
}
