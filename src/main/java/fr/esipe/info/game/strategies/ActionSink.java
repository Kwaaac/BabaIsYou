package fr.esipe.info.game.strategies;

import fr.esipe.info.game.Entity;

public class ActionSink implements ActionStrategy{
    @Override
    public void execute(Entity from, Entity to) {
        displayMessage("Destruction de " + from);
        displayMessage("Destruction de " + to);
    }
}
