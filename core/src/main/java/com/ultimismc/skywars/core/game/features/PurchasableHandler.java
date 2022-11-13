package com.ultimismc.skywars.core.game.features;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class PurchasableHandler {

    private final Map<String, Purchasable> serverPurchasables = new HashMap<>();

    public <T extends Purchasable> void registerPurchasableRepository(PurchasableRepository<T> purchasableRepository) {
        Map<String, T> purchasables = purchasableRepository.getPurchasables();
        serverPurchasables.putAll(purchasables);
    }

    public void addPurchasable(String key, Purchasable purchasable) {
        serverPurchasables.put(key, purchasable);
    }

    public void addPurchasable(Purchasable purchasable) {
        addPurchasable(purchasable.getName(), purchasable);
    }

    public Purchasable getPurchasable(String key) {
        return serverPurchasables.get(key);
    }

    public <T extends Purchasable> T getPurchasable(Class<T> clazz, String key) {
        Purchasable purchasable = getPurchasable(key);
        if(purchasable == null) return null;
        return clazz.cast(purchasable);
    }

    public Collection<Purchasable> getAllPurchasables() {
        return serverPurchasables.values();
    }
}
