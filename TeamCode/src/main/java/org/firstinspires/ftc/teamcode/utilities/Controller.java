package org.firstinspires.ftc.teamcode.utilities;

import org.firstinspires.ftc.teamcode.utilities.triggers.Trigger;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    // all triggers
    private final List<Trigger> triggers = new ArrayList<>();

    // add a new trigger via
    // controller.add(new Trigger(gamepad1.a).onRisingEdge(this::foo));
    public Trigger add(Trigger trigger) {
        triggers.add(trigger);
        return trigger;
    }

    // check all triggers
    public void update() {
        for (Trigger t : triggers) {
            t.update();
        }
    }
}
