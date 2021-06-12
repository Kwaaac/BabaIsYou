package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;

public class ActionNone implements ActionStrategy {
    @Override
    public void execute(Rules rules, BoardEntity from, BoardEntity to) {
    }
}
