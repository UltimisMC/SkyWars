package xyz.directplan.directlib;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.omg.CORBA.BAD_CONTEXT;

import java.lang.reflect.Constructor;

/**
 * @author DirectPlan
 */
public class TitleUtil {

    private static final Class<?> PACKET_CLASS;
    private static final Class<?> CHAT_BASE_COMPONENT;
    private static final Class<?> PACKET_PLAY_OUT_TITLE;

    static {
        PACKET_CLASS = getMCClass("Packet");
        CHAT_BASE_COMPONENT = getMCClass("IChatBaseComponent");
        PACKET_PLAY_OUT_TITLE = getMCClass("PacketPlayOutTitle");


    }

    public static void send(Player player, String title, String subTitle) {
        send(player, 3, 4, 2, title, subTitle);
    }

    public static void send(Player player, int fadeInTime, int showTime, int fadeOutTime, String title, String subtitle) {
        try {
            Object chatTitle = CHAT_BASE_COMPONENT.getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + title + "\"}");
            Constructor<?> titleConstructor = PACKET_PLAY_OUT_TITLE.getConstructor(
                    PACKET_PLAY_OUT_TITLE.getDeclaredClasses()[0], CHAT_BASE_COMPONENT,
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    PACKET_PLAY_OUT_TITLE.getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    fadeInTime, showTime, fadeOutTime);

            Object chatsTitle = CHAT_BASE_COMPONENT.getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + subtitle + "\"}");
            Constructor<?> stitleConstructor = PACKET_PLAY_OUT_TITLE.getConstructor(
                    PACKET_PLAY_OUT_TITLE.getDeclaredClasses()[0], CHAT_BASE_COMPONENT,
                    int.class, int.class, int.class);
            Object spacket = stitleConstructor.newInstance(
                    PACKET_PLAY_OUT_TITLE.getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    fadeInTime, showTime, fadeOutTime);

            sendPacket(player, packet);
            sendPacket(player, spacket);
        } catch (Exception ignored) {
        }
    }

    private static void sendPacket(Player player, Object packet) {
        (((CraftPlayer)player)).getHandle().playerConnection.sendPacket((Packet<?>) packet);
    }

    private static Class<?> getMCClass(String name) {
        try {
            return Class.forName("net.minecraft.server"
                    + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException ignored) {
        }
        return null;
    }

}
