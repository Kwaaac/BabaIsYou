package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.manager.LevelManager;

public class Main {
    public static void main(String[] args){
        EncryptionDecorator encoded = new EncryptionDecorator("levels/level04.txt");
        LevelManager levelManager = new LevelManager("bob", encoded);
        levelManager.displayBoard();
    }
}
