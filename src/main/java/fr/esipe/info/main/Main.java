package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.manager.LevelManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        EncryptionDecorator encoded = new EncryptionDecorator("levels/level01.txt");
        LevelManager levelManager = new LevelManager("bob", encoded);
        levelManager.displayBoard();
    }
}
