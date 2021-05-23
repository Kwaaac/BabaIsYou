package fr.esipe.info.game.strategies;

import fr.esipe.info.game.Entity;

public class ActionMelt implements ActionStrategy{
    @Override
    public void execute(Entity from, Entity to) {
        if(to.getState().isHot()){
            displayMessage("Destruction de " + from);
        }
        else{
            displayMessage("Rien ne se passe");
        }
    }
}
