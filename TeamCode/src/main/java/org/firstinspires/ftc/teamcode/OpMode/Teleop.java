package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.GOAL_POSE_BLUE;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.GOAL_POSE_RED;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.INTAKE_FORWARD;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.INTAKE_REVERSE;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.LAUNCHER_TARGET_VELOCITY;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.controllers.PIDController;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

import java.util.Objects;

@TeleOp(name = "Teleop", group = "StarterBot")
//@Disabled
public class Teleop extends OpMode {
    Robot robot;

    double headingError   = 0;
    double distanceToGoal = 0;
    boolean  headingLock    = false;
    double[] goalPose = GOAL_POSE_BLUE;

    private final PIDController headingController =
            new PIDController(0.8, 0.0, 0.04);

    boolean motorOn = false;
    boolean intakeOn = false;
    boolean reverseOn = false;



    final String[] options = {"Blue", "Red"};
    int selectedOption = 0;
    boolean upLast = false;
    boolean downLast = false;





    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        Scheduler.reset();
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
        if (gamepad1.dpad_down && !downLast) selectedOption = (selectedOption + 1) % options.length;
        if (gamepad1.dpad_up   && !upLast)   selectedOption = (selectedOption - 1 + options.length) % options.length;
        upLast   = gamepad1.dpad_up;
        downLast = gamepad1.dpad_down;

        telemetry.addLine("=== ALLIANCE SELECT ===");
        for (int i = 0; i < options.length; i++) {
            telemetry.addData(i == selectedOption ? "> " : "  ", options[i]);
        }
        telemetry.addLine("DPAD up/down to switch, Start to begin");
        telemetry.update();
    }

    @Override
    public void start() {
        boolean isBlue = Objects.equals(options[selectedOption], "Blue");
        if (isBlue) {
            goalPose = GOAL_POSE_BLUE;
        } else {
            goalPose = GOAL_POSE_RED;
        }
    }

    @Override
    public void loop() {
        robot.periodic();
        Scheduler.execute(); // runs anything scheduled — nothing's scheduled yet, but it's plugged in and ready


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

        // ── X — toggle heading lock ──────────────────────────────────────
        if (gamepad1.xWasPressed()) {
            headingLock = !headingLock;
        }
        double turnPower;
        if(headingLock) {
            double dx = goalPose[0] - robot.follower.pose().x();
            double dy = goalPose[1] - robot.follower.pose().y();
            distanceToGoal = Math.hypot(dx, dy);
            headingError = normalizeAngle(Math.atan2(dy, dx) + Math.PI - robot.follower.pose().heading());
            turnPower = headingController.calculate(0, headingError);
        }else {
            turnPower = gamepad1.right_stick_x;
        }

        // Field-centric drive
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                turnPower,
                robot.follower.pose().heading()
        );

        robot.follower.manual(powers);


        if (intakeOn) {
            robot.intake.setPower(INTAKE_FORWARD);
            robot.leftIntakeServo.setPower(INTAKE_FORWARD);
            robot.rightIntakeServo.setPower(INTAKE_FORWARD);
        } else if (reverseOn) {
            robot.intake.setPower(INTAKE_REVERSE);
            robot.leftIntakeServo.setPower(INTAKE_REVERSE);
            robot.rightIntakeServo.setPower(INTAKE_REVERSE);
        } else {
            robot.intake.setPower(0);
            robot.leftIntakeServo.setPower(0);
            robot.rightIntakeServo.setPower(0);
        }




        // Launcher controls
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
            robot.intake.setPower(INTAKE_FORWARD);
        } else {
            robot.windmillServo.setPower(0);
        }

        // Telemetry
        telemetry.addData("Triggers", "left (%.2f), right (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
        telemetry.addData("Heading", "%.1f°", Math.toDegrees(robot.follower.pose().heading()));
        telemetry.addData("Loop Hz", robot.getLoopTimeHz());
        telemetry.update();
    }

    private static double normalizeAngle(double angle) {
        return Math.atan2(Math.sin(angle), Math.cos(angle));
    }
}
