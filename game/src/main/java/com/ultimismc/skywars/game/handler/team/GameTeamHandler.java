package com.ultimismc.skywars.game.handler.team;

import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.TagUtil;

import java.util.LinkedList;
import java.util.Locale;

/**
 * @author DirectPlan
 */
public class GameTeamHandler {

    private final String[] alphabets;
    private final LinkedList<GameTeam> gameTeams = new LinkedList<>();

    private final GameHandler gameHandler;

    public GameTeamHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;

        char[] alphabetsChars = "abcdefghijklmnopqrstuvwxyz".toUpperCase(Locale.ROOT).toCharArray();
        alphabets = new String[alphabetsChars.length];
        for(int alphabetIndex = 0; alphabetIndex < alphabetsChars.length; alphabetIndex++) {
            char alphabetChar = alphabetsChars[alphabetIndex];
            String alphabet = Character.toString(alphabetChar);
            alphabets[alphabetIndex] = alphabet;
        }
    }

    public void handleTeamJoin(UserGameSession userGameSession) {
        GameTeam availableTeam = getAvailableTeam();
        if(!gameTeams.contains(availableTeam)) {
            gameTeams.add(availableTeam);
        }
        availableTeam.addPlayer(userGameSession);

        userGameSession.setGameTeam(availableTeam);

        // Tab shit
        User user = userGameSession.getUser();
        user.sendMessage("&aTeam Tag Group: &e" + availableTeam.getTagGroup());
        setupTeamTag(userGameSession);
    }

    public void handleTeamQuit(UserGameSession userGameSession) {
        GameTeam gameTeam = userGameSession.getGameTeam();
        if(gameTeam == null) return; // Strange
        gameTeam.removePlayer(userGameSession);
        if(gameTeam.isEmpty()) gameTeams.remove(gameTeam);
    }

    private GameTeam getAvailableTeam() {
        GameTeam lastTeam = null;
        for(GameTeam gameTeam : gameTeams) {
            lastTeam = gameTeam;
            if(gameTeam.isFull()) continue;
            return gameTeam;
        }
        // Create a new one.
        TeamType teamType = gameHandler.getTeamType();
        int maximumTeam = teamType.getMaximumTeam();
        String alphabet = alphabets[0];
        if(lastTeam != null) {
            int index = gameTeams.lastIndexOf(lastTeam);
            alphabet = getCode(index + 1);
        }
        return new GameTeam(alphabet, maximumTeam);
    }

    private void setupTeamTag(UserGameSession user) {
        Player player = user.getPlayer();
        GameTeam gameTeam = user.getGameTeam();
        if(gameTeam == null) return;

        String tagGroup = gameTeam.getTagGroup();
        for(UserGameSession online : gameHandler.getUserSessions()) {
            ChatColor tagColor = ChatColor.RED;
            String tag;
            if(gameTeam.isMember(online)) {
                tagColor = ChatColor.GREEN;
            }
//            if(gameHandler.isSoloGame()) tag = "[" + tagGroup + "]";
            tag = "[" + tagGroup + "]";
            TagUtil.setTag(player, online.getPlayer(), tagGroup, tagColor + tag);
        }
    }

    private String getCode(int index) {
        return alphabets[index];
    }
}
