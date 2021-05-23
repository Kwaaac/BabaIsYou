package fr.esipe.info.game.strategies;

import fr.esipe.info.game.Entity;

public class ActionNone implements ActionStrategy{
    @Override
    public void execute(Entity from, Entity to) {
        displayMessage("Rien ne se passe");
    }
}
