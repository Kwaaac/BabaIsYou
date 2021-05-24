package fr.esipe.info.main;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.Entity;
import fr.esipe.info.game.enums.EnumEntity;
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
        var entity = new Entity(new Noun(EnumEntity.BABA, new VectorCoord(0,0)));
        entity.executeAllActions(entity);
    }
}
