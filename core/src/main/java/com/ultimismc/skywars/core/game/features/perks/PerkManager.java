package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableFeature;
import com.ultimismc.skywars.core.game.features.perks.impl.*;
import com.ultimismc.skywars.core.game.features.soulwell.HarvestingSeasonSoulPerk;
import com.ultimismc.skywars.core.game.features.soulwell.XezbethLuckSoulPerk;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class PerkManager extends PurchasableFeature<Perk> {

    private final String name = "Perks";

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasables(new BridgerPerk(), new KnowledgePerk(), new LuckyCharmPerk(), new MiningExpertisePerk(), new NourishmentPerk(), new AnnoyOMitePerk(), new ArrowRecoveryPerk(),
                new BlazingArrowsPerk(), new EnvironmentalExpertPerk(), new SpeedBoostPerk(), new BarbarianPerk(), new BlackMagicPerk(), new FrostPerk(),
                new MarksmanshipPerk(), new NecromancerPerk(), new RobberyPerk(),
                new BulldozerPerk(), new FatPerk(), new JuggernautPerk(), new ResistanceBoostPerk(), new RevengePerk(),
                new SaviorPerk());

        // SOUL WELL PERKS
        registerPurchasables(new HarvestingSeasonSoulPerk(), new XezbethLuckSoulPerk());

        super.initializeFeature(plugin);
    }
}
