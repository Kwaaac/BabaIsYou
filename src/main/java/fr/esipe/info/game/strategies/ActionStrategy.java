package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;

public interface ActionStrategy {
    void execute(Rules rules, BoardEntity from, BoardEntity to);
}
