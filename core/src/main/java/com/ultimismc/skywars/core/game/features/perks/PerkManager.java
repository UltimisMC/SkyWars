package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.game.features.PurchasableRepository;
import com.ultimismc.skywars.core.game.features.perks.impl.*;
import com.ultimismc.skywars.core.game.features.soulwell.HarvestingSeasonSoulPerk;
import com.ultimismc.skywars.core.game.features.soulwell.XezbethLuckSoulPerk;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class PerkManager implements FeatureInitializer, PurchasableRepository<Perk> {

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

        // SOUL WELL PERKS
        registerPerks(new HarvestingSeasonSoulPerk(), new XezbethLuckSoulPerk());

        perks.sort((perk, perk2) -> {
            PurchasableRarity rarity1 = perk.getRarity();
            PurchasableRarity rarity2 = perk2.getRarity();
            return Integer.compare(rarity2.getPriority(), rarity1.getPriority());
        });
    }

    public Perk getPerk(String name) {
        return perksMap.get(name);
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

    @Override
    public Map<String, Perk> getPurchasables() {
        return perksMap;
    }
}
