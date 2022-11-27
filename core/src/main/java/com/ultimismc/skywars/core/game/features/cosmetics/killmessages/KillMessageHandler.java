package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.impl.*;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class KillMessageHandler extends PurchasableRegistry<KillMessage> {

    private final String name = "Kill Messages";

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasable(new DefaultKillMessage()); // Done
        registerPurchasable(new FireKillMessage()); // Done
        registerPurchasable(new WesternKillMessage()); // Done
        registerPurchasable(new LoveKillMessage()); // Done
        registerPurchasable(new PirateKillMessage()); // Done
        registerPurchasable(new GalacticKillMessage()); // Done
        registerPurchasable(new ComputerKillMessage()); // Done
        registerPurchasable(new HonourableKillMessage()); // Done
        registerPurchasable(new FlexKillMessage()); // Done
        registerPurchasable(new RainbowKillMessage()); // Done
        registerPurchasable(new BBQKillMessage()); // Done
        registerPurchasable(new InsectoidKillMessage()); // Done
        registerPurchasable(new BananaKillMessage()); // Done
        registerPurchasable(new SqueakKillMessage()); // Done
        registerPurchasable(new OinkKillMessage()); // Done
        registerPurchasable(new BuzzKillMessage()); // Done
        registerPurchasable(new BoxingKillMessage()); // Done
        registerPurchasable(new OxdKillMessage()); // Done
        registerPurchasable(new MedievalKillMessage()); // Done
        registerPurchasable(new WOOFKillMessage()); // Done
        registerPurchasable(new MemedKillMessage()); // Done
        registerPurchasable(new SnowStormKillMessage()); // Done
        registerPurchasable(new EggyKillMessage()); // Done
        registerPurchasable(new ToTheMoonKillMessage()); // Done
        registerPurchasable(new RoarKillMessage()); // Done

//        defaultPurchasable = new kill message that doesn't have a killer message for users that died without a killer!
    }
}
