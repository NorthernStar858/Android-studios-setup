package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumDrive extends OpMode {

    DcMotor LFMotor;
    DcMotor LBMotor;
    DcMotor RFMotor;
    DcMotor RBMotor;

    @Override
    public void init() {
        // Hardware mapping
        RFMotor = hardwareMap.get(DcMotor.class, "RFMotor");
        LFMotor = hardwareMap.get(DcMotor.class, "LFMotor");
        LBMotor = hardwareMap.get(DcMotor.class, "LBMotor");
        RBMotor = hardwareMap.get(DcMotor.class, "RBMotor");

        // ✅ Correct motor directions for proper movement
        LFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RFMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        RBMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // ✅ Prevent gliding when power = 0
        LFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        // ✅ Standard mecanum joystick mapping
        double y = -gamepad1.left_stick_y;       // Forward/backward
        double x = gamepad1.left_stick_x;        // Strafe
        double turn = gamepad1.right_stick_x;    // Rotate

        // Optional: Deadzone filter
        if (Math.abs(y) < 0.05) y = 0;
        if (Math.abs(x) < 0.05) x = 0;
        if (Math.abs(turn) < 0.05) turn = 0;

        // ✅ Mecanum drive formula
        double lf = y + x - turn;
        double lb = y - x - turn;
        double rf = y - x + turn;
        double rb = y + x + turn;

        // Normalize if needed (ensure max power doesn't exceed 1.0)
        double max = Math.max(Math.max(Math.abs(lf), Math.abs(lb)),
                Math.max(Math.abs(rf), Math.abs(rb)));
        if (max > 1.0) {
            lf /= max;
            lb /= max;
            rf /= max;
            rb /= max;
        }

        // Apply powers
        LFMotor.setPower(lf);
        LBMotor.setPower(lb);
        RFMotor.setPower(rf);
        RBMotor.setPower(rb);
    }
}
