package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utilities.Controller;
import org.firstinspires.ftc.teamcode.utilities.triggers.Trigger;

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

@TeleOp
public class Primary extends LinearOpMode {
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor frontLeft;
    private DcMotor frontRight;

    // speed modifier for wheels
    private double speed = 1.0;
    private double flyWheelSpeed = 1.0;

    // intake/shooting
    private DcMotor flyWheel;
    private DcMotor intake;
    private CRServo intakeServoL;
    private CRServo intakeServoR;
    private Servo flicker;
    private boolean intakeMotorActive = false;
    private boolean intakeServoActive = false;
    private int intakeDirection = 1;
    private boolean flyWheelActive = false;

    // manage event hooks for controller triggers
    private final Controller controller = new Controller();

    private void initialize() {
        frontLeft = hardwareMap.get(DcMotor.class, "front-left");
        frontRight = hardwareMap.get(DcMotor.class, "front-right");
        backRight = hardwareMap.get(DcMotor.class, "back-right");
        backLeft = hardwareMap.get(DcMotor.class, "back-left");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        intake = hardwareMap.get(DcMotor.class, "intake");
        intakeServoL = hardwareMap.get(CRServo.class, "intake-servo-L");
        intakeServoR = hardwareMap.get(CRServo.class, "intake-servo-R");
        flicker = hardwareMap.get(Servo.class, "flicker");
        flicker.setPosition(0);
        flyWheel = hardwareMap.get(DcMotor.class, "fly-wheel");


        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        double servoMax = 0.6;
        double servoMin = 0.4;

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            // omni wheel drive
            mecanumDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            // reverse intake direction
            controller.add(new Trigger(() -> gamepad1.left_trigger, 0.5)
                    .onHeld(() -> intakeDirection = -1,
                            () -> intakeDirection = 1));

            // intake motor toggle
            controller.add(new Trigger(() -> gamepad1.a)
                    .onRisingEdge(() -> intakeMotorActive = !intakeMotorActive));

            // intake servos toggle
            controller.add(new Trigger(() -> gamepad1.dpad_down)
                    .onRisingEdge(() -> intakeServoActive = !intakeServoActive));

            // flywheel enable
            controller.add(new Trigger(() -> gamepad1.right_trigger > 0.7)
                    .onRisingEdge(() -> flyWheelActive = true)
                    .onHeld(()->{}, () -> flyWheelActive = false));

            // flicker (hold)
            controller.add(new Trigger(() -> gamepad2.dpad_up)
                    .onHeld(() -> flicker.setPosition(servoMax),
                            () -> flicker.setPosition(servoMin)));

            // slow mode for drive
            controller.add(new Trigger(() -> gamepad2.x))
                    .onRisingEdge(() -> speed = (speed >= 1)?0.3:1);

            // slow mode for flywheels
            controller.add(new Trigger(() -> gamepad2.y)
                    .onRisingEdge(() -> flyWheelSpeed = (flyWheelSpeed >= 1)?0.5:1));


            controller.update();
            flyWheel.setPower(flyWheelActive?flyWheelSpeed:0);
            intakeServoL.setPower((intakeServoActive)? -1 * intakeDirection: 0);
            intakeServoR.setPower((intakeServoActive)? intakeDirection: 0);
            intake.setPower((intakeMotorActive)? -1 * intakeDirection: 0);

            telemetry.addData("Intake Motor", (intakeMotorActive)? "Active": "Inactive");
            telemetry.addData("Intake Servos", (intakeServoActive)? "Active": "Inactive");
            telemetry.addData("Intake Direction", (intakeDirection == 1)? "Pull": "Push");
            telemetry.addData("Speed", speed);
            telemetry.addData("flywheelSpeed", flyWheelSpeed);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

    void mecanumDrive(double strafe, double drive, double twist) {
        drive *= -1;

        double[] speeds = {
                (drive + strafe + twist), // FL
                (drive - strafe - twist), // FR
                (drive - strafe + twist), // BL
                (drive + strafe - twist) // BR
        };
        double max = Math.abs(speeds[0]); // normalize values
        for (double v : speeds) {
            if (max < Math.abs(v)) max = Math.abs(v);
        }
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }
        frontLeft.setPower(speeds[0] * speed);
        frontRight.setPower(speeds[1] * speed);
        backLeft.setPower(speeds[2] * speed);
        backRight.setPower(speeds[3] * speed);
    }
}
