package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Configs.Globals.Robot.LAUNCHER_MIN_VELOCITY;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Robot.LAUNCHER_TARGET_VELOCITY;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

@TeleOp(name = "Teleop", group = "StarterBot")
//@Disabled
public class Teleop extends OpMode {
    Robot robot;
    ////as;lekfjh;laweifjahsef

    double intakePower;
    boolean motorOn = false;
    boolean intakeOn = false;
    boolean reverseOn = false;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        // Field-centric drive
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                robot.follower.pose().heading()
        );

        robot.follower.manual(powers);
        robot.periodic();

        // Intake controls
        if (gamepad1.rightTriggerWasPressed()) {


        } else if (gamepad1.leftTriggerWasPressed()) {
            intakePower = -0.5;
            robot.intake.setPower(intakePower);
            robot.leftIntakeServo.setPower(intakePower);
            robot.rightIntakeServo.setPower(intakePower);

        } else {
            intakePower = 0;
            robot.intake.setPower(intakePower);
            robot.leftIntakeServo.setPower(intakePower);
            robot.rightIntakeServo.setPower(intakePower);
        }

        // Intake toggle
        if (gamepad1.leftBumperWasPressed()) {
            intakeOn = !intakeOn;

            if (intakeOn) {
                reverseOn = false;
            }
        }

        // Reverse toggle
        if (gamepad1.leftTriggerWasPressed()) {
            reverseOn = !reverseOn;

            if (reverseOn) {
                intakeOn = false;
            }
        }

        if (intakeOn) {
            intakePower = 1.0;
        } else if (reverseOn) {
            intakePower = -0.5;
        } else {
            intakePower = 0;
        }

        robot.intake.setPower(intakePower);
        robot.leftIntakeServo.setPower(intakePower);
        robot.rightIntakeServo.setPower(intakePower);

        // Launcher controls
        launch();

        // Telemetry
        telemetry.addData("Triggers", "left (%.2f), right (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
        telemetry.addData("Heading", "%.1f°", Math.toDegrees(robot.follower.pose().heading()));
        telemetry.addData("Loop Hz", robot.getLoopTimeHz());
        telemetry.update();
    }

    @Override
    public void stop() {
    }

    // Controls the launcher and windmill
    void launch() {
        if (gamepad1.yWasPressed()) {
            motorOn = !motorOn;
        }

        if(motorOn) {
            robot.launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);
        } else {
            robot.launcher.setVelocity(0);
        }

        if (gamepad1.right_bumper) {
            robot.windmillServo.setPower(1);
            intakePower += 1;
        } else {
            robot.windmillServo.setPower(0);
        }
    }
}
