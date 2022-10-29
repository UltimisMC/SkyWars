package com.ultimismc.skywars.lobby.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.LobbyManager;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@CommandAlias("purchases")
public class PurchasesCommand extends BaseCommand {

    @Dependency
    private LobbyManager lobbyManager;

    @Default
    public void onPurchases(User user) {
        lobbyManager.openPurchasesMenu(user);
    }

    @CommandAlias("viewpurchases")
    @CommandPermission("ultimismc.skywars.admin")
    @Syntax("<player>")
    public void onPurchaseOther(Player player, @Flags("other") User target) {
        lobbyManager.openPurchasesMenu(player, target);
    }
}
