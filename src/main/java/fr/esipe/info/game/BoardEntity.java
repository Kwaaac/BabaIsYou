package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.rule.Rules;

import java.awt.*;

public interface BoardEntity extends GameObject, Comparable<BoardEntity> {

    String printCommandLineEntity();

    Legend getLegend();

    void changeEntity(Legend newLegend);

    boolean isWord();

    boolean isNoun();

    boolean isOperator();

    boolean isProperty();

    void executeAction(BoardEntity entity, Rules rules);

    void executePreciceAction(BoardEntity entity, Rules rules, EnumProp prop);

    Entity clone();

    void draw(Graphics2D graphics);

    void nextAnim();

    void changeDirAnim(VectorCoord dir);

    int turnsBeforeActive();

    void setTurnBeforeActive(int count);
}
