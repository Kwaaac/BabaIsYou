package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.manager.GameManager;

public class ActionDefeat implements ActionStrategy{
    @Override
    public void execute(BoardEntity from, BoardEntity to) {
        GameManager.getInstance().removeGameObject(from);
    }
}
