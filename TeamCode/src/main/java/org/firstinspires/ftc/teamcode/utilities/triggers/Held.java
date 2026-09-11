package org.firstinspires.ftc.teamcode.utilities.triggers;

public class Held implements TriggerBehavior {
    private final Runnable whileHeld, whileReleased;

    public Held(Runnable whileHeld, Runnable whileReleased) {
        this.whileHeld = whileHeld;
        this.whileReleased = whileReleased;
    }

    public void update(boolean state) {
        if (state) {
            whileHeld.run();
        } else {
            whileReleased.run();
        }
    }
}
