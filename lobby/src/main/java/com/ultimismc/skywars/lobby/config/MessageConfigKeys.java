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
public enum MessageConfigKeys implements ConfigEntry {

    SKYWARS_LOBBY_SCOREBOARD_DISPLAYNAME("skywars.lobby.scoreboard.display-name", "&b&lSKYWARS"),
    SKYWARS_LOBBY_SCOREBOARD_LINES("skywars-lobby.scoreboard.lines",
            Arrays.asList(" %servertime_MMM dd, yyyy% &8L21G",
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
                    "&fTokens: &2%tokens%",
                    "&fLoot Chests: &e%loot-chests%",
                    " ",
                    "&3play.ultimismc.com"))

    ,


    SKYWARS_LOBBY_SHOP_ITEM_SLOT("skywars-lobby.shop-item.slot", 2),
    SKYWARS_LOBBY_SHOP_ITEM_DISPLAY_NAME("skywars-lobby.shop-item.display-name", "&aShop &7(Right Click)"),
    SKYWARS_LOBBY_SHOP_ITEM_LORE("skywars-lobby.shop-item.lore",
            Arrays.asList("&7Coins: &6%coins%")),

    ;

    private final String key;
    @Setter
    private Object value;
    private boolean forceEntryDeclaration;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    MessageConfigKeys(String key, Object value) {
        this(key, value, true);
    }
}