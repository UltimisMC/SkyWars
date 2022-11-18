package xyz.directplan.directlib;

import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import xyz.directplan.directlib.misc.title.Title;
import xyz.directplan.directlib.misc.title.TitleSlot;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author DirectPlan
 */
public class PluginUtility {

    private final static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static boolean hasChanceOccurred(int percentChance) {
        int randomPercentage = random.nextInt(100) + 1;
        return randomPercentage <= percentChance;
    }

    public static <T> T getRandom(List<T> collection) {
        int randomIndex = random.nextInt(collection.size());
        return collection.get(randomIndex);
    }

    public static String translateMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean isInventoryEmpty(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack[] contents = inventory.getContents();
        for(ItemStack content : contents) {
            if(content == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesLocationMatch(Location first, Location second) {
        int xf = (int) first.getX();
        int yf = (int) first.getY();
        int zf = (int) first.getZ();

        int xs = (int) second.getX();
        int ys = (int) second.getY();
        int zs = (int) second.getZ();

        return (xf == xs && yf == ys && zf == zs);
    }
    private static final FireworkEffect.Type[] effects = FireworkEffect.Type.values();

    public static void spawnCoolFirework(Player player) {
        spawnCoolFirework(player.getLocation());
    }
    public static void spawnCoolFirework(Location location) {

        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkEffect.Type randomEffect = effects[random.nextInt(effects.length)];

        FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(false)
                .withColor(Color.RED, Color.GREEN, Color.BLUE, Color.PURPLE).trail(false).with(randomEffect)
                .build();

        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        try {
            Field field = fireworkMeta.getClass().getDeclaredField("power");
            field.setAccessible(true);
            field.set(fireworkMeta, 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        fireworkMeta.addEffect(fireworkEffect);

        firework.setFireworkMeta(fireworkMeta);
    }

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    public static void spawnParticle(Location location, EnumParticle particle) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float) location.getX(),
                (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, 15);
        for (Player online : Bukkit.getOnlinePlayers()) {
            EntityPlayer entityPlayer = ((CraftPlayer)online).getHandle();
            PlayerConnection playerConnection = entityPlayer.playerConnection;
            playerConnection.sendPacket(packet);
        }
    }

    /**
     *  Checks cooldown
     *
     * @param lastTime
     * @param cooldownDelay
     * @return Seconds Left
     */
    public static int isOnCooldown(long lastTime, long cooldownDelay) {
        long timeLeft = ((lastTime + cooldownDelay) - System.currentTimeMillis());
        return (int) (timeLeft / 1000);
    }

    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String titleText, String subTitle) {
        Title title = new Title(TitleSlot.TITLE);
        title.setTitle(translateMessage(titleText));
        title.setSubTitle(translateMessage(subTitle));
        title.setFadeIn(fadeIn);
        title.setStay(stay);
        title.setFadeOut(fadeOut);
        title.send(player);
    }

    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title) {
        sendTitle(player, fadeIn, stay, fadeOut, title, "");
    }

    public static void sendSubTitle(Player player, int fadeIn, int stay, int fadeOut, String subTitle) {
        sendTitle(player, fadeIn, stay, fadeOut, "", subTitle);
    }

    public static void playChestAction(Chest chest, boolean open) {
        Location location = chest.getLocation();
        World world = ((CraftWorld) location.getWorld()).getHandle();
        BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
        TileEntityChest tileChest = (TileEntityChest) world.getTileEntity(position);
        world.playBlockAction(position, tileChest.w(), 1, open ? 1 : 0);
    }
}
