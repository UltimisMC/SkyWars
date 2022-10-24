package com.ultimismc.skywars.lobby.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.config.replacement.ReplacementBoundary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum ShopMessageKeys implements ConfigEntry {


    KITS_PERKS_CATEGORY_LORE("kits-perks.category-lore", Arrays.asList("&7Change the way you play by",
            "&7picking kits and perks!",
            " ",
            "&7Wins kits and perks in the &bSoul",
            "&bWell &7or buy them directly",
            "&7using &6coins&7.")),

    SOUL_WELL_CATEGORY_LORE("soul-well.category-lore", Arrays.asList("&7Use &bSouls &7to roll the well ",
            "&7and win kits & perks!",
            " ",
            "&7Earn &bSouls &7by killing players",
            "&7in SkyWars. This category contains",
            "&bSoul Harvesting &7and &bSoul",
            "&bUpgrading&7.",
            " ",
            "&7Souls: &b%souls%")),
    MY_COSMETICS_CATEGORY_LORE("my-cosmetics.category-lore", Arrays.asList("&7Browse and equip all the",
            "&7available in-game SkyWars",
            "&7cosmetics.",
            " ",
            "&7Find cosmetics in &6Spooky",
            "&6Chests &7or buy them directly",
            "&7using &2tokens&7.")),
    LEVEL_PROGRESSION_CATEGORY_LORE("level-progression.category-lore", Arrays.asList("&7View information about your",
            "&7SkyWars Level progression,",
            "&7select your Prestige Icon, and",
            "&7view level rewards.",
            " ",
            "&7Progress: &b%current-progress%&7/&a%maximum-progress%",
            "%current-prestige% %progress-bar% %next-prestige%")),
    ;

    private final String key;
    @Setter
    private Object value;
    private boolean forceEntryDeclaration;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    ShopMessageKeys(String key, Object value) {
        this(key, value, true);
    }

}
