package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import lombok.Getter;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractPlaceholderExpansion extends PlaceholderExpansion {

    private final String identifier;
    private final String author = "DirectPlan";
    private final String version;

    public AbstractPlaceholderExpansion(SkyWarsPlugin plugin, String identifier) {
        this.identifier = identifier;
        version = plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequestPair(Player player, String key, String value) {
        return null;
    }

    public String onPlaceholderRequestArray(Player player, String[] params) {
        if(params.length == 0) return null;
        String key = null, value = null;

        if(params.length == 1) {
            key = params[0];
        }
        if(params.length >= 2) {
            value = params[1];
        }

        return onPlaceholderRequestPair(player, key, value);
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        String[] arrayParams = params.split("_");
        return onPlaceholderRequestArray(player, arrayParams);
    }
}
