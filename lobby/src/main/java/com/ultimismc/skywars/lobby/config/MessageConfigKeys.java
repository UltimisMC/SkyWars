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
public enum MessageConfigKeys implements ConfigEntry {

    SKYWARS_LOBBY_SCOREBOARD_DISPLAYNAME("skywars-lobby.scoreboard.display-name", "&b&lSKYWARS"),
    SKYWARS_LOBBY_SCOREBOARD_LINES("skywars-lobby.scoreboard.lines",
            Arrays.asList("&7%servertime_MM/dd/yyyy% &8L21G",
                    " ",
                    "&fYour Level: &7%level%",
                    " ",
                    "&fSolo Kills: &a%solo-kills%",
                    "&fSolo Wins: &a%solo-wins%",
                    "&fDoubles Kills: &a%doubles-kills%",
                    "&fDoubles Wins: &a%doubles-wins%",
                    " ",
                    "&fCoins: &6%coins%",
                    "&fSouls: &b%souls%&7/%maximum-souls%",
                    " ",
                    "&3play.ultimismc.com"))

    ,


    SKYWARS_LOBBY_SHOP_ITEM_SLOT("skywars-lobby.shop-item.slot", 2),
    SKYWARS_LOBBY_SHOP_ITEM_DISPLAY_NAME("skywars-lobby.shop-item.display-name", "&aShop &7(Right Click)"),
    SKYWARS_LOBBY_SHOP_ITEM_LORE("skywars-lobby.shop-item.lore",
            Arrays.asList("&7Coins: &6%coins%")),


    SKYWARS_STATISTICS_LORE("skywars-lobby.statistics-lore",
            Arrays.asList("&7Wins: &a%wins%",
                    "&7Losses: &a%losses%",
                    " ",
                    "&7Kills: &a%kills%",
                    "&7Assists: &a%assists%",
                    "&7Deaths: &a%deaths%",
                    " ",
                    "&7Bow Kills: &a%bow-kills%",
                    "&7Void Kills: &a%void-kills%",
                    " ",
                    "&7Arrows Shot: &a%arrows-shot%",
                    "&7Arrows Hit: &a%arrows-hit%",
                    "&7Chests Opened: &a%chests-opened%")),

    LEVEL_LEVEL_SHOWN("progression-level.hide-level.showen", "&aYou are now showing your SkyWars level in chat!"),
    LEVEL_LEVEL_HIDDEN("progression-level.hide-level.hidden", "&aYou are now hiding your SkyWars level in chat!"),

    LEVEL_PRESTIGE_ICON_SUCCESS("progression-level.prestige-icon.success", "&aYou set your Prestige Icon to %prestige-icon%&a!"),
    LEVEL_PRESTIGE_ICON_ALREADY_SELECTED("progression-level.prestige-icon.already-selected", "&cYou have already selected this Prestige Icon!"),
    LEVEL_PRESTIGE_ICON_REQUIRED("progression-level.prestige-icon.required", "&cThis Prestige Icon requires %prestige%, unlocked at SkyWars Level %level%."),

    ;

    private final String key;
    @Setter
    private Object value;
}