package fr.esipe.info.game.strategies;

import fr.esipe.info.game.BoardEntity;

public class ActionNone implements ActionStrategy{
    @Override
    public void execute(BoardEntity from, BoardEntity to) {
        displayMessage("Rien ne se passe");
    }
}
