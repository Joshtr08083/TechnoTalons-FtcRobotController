package org.firstinspires.ftc.teamcode.utilities;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.ArrayList;


public class Controller  {

    private Gamepad gamepad1;
    private ArrayList<Button> buttons = new ArrayList<>();

    public Controller(Gamepad gamepad1) {
        this.gamepad1 = gamepad1;
    }


    public void update() {

    }
}
