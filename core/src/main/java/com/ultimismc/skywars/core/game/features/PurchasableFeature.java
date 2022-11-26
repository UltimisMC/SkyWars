package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author DirectPlan
 */
public abstract class PurchasableFeature<T extends Purchasable> implements FeatureInitializer, PurchasableRepository<T>, Iterable<T> {

    protected final Map<String, T> purchasables = new HashMap<>();
    protected final LinkedList<T> orderedPurchasables = new LinkedList<>();

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
        orderedPurchasables.add(purchasable);
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {

        orderedPurchasables.sort((perk, perk2) -> {
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

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return orderedPurchasables.iterator();
    }
}
