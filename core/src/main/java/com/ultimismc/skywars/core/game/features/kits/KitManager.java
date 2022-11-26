package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableFeature;
import com.ultimismc.skywars.core.game.features.kits.impl.*;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class KitManager extends PurchasableFeature<Kit> {

    private final String name = "Kits";

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasables(new DefaultKit(), new ArmorerKit(), new ArmorsmithKit(), new BatguyKit(), new CactusKit(), new DiscoKit(), new EcologistKit(),
                new FrogKit(), new GrenadeKit(), new HealerKit(), new KnightKit(), new ProKit(), new ScoutKit(), new BaseballPlayerKit(),
                new EnchanterKit(), new EnderchestKit(), new EngineerKit(), new FarmerKit(), new FishermanKit(), new HunterKit(), new MagicianKit(),
                new PharoahKit(), new PigRiderKit(), new PrincessKit(), new SlothKit(), new SnowmanKit(), new SpeleologistKit(), new WarlockKit(),
                new CannoneerKit(), new RookieKit(), new EnergixKit());

        super.initializeFeature(plugin);
    }
}
