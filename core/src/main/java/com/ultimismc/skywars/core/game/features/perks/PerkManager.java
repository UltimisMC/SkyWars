package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import com.ultimismc.skywars.core.game.features.perks.impl.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class PerkManager implements FeatureInitializer {

    private final String name = "Perks";

    private final Map<String, Perk> perksMap = new HashMap<>();
    private final List<Perk> perks = new ArrayList<>();

    public PerkManager(SkyWarsPlugin plugin) {
    }


    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPerks(new BridgerPerk(), new KnowledgePerk(), new LuckyCharmPerk(), new MiningExpertisePerk(), new NourishmentPerk(), new AnnoyOMitePerk(), new ArrowRecoveryPerk(),
                new BlazingArrowsPerk(), new EnvironmentalExpertPerk(), new SpeedBoostPerk(), new BarbarianPerk(), new BlackMagicPerk(), new FrostPerk(),
                new MarksmanshipPerk(), new NecromancerPerk(), new RobberyPerk(),
                new BulldozerPerk(), new FatPerk(), new JuggernautPerk(), new ResistanceBoostPerk(), new RevengePerk(),
                new SaviorPerk());

        perks.sort((perk, perk2) -> {
            PerkRarity rarity1 = perk.getRarity();
            PerkRarity rarity2 = perk2.getRarity();
            return Integer.compare(rarity2.getPriority(), rarity1.getPriority());
        });
    }


    public void registerPerk(Perk perk) {
        perksMap.put(perk.getName(), perk);
        perks.add(perk);
    }

    public void registerPerks(Perk... perks) {
        for(Perk perk : perks) {
            registerPerk(perk);
        }
    }
}
