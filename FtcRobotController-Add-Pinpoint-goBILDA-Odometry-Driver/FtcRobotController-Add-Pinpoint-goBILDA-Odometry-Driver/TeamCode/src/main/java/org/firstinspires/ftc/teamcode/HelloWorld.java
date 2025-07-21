package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="HelloWorld")
public class HelloWorld extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addLine("Hello, FTC!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("Running...");
            telemetry.update();
        }
    }
}