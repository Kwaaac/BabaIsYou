package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;

/**
 * Functional interface for the execution of a property over entities
 */
public interface ActionStrategy {
    void execute(Rules rules, BoardEntity from, BoardEntity to);
}
