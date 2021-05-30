package fr.esipe.info.game;

import fr.esipe.info.manager.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {
    BufferedImage image;

    public Sprite(String filename) {
        Objects.requireNonNull(filename);
        try {
            this.image = ImageIO.read(Objects.requireNonNull(Sprite.class.getResourceAsStream(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics, int x, int y) {
        var cellSize = GameManager.getInstance().getCellSize();
        graphics.drawImage(image, y * cellSize, x * cellSize, cellSize, cellSize, null);
    }
}
