package org.firstinspires.ftc.teamcode.utilities;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class Button {
    public static final int MODE_RISING_EDGE = 0;
    public static final int MODE_HOLD = 1;
    public static final int MODE_TOGGLE = 2;

    private boolean pressed = false;
    private double threshold;
    private Runnable onClick;
    private Runnable onRelease;
    private Runnable onHold;
    private BooleanSupplier input;
    private DoubleSupplier doubleValue;
    private int mode;

    public Button(Runnable onClick, int mode, BooleanSupplier input) {
        this.mode = mode;
        this.onClick = onClick;
        this.input = input;
    }
    public Button(Runnable onHold, Runnable onRelease, DoubleSupplier doubleValue, double threshold) {
        this.mode = Button.MODE_HOLD;
        this.onHold = onHold;
        this.onClick = onRelease;
        this.input = () -> doubleValue.getAsDouble() > threshold;
    }

    private void checkRisingEdge() {
        if (input.getAsBoolean()) {
            if (!pressed) {
                pressed = true;
                onClick.run();
            }
        } else {
            pressed = false;
        }
    }

    private boolean checkHold() {
        return false;
    }
    public void check(boolean value) {

        switch(mode) {
            case 0:
               checkRisingEdge();
               break;
            case 1:
                checkHold();
                break;
            default:
                break;
        }

    }

}
