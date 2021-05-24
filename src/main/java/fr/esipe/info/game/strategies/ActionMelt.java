package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.manager.GameManager;

public class ActionMelt implements ActionStrategy{
    @Override
    public void execute(BoardEntity from, BoardEntity to) {
        if(to != null){
            if(to.usesProperties(EnumOp.IS, EnumProp.HOT)){
                GameManager.getInstance().removeGameObject(from);
            }
        }
    }
}
