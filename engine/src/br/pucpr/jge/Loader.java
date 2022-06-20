package br.pucpr.jge;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Loader {
    private String root;

    public Loader(String root) {
        this.root = root;
    }

    public Loader() {
        this("/assets");
    }

    BufferedImage createErrorImage() {
        var img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        var g2d = img.getGraphics();
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(0, 0, 100, 100);
        g2d.dispose();

        return img;
    }

    public BufferedImage loadImage(String path) {
        var name = root + path;
        try {
            var image = ImageIO.read(getClass().getResourceAsStream(name));
            if (GameManager.getInstance().imagesLoaded.containsKey(name)){
                return (BufferedImage) GameManager.getInstance().imagesLoaded.get(name);
            }
            GameManager.getInstance().imagesLoaded.put(name, image);
            return (BufferedImage) GameManager.getInstance().imagesLoaded.get(name);
        } catch (Exception e) {
            System.err.println("Unable to load " + name);
            return createErrorImage();
        }
    }
}