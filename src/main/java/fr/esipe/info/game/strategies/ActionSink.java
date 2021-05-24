package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.manager.GameManager;

public class ActionSink implements ActionStrategy{
    @Override
    public void execute(BoardEntity from, BoardEntity to) {
        if(to != null){
            GameManager.getInstance().removeGameObject(from);
            GameManager.getInstance().removeGameObject(to);
        }
    }
}
