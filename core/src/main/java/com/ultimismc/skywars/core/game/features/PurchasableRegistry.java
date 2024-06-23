package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author DirectPlan
 */
public abstract class PurchasableRegistry<T extends Purchasable> implements FeatureInitializer, PurchasableRepository<T>, Iterable<T> {

    protected final Map<String, T> purchasables = new LinkedHashMap<>();
    protected final LinkedList<T> sortedPurchasables = new LinkedList<>();

    @Setter protected T defaultPurchasable;
    @Getter protected final String settingKey;

    public PurchasableRegistry(String settingKey) {
        this.settingKey = settingKey;
    }

    public T getPurchasable(String name) {
        return purchasables.get(name);
    }

    @SafeVarargs
    public final void registerPurchasables(T... purchasables) {
        for(T purchasable : purchasables) {
            registerPurchasable(purchasable);
        }
    }

    public void registerPurchasable(T purchasable) {
        purchasables.put(purchasable.getName(), purchasable);
        sortedPurchasables.add(purchasable);
        if(purchasable.isDefault()) {
            this.defaultPurchasable = purchasable;
        }
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {

        sortedPurchasables.sort((perk, perk2) -> {
            PurchasableRarity rarity1 = perk.getRarity();
            PurchasableRarity rarity2 = perk2.getRarity();
            return Integer.compare(rarity2.getPriority(), rarity1.getPriority());
        });
        log(plugin, "Loaded a total of " + getSize() + " " + getName() + ".");
    }

    public int getSize() {
        return purchasables.size();
    }

    @Override
    public Map<String, T> getPurchasables() {
        return purchasables;
    }

    @Override
    public Iterator<T> iterator() {
        return sortedPurchasables.iterator();
    }
}
