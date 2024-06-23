package com.ultimismc.skywars.core.game.features.cosmetics.cages;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
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
import xyz.directplan.directlib.config.ConfigHandler;

import java.io.File;
import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public class CageHandler extends PurchasableRegistry<Cage> {

    private final String name = "Cages";

    private final SkyWarsPlugin plugin;
    private SchematicAdapter schematicAdapter;

    private Cage defaultCage;
    private CageSchematic voidSchematic;

    private final World gameWorld;

    public CageHandler(SkyWarsPlugin plugin) {
        super("cage");
        this.plugin = plugin;

        gameWorld = Bukkit.getWorld(ConfigKeys.WORLD_NAME.getStringValue());
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        // Checking for schematic adapter
        if(!pluginManager.isPluginEnabled("WorldEdit")) {
            plugin.disablePlugin("WorldEdit is not detected.");
            return;
        }

        ConfigHandler configHandler = plugin.getConfigHandler();
        configHandler.loadConfiguration("cages.yml", CageConfigKeys.class);

        schematicAdapter = new WorldEditSchematicAdapter(gameWorld);
        log(plugin, "Using " + schematicAdapter.getName() + " as a schematic adapter!");

        // Loading schematics
        log(plugin, "Verifying schematics folder...");
        File file = new File(plugin.getDataFolder(), "/cages/");
        if(file.mkdirs()) {
            log(plugin, "Schematics folder created!");
        }
        log(plugin, "Starting cage load sequence...");

        // Short-term solution for serializing objects. Will be improved in the future.
        List<String> serializedCages = CageConfigKeys.SERIALIZED_CAGES_DATA.getStringList();
        for(String serializedCage : serializedCages) {
            if(serializedCage.isEmpty()) continue;
            String[] cageArguments = serializedCage.split("/");

            String name = cageArguments[0];
            log(plugin, "Loading " + name + " cage...");

            Material material = Material.valueOf(cageArguments[1]);
            int durability = Integer.parseInt(cageArguments[2]);
            CosmeticRarity rarity = CosmeticRarity.valueOf(cageArguments[3]);
            PurchasableDesign design = new PurchasableDesign(material, durability);

            String fileName = name.toLowerCase(Locale.ROOT).replace(" ", "-") + "." + schematicAdapter.getFileType();

            Map<TeamType, CageSchematic> cageSchematics = loadCageSchematics(fileName);

            Cage cage = new Cage(cageSchematics, design, name, rarity);
            registerPurchasable(cage);
        }
        voidSchematic = schematicAdapter.loadSchematic(new File(plugin.getDataFolder(), "/cages/void.schematic"));
        defaultCage = getPurchasable("Default");
        defaultCage.setDefaultCage(true);

        super.initializeFeature(plugin);
    }

    private Map<TeamType, CageSchematic> loadCageSchematics(String cageFileName) {
        Map<TeamType, CageSchematic> cageSchematicMap = new HashMap<>();

        for(TeamType teamType : TeamType.values()) {
            String teamName = teamType.getName();
            File schematicFile = new File(plugin.getDataFolder(), "/cages/" + teamName.toLowerCase() + "/" + cageFileName);

            if(!schematicFile.exists()) {
                log(plugin, "Cage '" + name + "' does not have a " + teamName + " schematic. (" + schematicFile + ")");
                continue;
            }
            CageSchematic cageSchematic = schematicAdapter.loadSchematic(schematicFile);

            cageSchematicMap.put(teamType, cageSchematic);
        }
        return cageSchematicMap;
    }

    public void updateCageSchematic(User user, String name) {
        Cage cage = getPurchasable(name);
        if(cage == null) {
            user.sendMessage("&cA cage by this name does not exist!");
            return;
        }
        String schematicFileName = name.toLowerCase(Locale.ROOT).replace(" ", "-") + "." + schematicAdapter.getFileType();

        Map<TeamType, CageSchematic> updatedCageSchematics = loadCageSchematics(schematicFileName);

        cage.setSchematics(updatedCageSchematics);
        user.sendMessage("&aCage schematics for &e" + cage.getName() + " &ahas been updated!");
    }

    public void createCage(User user, String name, Material material, int durability, String rarityName) {
        if(getPurchasable(name) != null) {
            user.sendMessage("&cA cage by this name already exists!");
            return;
        }
        CosmeticRarity rarity;
        try {
            rarity = CosmeticRarity.valueOf(rarityName.toUpperCase());
        }catch (Exception ignored) {
            user.sendMessage("&cRarity '" + rarityName + "' does not exist.");
            return;
        }
        PurchasableDesign design = new PurchasableDesign(material, durability);

        String schematicFileName = name.toLowerCase(Locale.ROOT).replace(" ", "-") + "." + schematicAdapter.getFileType();

        user.sendMessage("&7Loading schematics...");
        Map<TeamType, CageSchematic> cageSchematics = loadCageSchematics(schematicFileName);

        Cage cage = new Cage(cageSchematics, design, name, rarity);
        registerPurchasable(cage);
        user.sendMessage("&aCage &e" + name + "&a has been added! Type &e/cage placecage " + name + " &ato test!");
    }

    public void placeCage(TeamType teamType, Cage cage, Location location, boolean ignoreAir) {
        log(plugin, "Placing schematic of " + cage.getName());
        cage.placeSchematic(teamType, location, ignoreAir);
    }

    public void placeCage(TeamType teamType, User user, Cage cage, boolean ignoreAir) {
        Player player = user.getPlayer();
        placeCage(teamType, cage, player.getLocation(), ignoreAir);
    }
    public void placeCage(TeamType teamType, User user, String cageName, boolean ignoreAir) {
        Cage cage = getPurchasable(cageName);
        if(cage == null) {
            user.sendMessage("&cA cage by this name does not exist!");
            return;
        }
        user.sendMessage("&aPlacing cage: &e" + teamType.getName() + " " + cage.getName() + "&a...");
        placeCage(teamType, user, cage, ignoreAir);
    }

    public void removeCage(Location location) {
        voidSchematic.placeSchematic(location, false);
    }

    @Override
    public void shutdownFeature(SkyWarsPlugin plugin) {
        log(plugin, "Starting cage save sequence...");

        // Short-term solution for serializing objects. Will be improved in the future.
        List<String> serializedCages = new ArrayList<>();
        for(Cage cage : purchasables.values()) {
            String name = cage.getName();
            PurchasableDesign design = cage.getDesign();
            Material material = design.getMaterial();
            int durability = design.getDurability();
            CosmeticRarity rarity = cage.getCosmeticRarity();
            serializedCages.add(name + "/" + material.name() + "/" + durability + "/" + rarity.name());
        }
        CageConfigKeys.SERIALIZED_CAGES_DATA.setValue(serializedCages);
    }
}
