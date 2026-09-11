package org.firstinspires.ftc.teamcode.utilities.triggers;

public class RisingEdge implements TriggerBehavior {
    private final Runnable onTrigger;
    private boolean prevState = false;
    public RisingEdge(Runnable onTrigger) {
        this.onTrigger = onTrigger;
    }

    public void update(boolean state) {
        if (state && prevState) onTrigger.run();
        prevState = state;
    }
}
