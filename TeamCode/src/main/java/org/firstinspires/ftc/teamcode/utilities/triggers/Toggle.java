package org.firstinspires.ftc.teamcode.utilities.triggers;

public class Toggle implements TriggerBehavior {
    private final Runnable onToggleOn, onToggleOff;
    private boolean prevState = false;
    private boolean toggleState = false;

    public Toggle(Runnable onToggleOn, Runnable onToggleOff) {
        this.onToggleOn = onToggleOn;
        this.onToggleOff = onToggleOff;
    }

    public void update(boolean state) {
        if (state && !prevState) {
            toggleState = !toggleState;
            if (toggleState) {
                onToggleOn.run();
            } else {
                onToggleOff.run();
            }
        }
        prevState = state;
    }
}
