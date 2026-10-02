package org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;

public class Shoot {

    public static Command create(Flywheel flywheel, Robot robot) {
        return sequential(
                instant(() -> {
                    robot.windmillServo.setPower(1);
                    robot.intake.setPower(INTAKE_FORWARD);
                    robot.leftIntakeServo.setPower(INTAKE_FORWARD);
                    robot.rightIntakeServo.setPower(INTAKE_FORWARD);
                })
        );
    }
}