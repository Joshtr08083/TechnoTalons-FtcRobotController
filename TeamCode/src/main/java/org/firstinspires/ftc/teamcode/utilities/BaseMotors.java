package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
=============================
    Gamepad Controls
=============================
- BALLS -
    (A) TOGGLE - Intake Motor Cylinder Surgical Tubing Thing
    (DPAD_D) TOGGLE - Intake Servos Sides
    (LEFT_TRIGGER) HOLD - Reverse intake motor and servos
    (RIGHT_TRIGGER) HOLD - Flywheel motor

- Drive -
    (LEFT_STICK) - Forwards, Backwards, Strafe Left, Strafe Right
    (RIGHT_STICK) - Turn/Rotate/Pivot

*/

public class BaseMotors {
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    // speed modifier for wheels (NTS: Maybe add control for variable speed)

    // intake/shooting
    public DcMotor flyWheel;
    public DcMotor intake;
    public CRServo intakeServoL;
    public CRServo intakeServoR;
    public Servo flicker;

    public HardwareMap hwMap;
    public Telemetry telemetry;

    public BaseMotors(HardwareMap hwMap, Telemetry telemetry) {
        this.hwMap = hwMap;
        this.telemetry = telemetry;
    }

    public void initialize() {
        frontLeft = hwMap.get(DcMotor.class, "front-left");
        frontRight = hwMap.get(DcMotor.class, "front-right");
        backRight = hwMap.get(DcMotor.class, "back-right");
        backLeft = hwMap.get(DcMotor.class, "back-left");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        intake = hwMap.get(DcMotor.class, "intake");
        intakeServoL = hwMap.get(CRServo.class, "intake-servo-L");
        intakeServoR = hwMap.get(CRServo.class, "intake-servo-R");
        flicker = hwMap.get(Servo.class, "flicker");
        flicker.setPosition(0);
        flyWheel = hwMap.get(DcMotor.class, "fly-wheel");


        telemetry.addData("Status", "Initialized");
    }
}
