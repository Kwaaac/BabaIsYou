package fr.esipe.info.main;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.Entity;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.states.HotState;
import fr.esipe.info.game.states.MeltState;
import fr.esipe.info.game.words.Noun;

public class Main {
    public static void main(String[] args) {

        /*Application.run(Color.BLACK, context -> {

            EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_00.getFileName());
            LevelManager levelManager = new LevelManager("bob", encoded);
            levelManager.displayBoard();

            while (true) {
                levelManager.processEvent(context.pollEvent());
            }
        });*/
        Entity entity = new Entity(new Noun(EnumEntity.BABA, new VectorCoord(0,0)),new VectorCoord(0,0), new MeltState());
        Entity entity1 = new Entity(new Noun(EnumEntity.LAVA, new VectorCoord(0,0)),new VectorCoord(0,0), new HotState());

        entity1.getState().getActionStrategy().execute(entity1, entity);
    }
}
