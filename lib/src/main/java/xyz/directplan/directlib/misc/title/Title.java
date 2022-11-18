package xyz.directplan.directlib.misc.title;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {

    private String title, subTitle;
    private final TitleSlot titleSlot;
    private int fadeIn, stay, fadeOut;

    public Title(TitleSlot slot) { this.titleSlot = slot; }

    public Title setTitle(String arg0) { this.title = arg0; this.subTitle = ""; return this; }

    public Title setSubTitle(String arg0) { this.subTitle = arg0; return this; }

    public Title setFadeIn(int arg0) { this.fadeIn = arg0; return this; }

    public Title setStay(int arg0) { this.stay = arg0; return this; }

    public Title setFadeOut(int arg0) { this.fadeOut = arg0; return this; }

    public void send(Player player) {
        if(title == null)
            throw new IllegalArgumentException("Title cannot be null");
        if(titleSlot == TitleSlot.TITLE) {
            try {
                IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
                IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");

                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle);
                PacketPlayOutTitle length = new PacketPlayOutTitle(fadeIn, stay, fadeOut);


                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
            } catch (Exception exception) {
            }
        } else if(titleSlot == TitleSlot.ACTIONBAR) {
            PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(title), (byte)2);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

}