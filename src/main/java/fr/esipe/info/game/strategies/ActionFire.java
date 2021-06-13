package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.manager.GameManager;

/**
 * Representation of the execution of the fire property
 */
public class ActionFire implements ActionStrategy {
    @Override
    public void execute(Rules rules, BoardEntity from, BoardEntity to) {
        int turns = from.turnsBeforeActive();

        if (turns == -1) {
            from.setTurnBeforeActive(2);
        } else if (turns == 0) {
            GameManager.getInstance().removeGameObject(from);
        } else {
            from.setTurnBeforeActive(turns - 1);
        }
    }
}
