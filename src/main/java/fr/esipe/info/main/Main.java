package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;
import fr.umlv.zen5.Application;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(new BufferedReader(
                new InputStreamReader(Level.class.getResourceAsStream("/levels/level00.txt"))).lines().collect(Collectors.joining("\n")));

        Application.run(new Color(0f, 0f, 0f, .0f), context -> {

            EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_00.getFileName());
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
