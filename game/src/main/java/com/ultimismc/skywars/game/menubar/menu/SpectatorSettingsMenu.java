package com.ultimismc.skywars.game.menubar.menu;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.setting.UserSettingHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.Locale;
import java.util.function.Consumer;

/**
 * @author DirectPlan
 */
public class SpectatorSettingsMenu extends InventoryUI {

    private final User user;

    public SpectatorSettingsMenu(User user) {
        super("Spectator Settings", 4);

        this.user = user;
    }

    @Override
    public void build(Player player) {

        setSlot(11, new MenuItem(Material.LEATHER_BOOTS, "&aNo Speed", (item, clicker, clickedBlock, clickType) -> clicker.removePotionEffect(PotionEffectType.SPEED)));
        setSlot(12, new MenuItem(Material.CHAINMAIL_BOOTS, "&aSpeed I", (item, clicker, clickedBlock, clickType) -> giveSpeed(clicker, 1)));
        setSlot(13, new MenuItem(Material.IRON_BOOTS, "&aSpeed II", (item, clicker, clickedBlock, clickType) -> giveSpeed(clicker, 2)));
        setSlot(14, new MenuItem(Material.GOLD_BOOTS, "&aSpeed III", (item, clicker, clickedBlock, clickType) -> giveSpeed(clicker, 3)));
        setSlot(15, new MenuItem(Material.DIAMOND_BOOTS, "&aSpeed IV", (item, clicker, clickedBlock, clickType) -> giveSpeed(clicker, 4)));

        MenuItem autoTeleport = spectatorFeature(Material.COMPASS, "Auto Teleport", "spectator.auto-teleport", enabled -> {
            if(enabled) {
                user.sendMessage("&aOnce you select a player using your compass, it will auto teleport you to them!");
                return;
            }
            user.sendMessage("&cYou will no longer auto teleport to targets!");
        });
        MenuItem nightVision = spectatorFeature(Material.ENDER_PEARL, "Night Vision", "spectator.night-vision", enabled -> {
            if(enabled) {
                user.sendMessage("&aYou now have night vision!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 5));
                return;
            }
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            user.sendMessage("&cYou no longer have night vision!");
        });
        MenuItem firstPerson = spectatorFeature(Material.WATCH, "First Person", "spectator.first-person", enabled -> {
            if(enabled) {
                user.sendMessage("&aYou will now by default use First Person spectating!");
                return;
            }
            user.sendMessage("&cYou will now by default use Third Person spectating!");
        });
        MenuItem otherSpectators = spectatorFeature(Material.REDSTONE, "Other Spectators", "Show", "Hide", "spectator.other-spectators", enabled -> {
            if(enabled) {
                user.sendMessage("&aYou can now see other spectators!");
                return;
            }
            user.sendMessage("&cYou can no longer see other spectators!");
        });

        setSlot(20, autoTeleport);
        setSlot(21, nightVision);
        setSlot(23, firstPerson);
        setSlot(24, otherSpectators);
    }

    private MenuItem spectatorFeature(Material material, String name, String settingKey, Consumer<Boolean> consumer) {
        return spectatorFeature(material, name, "Enable", "Disable", settingKey, consumer);
    }

    private MenuItem spectatorFeature(Material material, String name, String enableAction, String disableAction, String settingKey, Consumer<Boolean> consumer) {
        UserSettingHandler userSettingHandler = user.getUserSettingHandler();
        boolean enabled = userSettingHandler.isActivated(settingKey);
        String displayName = (enabled ? "&c" + disableAction : "&a" + enableAction) + " " + name;

        MenuItem menuItem = new MenuItem(material, displayName, (item, clicker, clickedBlock, clickType) -> {
            userSettingHandler.setSetting(settingKey, !enabled);
            refresh(user.getPlayer());
            consumer.accept(!enabled);
        });
        menuItem.setLore("&7Click to " + (enabled ? disableAction.toLowerCase(Locale.ROOT) : enableAction.toLowerCase(Locale.ROOT)) + " " + name.toLowerCase(Locale.ROOT) + "!");
        return menuItem;
    }
    private void giveSpeed(Player player, int level) {
        player.removePotionEffect(PotionEffectType.SPEED);
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, level - 1);
        player.addPotionEffect(potionEffect);
    }
}
