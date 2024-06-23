package com.ultimismc.skywars.lobby.menu;

import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.user.Statistics;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class UserStatsMenu extends InventoryUI {

    private final User user;

    public UserStatsMenu(User user) {
        super("SkyWars Statistics", 5);

        this.user = user;
    }

    @Override
    public void build(Player player) {
        UserStatistics userStatistics = user.getStatistics();
        MenuItem allModes = new MenuItem(Material.PAPER, ChatColor.GREEN + "All Modes Statistics");
        allModes.setLore(getStatisticsLore(userStatistics));

        setSlot(4, allModes);

        int[] modeSlots = {20, 24};
        int currentIndex = 0;
        for(Map.Entry<TeamType, GameStatistics> statisticsEntry : userStatistics.getGameStats().entrySet()) {
            TeamType teamType = statisticsEntry.getKey();
            GameStatistics gameStatistics = statisticsEntry.getValue();

            List<String> statisticsLore = getStatisticsLore(gameStatistics);
            MenuItem menuItem = new MenuItem(Material.PAPER, ChatColor.GREEN + teamType.getName() + " Statistics");
            menuItem.setLore(statisticsLore);

            int modeSlot = modeSlots[currentIndex];
            setSlot(modeSlot, menuItem);
            currentIndex++;
        }
        addCloseButton();
    }

    private List<String> getStatisticsLore(Statistics statistics) {
        int totalWins = statistics.getWins();
        int totalLosses = statistics.getLosses();
        int totalKills = statistics.getKills();
        int totalAssists = statistics.getAssists();
        int totalDeaths = statistics.getDeaths();
        int totalBowKills = statistics.getBowKills();
        int totalVoidKills = statistics.getVoidKills();
        int totalArrowsShot = statistics.getArrowsShot();
        int totalArrowsHit = statistics.getArrowsHit();
        int totalChestsOpened = statistics.getChestsOpened();

        MessageConfigKeys statisticsLore = MessageConfigKeys.SKYWARS_STATISTICS_LORE;
        return statisticsLore.getStringList(new Replacement("wins", totalWins),
                new Replacement("losses", totalLosses),
                new Replacement("kills", totalKills),
                new Replacement("assists", totalAssists),
                new Replacement("deaths", totalDeaths),
                new Replacement("bow-kills", totalBowKills),
                new Replacement("void-kills", totalVoidKills),
                new Replacement("arrows-shot", totalArrowsShot),
                new Replacement("arrows-hit", totalArrowsHit),
                new Replacement("chests-opened", totalChestsOpened));
    }
}
