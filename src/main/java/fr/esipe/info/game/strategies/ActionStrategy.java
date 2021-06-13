package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;

/**
 * Functional interface for the execution of a property over entities
 */
public interface ActionStrategy {
    /**
     * Execute the property action on the from and to entity
     *
     * @param rules Rules of the level
     * @param from  The entity that encounter the "to" entity
     * @param to    The entity that is encountered by the "from" entity
     */
    void execute(Rules rules, BoardEntity from, BoardEntity to);
}
