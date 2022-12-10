package xyz.directplan.directlib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author DirectPlan
 */
public class TagUtil {

    private final static String TAG_PREFIX = "skywars_team_";

    public static void setTag(Player player, Player other, String group, String tag) {
        Scoreboard scoreboard = player.getScoreboard();
        if(scoreboard == null) {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        String teamTag = TAG_PREFIX + group;
        Team team = scoreboard.getTeam(teamTag);
        if(team == null) {
            team = scoreboard.registerNewTeam(teamTag);
        }
        team.setPrefix(tag);

        String otherName = other.getName();
        if(team.hasEntry(otherName)) {
            return;
        }
        team.addEntry(otherName);

        player.setScoreboard(scoreboard);
    }
}
