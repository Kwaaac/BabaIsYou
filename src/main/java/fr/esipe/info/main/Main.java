package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.LevelManager;

public class Main {
    public static void main(String[] args){
        EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_00.getFileName());
        LevelManager levelManager = new LevelManager("bob", encoded);
        levelManager.displayBoard();
    }
}
