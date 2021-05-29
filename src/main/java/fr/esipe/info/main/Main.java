package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;
import fr.umlv.zen5.Application;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Application.run(new Color(0f, 0f, 0f, .0f), context -> {

            EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_01.getFileStream());
            LevelManager levelManager = new LevelManager("bob", encoded);
            GameManager gameManager = GameManager.getInstance();
            gameManager.setLevelManager(levelManager);
            levelManager.displayBoard();

            while (levelManager.processEvent(context.pollEvent()) && !levelManager.isLose() && !levelManager.isWin()) {

            }

            context.exit(0);
        });

    }
}
