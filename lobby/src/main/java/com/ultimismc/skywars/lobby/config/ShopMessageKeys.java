package com.ultimismc.skywars.lobby.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum ShopMessageKeys implements ConfigEntry {

    SHOP_ITEM_STATUS_INSUFFICIENT_FUNDS("shop-item.status.insufficient-funds", "&cYou cannot afford this."),
    SHOP_ITEM_STATUS_CLICK_TO_PURCHASE("shop-item.status.click-to-purchase", "&eClick to purchase!"),
    SHOP_ITEM_PURCHASED_MESSAGE("shop-item.purchased-message", "&aYou've purchased &e%name% &afor &6%price%&a."),
    SHOP_ITEM_INSUFFICIENT_FUNDS_MESSAGE("shop-item.insufficient-funds-message", "&cYou don't have enough %currency% to buy that!"),

    KITS_PERKS_CATEGORY_LORE("kits-perks.category-lore", Arrays.asList("&7Change the way you play by",
            "&7picking kits and perks!",
            " ",
            "&7Wins kits and perks in the &bSoul",
            "&bWell &7or buy them directly",
            "&7using &6coins&7.")),
    PERKS_SLOTS_ITEM_LORE("kits-perks.perks.perks-slots.lore",
            Arrays.asList("&7You can have up to &a6 &7Perks",
                    "&7active at any time.",
                    "&7The slots &cto right &7of",
                    "&7this icon indicate your",
                    "&7currently active perks.",
                    "&eRight-click &7on a perk to",
                    "&7disable it, and make room to",
                    "&7activate another perk.",
                    "&eLeft-click &7on a perk to",
                    "&7replace it directly with another",
                    "&7perk.",
                    "&7Click on any of your",
                    "&apurchased perks &7to enable",
                    "&7them if you have an available",
                    "&7slot.")),
    PERK_EQUIPPED_MESSAGE("kits-perks.perks.equipped-message", "&aYou have equipped your &e%name% &aperk!"),
    PERK_UN_EQUIPPED_MESSAGE("kits-perks.perks.un-equipped-message", "&cYou have unequipped your &e%name% &cperk!"),
    PERK_SLOTS_MAXED_MESSAGE("kits-perks.perks.perk-slots-maxed-message", "&cYou have no available Perk Slots!"),

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

    COSMETIC_TRAILS_LORE("my-cosmetics.projectile-trails-lore",
            Arrays.asList("&7Change your projectile particle",
                    "&7trail effect.")),
    COSMETIC_CAGES_LORE("my-cosmetics.cages-lore",
            Arrays.asList("&7Change the color of your",
                    "&7spawning cell.")),

    COSMETIC_VICTORY_DANCES_LORE("my-cosmetics.victory-dances-lore",
            Arrays.asList("&7Celebrate by gloating and",
                    "&7showing off to other players",
                    "&7whenever you win!")),
    COSMETIC_KILL_EFFECTS_LORE("my-cosmetics.kill-effects-lore",
            Arrays.asList("&7Pick an effect to trigger",
                    "&7whenever you eliminate another",
                    "&7player!")),
    COSMETIC_DEATH_CRIES_LORE("my-cosmetics.death-cries-lore",
            Arrays.asList("&7Let others know just how salty",
                    "&7your tears are every time you",
                    "&7die with these death cries!")),
    COSMETIC_BALLONS_LORE("my-cosmetics.ballons-lore",
            Arrays.asList("&7Attach a ballon to your spawn",
                    "&7island!")),
    COSMETIC_KILL_MESSAGES_LORE("my-cosmetics.kill-messages-lore",
            Arrays.asList("&7Select a Kill Message package to",
                    "&7replace chat messages when you",
                    "&7kill players!")),
    COSMETIC_SPRAY_LORE("my-cosmetics.sprays-lore",
            Arrays.asList("&7Select a spray to show off all",
                    "&7over the place! Spray slots can",
                    "&7be found around the center",
                    "&7islands."))

    ;

    private final String key;
    @Setter
    private Object value;
}
