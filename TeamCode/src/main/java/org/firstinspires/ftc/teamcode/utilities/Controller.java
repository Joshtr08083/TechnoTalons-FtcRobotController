package org.firstinspires.ftc.teamcode.utilities;

import org.firstinspires.ftc.teamcode.utilities.triggers.Trigger;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final List<Trigger> triggers = new ArrayList<>();

    public Trigger add(Trigger trigger) {
        triggers.add(trigger);
        return trigger;
    }

    public void update() {
        for (Trigger t : triggers) {
            t.update();
        }
    }
}
