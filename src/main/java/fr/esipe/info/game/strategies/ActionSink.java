package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;

public class ActionSink implements ActionStrategy{
    @Override
    public void execute(BoardEntity from, BoardEntity to) {
        if(to != null && !from.equals(to)){
            System.out.println("Application de SINK");
            GameManager.getInstance().removeGameObject(to);
            GameManager.getInstance().removeGameObject(from);
            LevelManager.lose();
        }
    }
}
