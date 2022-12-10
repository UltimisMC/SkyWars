package xyz.directplan.directlib.misc.hologram;

import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import xyz.directplan.directlib.PluginUtility;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Hologram {

    private final List<ArmorStand> armorStands = new ArrayList<>();
    private final Location location;

    public List<String> getLines() {
        List<String> list = new ArrayList<>();
        for (ArmorStand armorStand : armorStands) {
            list.add(armorStand.isCustomNameVisible() ? armorStand.getName() : "");
        }
        return list;
    }

    public void updateLines(String... lines) {
        if (lines.length == 0) {
            destroy();
            return;
        }
        for(int i = 0; i < lines.length; i++) {
            lines[i] = PluginUtility.translateMessage(lines[i]);
        }
        int i = 0;
        if (lines.length >= armorStands.size()) {
            Location lastLocation = location.clone();
            for (ArmorStand armorStand : armorStands) {
                if (lines[i].isEmpty()) {
                    armorStand.setCustomNameVisible(false);
                } else {
                    armorStand.setCustomName(lines[i]);
                }
                lastLocation = armorStand.getLocation();
                i++;
            }
            for (int j = i; j < lines.length; j++) {
                String line = lines[j];

                Location updatedLastLocation = lastLocation.clone().add(0, -.3, 0);
                ArmorStand armorStand = (ArmorStand) location.getWorld()
                        .spawnEntity(updatedLastLocation, EntityType.ARMOR_STAND);
                armorStand.setGravity(false);
                armorStand.setSmall(true);
                armorStand.setVisible(false);
                armorStand.setCustomNameVisible(!line.isEmpty());
                armorStand.setCustomName(line);
                armorStand.setCanPickupItems(false);
                armorStand.setRemoveWhenFarAway(true);
                armorStands.add(armorStand);
                lastLocation = updatedLastLocation;
            }
        } else {
            int size = armorStands.size();
            for (String line : lines) {
                ArmorStand armorStand = armorStands.get(i);
                armorStand.setCustomNameVisible(!line.isEmpty());
                armorStand.setCustomName(line);
                i++;
            }
            for (int j = i; j < size; j++) {
                armorStands.remove(j).remove();
            }
        }
    }

    public void destroy() {
        armorStands.forEach(ArmorStand::remove);
        armorStands.clear();
    }
}