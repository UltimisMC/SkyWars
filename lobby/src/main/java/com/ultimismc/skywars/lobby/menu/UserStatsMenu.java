package com.ultimismc.skywars.lobby.menu;

import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.game.TeamType;
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
        int totalWins = userStatistics.getTotalWins();
        int totalLosses = userStatistics.getTotalLosses();
        int totalKills = userStatistics.getTotalKills();
        int totalAssists = userStatistics.getTotalAssists();
        int totalDeaths = userStatistics.getTotalDeaths();
        int totalBowKills = userStatistics.getTotalBowKills();
        int totalVoidKills = userStatistics.getTotalVoidKills();
        int totalArrowsShot = userStatistics.getTotalArrowShot();
        int totalArrowsHit = userStatistics.getTotalArrowHits();
        int totalChestsOpened = userStatistics.getTotalChestsOpened();

        MenuItem allModes = new MenuItem(Material.PAPER, ChatColor.GREEN + "All Modes Statistics");
        allModes.setLore(getStatisticsLore(totalWins, totalLosses, totalKills, totalAssists, totalDeaths, totalBowKills, totalVoidKills,
                totalArrowsShot, totalArrowsHit, totalChestsOpened));

        setSlot(4, allModes);

        int[] modeSlots = {20, 24};
        int currentIndex = 0;
        for(Map.Entry<TeamType, GameStatistics> statisticsEntry : userStatistics.getGameStats().entrySet()) {
            TeamType teamType = statisticsEntry.getKey();
            GameStatistics statistics = statisticsEntry.getValue();
            int wins = statistics.getWins();
            int losses = statistics.getLosses();
            int kills = statistics.getKills();
            int assists = statistics.getAssists();
            int deaths = statistics.getDeaths();
            int bowKills = statistics.getBowKills();
            int voidKills = statistics.getVoidKills();
            int arrowsShot = statistics.getArrowsShot();
            int arrowsHit = statistics.getArrowsHit();
            int chestsOpened = statistics.getChestsOpened();

            List<String> statisticsLore = getStatisticsLore(wins, losses, kills, assists, deaths, bowKills, voidKills, arrowsShot, arrowsHit, chestsOpened);
            MenuItem menuItem = new MenuItem(Material.PAPER, ChatColor.GREEN + teamType.getName() + " Statistics");
            menuItem.setLore(statisticsLore);

            int modeSlot = modeSlots[currentIndex];
            setSlot(modeSlot, menuItem);
            currentIndex++;
        }
        addCloseButton();
    }

    private List<String> getStatisticsLore(int wins, int losses, int kills, int assists, int deaths, int bowKills, int voidKills, int arrowsShot, int arrowsHit, int chestsOpened) {
        MessageConfigKeys statisticsLore = MessageConfigKeys.SKYWARS_STATISTICS_LORE;
        return statisticsLore.getStringList(new Replacement("wins", wins),
                new Replacement("losses", losses),
                new Replacement("kills", kills),
                new Replacement("assists", assists),
                new Replacement("deaths", deaths),
                new Replacement("bow-kills", bowKills),
                new Replacement("void-kills", voidKills),
                new Replacement("arrows-shot", arrowsShot),
                new Replacement("arrows-hit", arrowsHit),
                new Replacement("chests-opened", chestsOpened));
    }
}
