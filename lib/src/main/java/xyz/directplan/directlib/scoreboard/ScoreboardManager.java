package xyz.directplan.directlib.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.scoreboard.impl.FastScoreboardImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author DirectPlan
 */
public class ScoreboardManager {

    private final Map<UUID, DirectScoreboard> scoreboards = new HashMap<>();

    private final Executor asyncExecutor;

    public ScoreboardManager(JavaPlugin plugin, String threadName, Runnable... scoreboardUpdaters) {

        asyncExecutor = Executors.newFixedThreadPool(15, r -> {
            Thread thread = new Thread(r);
            thread.setName(threadName);
            return thread;
        });

        for(Runnable runnable : scoreboardUpdaters) {
            plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 20L, 20L);
        }
    }

    public DirectScoreboard createScoreboard(Player player, String displayName, List<String> lines) {
        FastScoreboardImpl scoreboard = new FastScoreboardImpl(player);
        updateScoreboard(scoreboard, displayName, lines);
        return scoreboard;
    }

    public DirectScoreboard sendScoreboard(Player player, String displayName, List<String> lines) {
        UUID uuid = player.getUniqueId();
        DirectScoreboard scoreboard = scoreboards.get(uuid);
        if(scoreboard == null) {
            scoreboard = new FastScoreboardImpl(player);
            scoreboards.put(uuid, scoreboard);
        }
        updateScoreboard(scoreboard, displayName, lines);
        return scoreboard;
    }

    public void updateScoreboard(UUID uuid, String displayName, List<String> lines) {
        DirectScoreboard scoreboard = scoreboards.get(uuid);
        if(scoreboard == null) return;

        updateScoreboard(scoreboard, displayName, lines);
    }

    public void updateScoreboard(DirectScoreboard scoreboard, String displayName, List<String> lines) {
        if(scoreboard.isAsyncSupported()) {
            asyncExecutor.execute(() -> updateScoreboard0(scoreboard, displayName, lines));
            return;
        }
        updateScoreboard0(scoreboard, displayName, lines);
    }

    public void updateLine(DirectScoreboard scoreboard, int line, String content) {
        scoreboard.updateLine(line, content);
    }

    public void updateLine(UUID uuid, int line, String content) {
        DirectScoreboard scoreboard = scoreboards.get(uuid);
        if(scoreboard != null) {
            updateLine(scoreboard, line, content);
        }
    }

    private void updateScoreboard0(DirectScoreboard scoreboard, String displayName, List<String> lines) {
        if(displayName != null) {
            scoreboard.updateDisplayName(PluginUtility.translateMessage(displayName));
        }
        if(lines != null) {
            scoreboard.update(lines);
        }
    }

    public void removeScoreboard(UUID uuid) {
        DirectScoreboard scoreboard = scoreboards.remove(uuid);
        if(scoreboard == null) return;
        removeScoreboard(scoreboard);
    }

    public void removeScoreboard(DirectScoreboard scoreboard) {
        if(scoreboard.isAsyncSupported()) {
            asyncExecutor.execute(scoreboard::remove);
            return;
        }
        scoreboard.remove();
    }
}
