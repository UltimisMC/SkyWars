package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.storage.UserStorage;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @author DirectPlan
 */
public class UserManager {

    @Getter private final Map<UUID, User> users = new HashMap<>();

    private final SkyWarsPlugin plugin;

    private final UserStorage storage;
    private final ExecutorService executorService;

    public UserManager(SkyWarsPlugin plugin) {
        this.plugin = plugin;
        storage = plugin.getStorage();

        executorService = Executors.newFixedThreadPool(10);
    }

    public User loadUser(UUID uuid) {
        User user = users.get(uuid);
        if(user == null) {
            user = storage.loadUser(uuid);
        }
        return user;
    }

    public void handleJoin(Player player) {
        UUID uuid = player.getUniqueId();
        plugin.getLogger().info("Loading user " + player.getName() + "...");
        loadUserAsync(uuid, true, user -> user.setPlayer(player));
    }

    public void handleQuit(Player player) {
        User removed = users.remove(player.getUniqueId());
        removed.setOnline(false);
        saveUserAsync(removed, false, null);
    }



    public void loadUserAsync(UUID uuid, boolean cache, Consumer<User> syncTask) {
        CompletableFuture.supplyAsync(() -> {
            long time = System.currentTimeMillis();
            User user = loadUser(uuid);
            if(cache) {
                cacheUser(user);
            }
            plugin.getLogger().info(user.getName() + " has taken " +(System.currentTimeMillis() - time) +" ms to load from storage!");
            return user;
        }, executorService).thenAccept(output -> MinecraftServer.getServer().postToMainThread(() -> syncTask.accept(output)));
    }

    public void saveUserAsync(User user, boolean remove, Consumer<User> syncTask) {
        CompletableFuture.supplyAsync(() -> {
            long time = System.currentTimeMillis();
            if(remove) {
                User savedUser = saveUserAndRemove(user.getUuid());
                plugin.getLogger().info(user.getName() + " took "+(System.currentTimeMillis() - time)+" ms to save data on database & remove");
                return savedUser;
            }
            saveUser(user);

            plugin.getLogger().info(user.getName() + " took "+(System.currentTimeMillis() - time)+" ms to save data on database");
            return user;
        }, executorService).thenAccept(output -> MinecraftServer.getServer().postToMainThread(() -> syncTask.accept(output)));
    }

    public void saveUserAsync(UUID uuid, boolean remove, Consumer<User> syncTask) {
        User user = getCachedUser(uuid);
        saveUserAsync(user, remove, syncTask);
    }


    public User getCachedUser(UUID uuid) {
        return users.get(uuid);
    }

    public User getCachedUser(Player player) {
        return getCachedUser(player.getUniqueId());
    }

    public void cacheUser(User user) {
        users.put(user.getUuid(), user);
    }

    public void saveUser(User user) {
        storage.saveUser(user);
    }

    /* Only trigger when the server is under the shutting down state */
    public void saveUsers() {
        users.forEach((uuid, user) -> saveUser(user));
    }

    /* Only triggers when the User logged out */
    public User saveUserAndRemove(UUID uuid) {
        User user = users.remove(uuid);
        if(user != null) {
            saveUser(user);
        }
        return user;
    }
}
