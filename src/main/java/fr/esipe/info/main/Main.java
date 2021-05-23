package fr.esipe.info.main;

import fr.esipe.info.factories.WordFactory;
import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.Entity;
import fr.esipe.info.game.enums.EnumEntity;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.LevelManager;

public class Main {
    static WordFactory wordFactory;

    public static void main(String[] args){
        EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_00.getFileName());
        LevelManager levelManager = new LevelManager("bob", encoded);
        levelManager.displayBoard();

        Entity entity = new Entity(EnumEntity.BABA);
        System.out.println(entity.getCoord());
    }
}
