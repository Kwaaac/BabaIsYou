package fr.esipe.info.game.strategies;

import fr.esipe.info.game.Entity;

public class ActionHot implements ActionStrategy{
    @Override
    public void execute(Entity from, Entity to) {
        if(to.getState().isMelt()){
            displayMessage("Destruction de " + to);
        }
    }
}
