package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.manager.GameManager;

/**
 * Representation of the execution of the defeat property
 */
public class ActionDefeat implements ActionStrategy {
    @Override
    public void execute(Rules rules, BoardEntity from, BoardEntity to) {
        if (rules.hasProperty(from, EnumProp.YOU)){
            GameManager.getInstance().removeGameObject(from);
        }

    }
}
