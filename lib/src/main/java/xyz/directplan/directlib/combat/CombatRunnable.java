package xyz.directplan.directlib.combat;

import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class CombatRunnable implements Runnable {

    private final CombatManager<?> combatManager;

    @Override
    public void run() {
        Map<UUID, LinkedList<Combat>> combatMap = combatManager.getCombatMap();
        for(Map.Entry<UUID, LinkedList<Combat>> entry : combatMap.entrySet()) {
            LinkedList<Combat> combats = entry.getValue();
            combats.removeIf(Combat::hasExpired);
        }
    }
}
