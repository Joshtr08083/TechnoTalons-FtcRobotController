package org.firstinspires.ftc.teamcode.utilities.triggers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Trigger {
    private final BooleanSupplier input;
    private final List<TriggerBehavior> behaviors = new ArrayList<>();

    public Trigger(BooleanSupplier input) {
        this.input = input;
    }
    public Trigger onRisingEdge(Runnable onTrigger) {
        behaviors.add(new RisingEdge(onTrigger));
        return this;
    }

    public Trigger onFallingEdge(Runnable onTrigger) {
        behaviors.add(new FallingEdge(onTrigger));
        return this;
    }

    public Trigger onHeld(Runnable whileHeld, Runnable whileReleased) {
        behaviors.add(new Held(whileHeld, whileReleased));
        return this;
    }

    public Trigger onHeld(Runnable whileHeld) {
        return this.onHeld(whileHeld, () -> {});
    }

    public Trigger onToggle(Runnable onToggleOn, Runnable onToggleOff) {
        behaviors.add(new Toggle(onToggleOn, onToggleOff));
        return this;
    }

    public Trigger onTrigger(Runnable onToggleOn) {
        return this.onToggle(onToggleOn, ()->{});
    }

    public void update() {
        boolean state = input.getAsBoolean();
        for (TriggerBehavior b: behaviors) {
            b.update(state);
        }
    }

}
