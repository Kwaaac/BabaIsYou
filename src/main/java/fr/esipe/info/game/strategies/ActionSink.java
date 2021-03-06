package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.manager.GameManager;

/**
 * Representation of the execution of the Sink property
 */
public class ActionSink implements ActionStrategy {
    @Override
    public void execute(Rules rules, BoardEntity from, BoardEntity to) {
        if (to != null && !from.equals(to)) {
            GameManager.getInstance().removeGameObject(to);
            GameManager.getInstance().removeGameObject(from);
        }
    }
}
