package com.ultimismc.skywars.core.game.features.perks.event;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.events.UserSkyWarsEvent;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class PerkEventHandler implements FeatureInitializer {

    private final String name = "Perk Event Handler";

    private final Map<Class<? extends UserSkyWarsEvent>, List<PerkEvent<?>>> eventPerks = new HashMap<>();

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        plugin.registerListeners(new PerkEventListener(this));
    }

    public <T extends PerkEvent<?>> void addPerkEvent(Class<? extends UserSkyWarsEvent> eventClass, T perkEvent) {
        List<PerkEvent<?>> perkEvents = eventPerks.computeIfAbsent(eventClass, aClass -> new ArrayList<>());
        perkEvents.add(perkEvent);
    }

    public <T extends PerkEvent<?>> void addPerkEvent(T perkEvent) {
        addPerkEvent(perkEvent.getEventClass(), perkEvent);
    }

    public void triggerPerk(User user, UserSkyWarsEvent event) {
        List<PerkEvent<?>> perkEvents = eventPerks.get(event.getClass());

        for(PerkEvent perkEvent : perkEvents) {
            Perk perk = (Perk) perkEvent;

            UserAsset userAsset = user.getAsset(perk, event.getGameType());
            if(userAsset == null) continue;
            if(!userAsset.isActivated()) continue; // Checks if the perk is activated for the game type

            perkEvent.onTrigger(user, userAsset, event);
        }
    }
}
