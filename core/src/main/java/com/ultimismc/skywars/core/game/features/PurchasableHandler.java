package com.ultimismc.skywars.core.game.features;

import lombok.Getter;

import java.util.*;

/**
 * @author DirectPlan
 */
public class PurchasableHandler {

    private final Map<String, Purchasable> serverPurchasables = new HashMap<>();
    @Getter private final List<Purchasable> defaultPurchasables = new ArrayList<>();

    public <T extends Purchasable> void registerPurchasableRepository(PurchasableRepository<T> purchasableRepository) {
        Map<String, T> purchasables = purchasableRepository.getPurchasables();
        purchasables.forEach(this::addPurchasable);
    }

    public void addPurchasable(String key, Purchasable purchasable) {
        serverPurchasables.put(key, purchasable);
        if(purchasable.isDefault()) {
            defaultPurchasables.add(purchasable);
        }
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
