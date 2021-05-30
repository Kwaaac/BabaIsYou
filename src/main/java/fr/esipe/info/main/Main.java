package fr.esipe.info.main;

import fr.esipe.info.files.EncryptionDecorator;
import fr.esipe.info.game.enums.Level;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;
import fr.umlv.zen5.Application;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        AudioInputStream audioInputStream;
        try {
            System.out.println(Main.class.getResourceAsStream("/Music/baba.wav"));
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Main.class.getResourceAsStream("/Music/baba.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        Application.run(new Color(0f, 0f, 0f, 1f), context -> {
            GameManager gameManager = GameManager.getInstance();
            gameManager.setHeight((int) context.getScreenInfo().getHeight());
            gameManager.setWidth((int) context.getScreenInfo().getWidth());

            EncryptionDecorator encoded = new EncryptionDecorator(Level.LEVEL_00.getFileStream());
            LevelManager levelManager = new LevelManager("bob", encoded);
            gameManager.setLevelManager(levelManager);
            context.renderFrame(levelManager::render);


            while (levelManager.processEvent(context) && !levelManager.isLose() && !levelManager.isWin()) {

            }

            context.exit(0);
        });

    }
}
