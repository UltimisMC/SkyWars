package com.ultimismc.skywars.core.game.kits;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface Kit {

    String getName();

    KitRarity getRarity();

    List<KitItem> getItems();

    int getPrice();


}
