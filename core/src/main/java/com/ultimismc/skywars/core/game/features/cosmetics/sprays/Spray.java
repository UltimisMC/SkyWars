package com.ultimismc.skywars.core.game.features.cosmetics.sprays;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import org.bukkit.entity.ItemFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author DirectPlan
 */
public abstract class Spray extends Cosmetic {

    private final BufferedImage image;

    public Spray(String name, CosmeticRarity rarity, String imageUrl) {
        super(name, rarity);

        File imageFile = new File("sprays/" + imageUrl);
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not load image " + imageUrl, e);
        }
    }

    public void drawSprayFrame(ItemFrame frame) {
        
    }
}
