package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.manager.LevelManager;

public class ActionWin implements ActionStrategy{
    @Override
    public void execute(BoardEntity from, BoardEntity to) {
        LevelManager.win();
    }
}
