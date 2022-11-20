package com.ultimismc.skywars.core.game.features.cosmetics.cages;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.CageConfigKeys;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableFeature;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic.CageSchematic;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic.SchematicAdapter;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic.WorldEditSchematicAdapter;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public class CageHandler implements PurchasableFeature<Cage> {

    private final String name = "Cosmetics: Cage";

    private final SkyWarsPlugin plugin;
    private SchematicAdapter schematicAdapter;

    private final Map<String, Cage> cages = new HashMap<>();

    private final World world;
    private Cage defaultCage;
    private CageSchematic voidSchematic;

    public CageHandler(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        world = Bukkit.getWorld("world");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        // Checking for schematic adapter
        if(!pluginManager.isPluginEnabled("WorldEdit")) {
            plugin.shutdown("WorldEdit is not detected.");
            return;
        }
        schematicAdapter = new WorldEditSchematicAdapter(world);
        log(plugin, "Using " + schematicAdapter.getName() + " as a schematic adapter!");

        // Loading schematics
        log(plugin, "Verifying schematics folder...");
        File file = new File(plugin.getDataFolder(), "/cages/");
        if(file.mkdirs()) {
            log(plugin, "Schematics folder created!");
        }
        log(plugin, "Starting cage load sequence...");
        List<String> serializedCages = CageConfigKeys.SERIALIZED_CAGES_DATA.getStringList();
        for(String serializedCage : serializedCages) {
            if(serializedCage.isEmpty()) continue;
            String[] cageArguments = serializedCage.split("/");

            String name = cageArguments[0];
            log(plugin, "Loading " + name + " cage...");

            Material material = Material.valueOf(cageArguments[1]);
            int durability = Integer.parseInt(cageArguments[2]);
            CosmeticRarity rarity = CosmeticRarity.valueOf(cageArguments[3]);

            String schematicFileString = cageArguments[4];
            File schematicFile = new File(plugin.getDataFolder(), "/cages/" + schematicFileString);
            if(!schematicFile.exists()) {
                log(plugin, "Cage '" + name + "' does not have a schematic. (" + schematicFileString + ")");
                continue;
            }

            CageSchematic schematic = schematicAdapter.loadSchematic(schematicFile);
            PurchasableDesign design = new PurchasableDesign(material, durability);

            Cage cage = new Cage(schematic, design, name, rarity);
            registerCage(name, cage);
        }
        voidSchematic = schematicAdapter.loadSchematic(new File(plugin.getDataFolder(), "/cages/void.schematic"));
        defaultCage = getCage("Default");
        defaultCage.setDefaultCage(true);
    }

    @Override
    public void shutdownFeature(SkyWarsPlugin plugin) {
        log(plugin, "Starting cage save sequence...");
        List<String> serializedCages = new ArrayList<>();
        for(Cage cage : cages.values()) {
            String name = cage.getName();
            Material material = cage.getDisplayMaterial();
            int durability = cage.getDisplayDurability();
            CosmeticRarity rarity = cage.getCosmeticRarity();

            String schematicFile = name.toLowerCase(Locale.ROOT).replace(" ", "-") + "." + schematicAdapter.getFileType();

            serializedCages.add(name + "/" + material.name() + "/" + durability + "/" + rarity.name() + "/" + schematicFile);
        }
        CageConfigKeys.SERIALIZED_CAGES_DATA.setValue(serializedCages);
    }

    public void updateCageSchematic(User user, String name) {
        Cage cage = getCage(name);
        if(cage == null) {
            user.sendMessage("&cA cage by this name does not exist!");
            return;
        }
        String schematicFileName = name.toLowerCase(Locale.ROOT).replace(" ", "-") + "." + schematicAdapter.getFileType();
        File schematicFile = new File(plugin.getDataFolder(), "/cages/" + schematicFileName);
        if(!schematicFile.exists()) {
            user.sendMessage("&cCould not a schematic named '" + schematicFileName + "'");
            return;
        }
        user.sendMessage("&aLoading schematic...");
        CageSchematic schematic = schematicAdapter.loadSchematic(schematicFile);
        cage.setSchematic(schematic);
        user.sendMessage("&aCage schematic for &e" + cage.getName() + " &ahas been updated!");
    }

    public void createCage(User user, String name, Material material, int durability, String rarityName) {
        if(getCage(name) != null) {
            user.sendMessage("&cA cage by this name already exists!");
            return;
        }

        String schematicFileName = name.toLowerCase(Locale.ROOT).replace(" ", "-") + "." + schematicAdapter.getFileType();
        File schematicFile = new File(plugin.getDataFolder(), "/cages/" + schematicFileName);
        if(!schematicFile.exists()) {
            user.sendMessage("&cCould not a schematic named '" + schematicFileName + "'");
            return;
        }

        name = name.replace("-", " ");
        user.sendMessage("&aLoading schematic...");
        CageSchematic schematic = schematicAdapter.loadSchematic(schematicFile);
        CosmeticRarity rarity;
        try {
            rarity = CosmeticRarity.valueOf(rarityName.toUpperCase());
        }catch (Exception ignored) {

            return;
        }
        PurchasableDesign design = new PurchasableDesign(material, durability);
        Cage cage = new Cage(schematic, design, name, rarity);
        registerCage(cage);
        user.sendMessage("&aCage &e" + name + "&a has been added! Type &e/cage placecage " + name + " &ato test!");
    }

    public void registerCage(Cage cage) {
        registerCage(cage.getName(), cage);
    }

    public void registerCage(String name, Cage cage) {
        cages.put(name, cage);
    }

    public Cage getCage(String name) {
        return cages.get(name);
    }

    public void placeCage(Cage cage, Location location, boolean ignoreAir) {
        log(plugin, "Placing schematic of " + cage.getName());
        cage.placeSchematic(location, ignoreAir);
    }
    public void placeCage(User user, Cage cage, boolean ignoreAir) {
        Player player = user.getPlayer();
        placeCage(cage, player.getLocation(), ignoreAir);
    }

    public void placeCage(User user, String cageName, boolean ignoreAir) {
        Cage cage = getCage(cageName);
        if(cage == null) {
            user.sendMessage("&cA cage by this name does not exist!");
            return;
        }
        user.sendMessage("&aPlacing cage: &e" + cage.getName() + "&a...");
        placeCage(user, cage, ignoreAir);
    }

    public void removeCage(Location location) {
        voidSchematic.placeSchematic(location, false);
    }

    @Override
    public Map<String, Cage> getPurchasables() {
        return cages;
    }
}
