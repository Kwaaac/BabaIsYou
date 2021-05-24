package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.LevelManager;
import fr.umlv.zen5.Application;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Application.run(Color.BLACK, context -> {

            EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_00.getFileName());
            LevelManager levelManager = new LevelManager("bob", encoded);
            levelManager.displayBoard();

            while (levelManager.processEvent(context.pollEvent())) {

            }

            context.exit(0);
        });

    }
}
