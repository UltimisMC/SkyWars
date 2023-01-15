package com.ultimismc.skywars.game.handler.team;

import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.TagUtil;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;

/**
 * @author DirectPlan
 */
public class GameTeamHandler {

    private final String[] alphabets;
    @Getter private final LinkedList<GameTeam> gameTeams = new LinkedList<>();

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

    public void addGameTeam(GameTeam gameTeam) {
        gameTeams.add(gameTeam);
    }

    public void removeGameTeam(GameTeam gameTeam) {
        gameTeams.remove(gameTeam);
    }

    public void handleTeamJoin(UserGameSession userGameSession) {
        GameTeam availableTeam = getAvailableTeam();
        if(!gameTeams.contains(availableTeam)) {
            addGameTeam(availableTeam);
        }
        availableTeam.addPlayer(userGameSession);

        userGameSession.setGameTeam(availableTeam);
    }

    public void handleTeamQuit(UserGameSession userGameSession) {
        GameTeam gameTeam = userGameSession.getGameTeam();
        if(gameTeam == null) return; // Strange
        gameTeam.removePlayer(userGameSession);
        if(gameTeam.isEmpty()) removeGameTeam(gameTeam);

        Player player = userGameSession.getPlayer();
        TagUtil.clearTag(player);
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

    public GameTeam getLastTeamAlive() {
        for(GameTeam gameTeam : gameTeams) {
            System.out.println("Game Team " + gameTeam.getTagGroup() + ": " + gameTeam.getPlayers().values() + " (" + gameTeam.isAlive() + ")");
        }

        Optional<GameTeam> lastTeamOptional = gameTeams.stream().filter(GameTeam::isAlive).findFirst();
        if(!lastTeamOptional.isPresent()) {
            throw new RuntimeException("Could not find any alive team. Game Teams is empty? Size: " + gameTeams.size());
        }
        return lastTeamOptional.get();
    }

    public int getTeamsLeft() {
        int teamsLeft = 0;
        for(GameTeam gameTeam : gameTeams) {
            if(!gameTeam.isAlive()) continue;
            teamsLeft++;
        }
        return teamsLeft;
    }

    public void setupTeamTag(UserGameSession user) {
        Player player = user.getPlayer();
        GameTeam gameTeam = user.getGameTeam();
        if(gameTeam == null) return;

        String tagGroup = gameTeam.getTagGroup();
        for(UserGameSession online : gameHandler.getUserSessions()) {
            Player other = online.getPlayer();
            if(user.isSpectator()) {
                TagUtil.setTag(player, other, "SPEC", ChatColor.GRAY + "");
                continue;
            }
            ChatColor tagColor = ChatColor.RED;
            String tag = "";
            if(gameTeam.isMember(online)) {
                tagColor = ChatColor.GREEN;
            }
            if(gameHandler.isSoloGame()) tag = "[" + tagGroup + "] ";

            TagUtil.setTag(player, other, tagGroup, tagColor + tag);
        }
    }

    private String getCode(int index) {
        return alphabets[index];
    }
}
