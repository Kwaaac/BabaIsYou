package fr.esipe.info.game;

import fr.esipe.info.manager.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {
    private BufferedImage image;
    private Color colorTheme;
    private boolean painted = false;

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
        if (painted) {
            return;
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                var imgRGB = image.getRGB(i, j);
                if (imgRGB == Color.WHITE.getRGB()) {
                    image.setRGB(i, j, colorTheme.getRGB());
                }
            }
        }
        painted = true;
    }

    public void draw(Graphics2D graphics, int x, int y) {
        var gm = GameManager.getInstance();
        var cellSize = gm.getCellSize();
        var widthDelta = gm.getWidthDelta();
        var heightDelta = gm.getHeightDelta();
        paintComponent(graphics);
        graphics.drawImage(image, y * cellSize + widthDelta, x * cellSize + heightDelta, cellSize, cellSize, null);
    }
}
