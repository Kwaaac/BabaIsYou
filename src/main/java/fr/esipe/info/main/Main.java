package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;
import fr.umlv.zen5.Application;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Application.run(new Color(17, 15, 15), context -> {
            GameManager gameManager = GameManager.getInstance();
            gameManager.setHeight((int) context.getScreenInfo().getHeight());
            gameManager.setWidth((int) context.getScreenInfo().getWidth());

            EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_01.getFileStream());
            LevelManager levelManager = new LevelManager("bob", encoded, "/Music/baba.wav");
            gameManager.setLevelManager(levelManager);
            context.renderFrame(levelManager::render);

            while (levelManager.processEvent(context) && !levelManager.isLose() && !levelManager.isWin()) {

            }

            context.exit(0);
        });

    }
}
