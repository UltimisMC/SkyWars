package xyz.directplan.directlib.scoreboard;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface DirectScoreboard {

    void updateDisplayName(String displayName);

    void update(List<String> lines);

    void update(String... lines);

    void updateLine(int line, String content);

    void remove();

    boolean isAsyncSupported();
}
