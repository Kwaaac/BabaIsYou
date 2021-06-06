package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.states.State;

import java.awt.*;
import java.util.List;

public interface BoardEntity extends GameObject, Comparable<Entity> {
    String printCommandLineEntity();

    Legend getLegend();

    void changeEntity(Legend newLegend);

    boolean isWord();

    boolean isNoun();

    boolean isOperator();

    boolean isProperty();

    void executeAction(BoardEntity entity);

    boolean isSteppable();

    boolean isMovable();

    List<State> getStates();

    void draw(Graphics2D graphics);

    void changeDirAnim(VectorCoord dir);

    void nextAnim();
}
