package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.manager.LevelManager;

public class ActionWin implements ActionStrategy {
    @Override
    public void execute(Rules rules, BoardEntity from, BoardEntity to) {
        LevelManager.win();
    }
}
