package fr.esipe.info.game;

import fr.esipe.info.manager.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {
    BufferedImage image;
    Color colorTheme = Color.RED;

    public Sprite(String filename, Color colorTheme) {
        Objects.requireNonNull(filename);
        try {
            this.image = ImageIO.read(Objects.requireNonNull(Sprite.class.getResourceAsStream(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.colorTheme = colorTheme;
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (image.getRGB(i, j) == Color.WHITE.getRGB()) {
                    image.setRGB(i, j, colorTheme.getRGB());
                }
            }
        }
    }

    public void draw(Graphics2D graphics, int x, int y) {
        var cellSize = GameManager.getInstance().getCellSize();
        paintComponent(graphics);
        graphics.drawImage(image, y * cellSize, x * cellSize, cellSize, cellSize, null);
    }
}
