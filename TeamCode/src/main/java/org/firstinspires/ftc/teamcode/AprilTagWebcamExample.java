package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AprilTagWebcam;

import org.firstinspires.ftc.vision.arpiltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetection;

@Autonomous
public class AprilTagWebcamExample extends Primary{
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();

    @Override
    public void init();
        aprilTagWebcam.init(hardwareMap, telemtetry);
    }

    @Override
    public void loop() {
    // update the vision portal
        aprilTagWebcam.update();
        AprilTagDetection id20 = aprilTagWebcam.getTagBySpecificId(20);
        telemetry.addData("id20 String", id20.toString());


    }





}
