package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.manager.GameManager;

public class ActionHot implements ActionStrategy {
    @Override
    public void execute(Rules rules, BoardEntity from, BoardEntity to) {
        if (to != null) {
            if (rules.hasProperty(to, EnumProp.MELT)) {
                GameManager.getInstance().removeGameObject(from);
            }
        }
    }
}
