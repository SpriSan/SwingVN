package managers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourceRegister {

    private static final Map<String, BufferedImage> images = new HashMap<>();

    private ResourceRegister() {}

    public static BufferedImage getImage(String name) {
        if (!images.containsKey(name)) {
            loadImage(name);
        }
        return images.get(name);
    }

    private static void loadImage(String name) {
        try {
            String path = "/images/" + name + ".png";
            BufferedImage img = ImageIO.read(ResourceRegister.class.getResource(path));
            images.put(name, img);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Impossible de charger l'image : " + name);
            e.printStackTrace();
        }
    }
}

