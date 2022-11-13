package com.ultimismc.skywars.lobby.shop.soulwell.roll;

import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SoulWellRollAnimatorClock implements Runnable {

    private final SoulWellRollMenu soulWellRollMenu;

    private double speed = 0.0;
    private double progress = 0.0;

    @Override
    public void run() {
        progress++;
        if(progress >= speed) {
            speed += 0.1;
            if(speed >= 2.5) {
                speed += 0.4;
            }
            if(speed >= 5) {
                speed += 1;
            }
            progress = 0;
            soulWellRollMenu.update();
            return;
        }

        if(speed >= 12) {
            soulWellRollMenu.stopRoll();
        }
    }
}
