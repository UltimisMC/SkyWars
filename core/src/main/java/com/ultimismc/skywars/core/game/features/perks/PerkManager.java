package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEventHandler;
import com.ultimismc.skywars.core.game.features.perks.impl.*;
import com.ultimismc.skywars.core.game.features.soulwell.HarvestingSeasonSoulPerk;
import com.ultimismc.skywars.core.game.features.soulwell.XezbethLuckSoulPerk;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class PerkManager extends PurchasableRegistry<Perk> {

    private final String name = "Perks";

    private PerkEventHandler perkEventHandler;

    public PerkManager() {
        super("perk");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        perkEventHandler = new PerkEventHandler(plugin);

        registerPurchasables(new BridgerPerk(), new KnowledgePerk(), new LuckyCharmPerk(), new MiningExpertisePerk(), new NourishmentPerk(), new AnnoyOMitePerk(), new ArrowRecoveryPerk(),
                new BlazingArrowsPerk(), new EnvironmentalExpertPerk(), new SpeedBoostPerk(), new BarbarianPerk(), new BlackMagicPerk(), new FrostPerk(),
                new MarksmanshipPerk(), new NecromancerPerk(), new RobberyPerk(),
                new BulldozerPerk(), new FatPerk(), new JuggernautPerk(), new ResistanceBoostPerk(), new RevengePerk(),
                new SaviorPerk());

        // SOUL WELL PERKS
        registerPurchasables(new HarvestingSeasonSoulPerk(), new XezbethLuckSoulPerk());

        // Initializing & registering perk event listener
        perkEventHandler.initializeFeature(plugin);
        super.initializeFeature(plugin);
    }

    @Override
    public void registerPurchasable(Perk perk) {
        if(perk instanceof PerkEvent) {
            PerkEvent<?> perkEvent = (PerkEvent<?>) perk;
            perkEventHandler.addPerkEvent(perkEvent);
        }
        super.registerPurchasable(perk);
    }
}
