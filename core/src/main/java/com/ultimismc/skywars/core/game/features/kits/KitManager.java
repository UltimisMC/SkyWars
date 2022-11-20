package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableFeature;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.game.features.kits.impl.*;
import lombok.Getter;

import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public class KitManager implements PurchasableFeature<Kit> {

    private final String name = "Kits";

    private final Map<String, Kit> kitsMap = new HashMap<>();
    private final List<Kit> kits = new ArrayList<>();

    public KitManager(SkyWarsPlugin plugin) {
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerKits(new ArmorerKit(), new ArmorsmithKit(), new BatguyKit(), new CactusKit(), new DiscoKit(), new EcologistKit(),
                new FrogKit(), new GrenadeKit(), new HealerKit(), new KnightKit(), new ProKit(), new ScoutKit(), new BaseballPlayerKit(),
                new EnchanterKit(), new EnderchestKit(), new EngineerKit(), new FarmerKit(), new FishermanKit(), new HunterKit(), new MagicianKit(),
                new PharoahKit(), new PigRiderKit(), new PrincessKit(), new SlothKit(), new SnowmanKit(), new SpeleologistKit(), new WarlockKit(),
                new CannoneerKit(), new RookieKit(), new EnergixKit());

        // Why did I pinpoint it?
        kits.sort((kit1, kit2) -> {
            PurchasableRarity rarity1 = kit1.getRarity();
            PurchasableRarity rarity2 = kit2.getRarity();
            return Integer.compare(rarity2.getPriority(), rarity1.getPriority());
        });
        plugin.log("Loaded a total of " + kits.size() + " kits.");

    }

    public void registerKit(Kit kit) {
        kitsMap.put(kit.getName(), kit);
        kits.add(kit);
    }

    public void registerKits(Kit... kits) {
        for(Kit kit : kits) {
            registerKit(kit);
        }
    }

    public Kit getKit(String name) {
        return kitsMap.get(name);
    }

    public Collection<Kit> getKits() {
        return kits;
    }

    @Override
    public Map<String, Kit> getPurchasables() {
        return kitsMap;
    }
}
