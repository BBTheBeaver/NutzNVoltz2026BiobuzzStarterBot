package org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Ramp;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

public class SetRamp {
    public static Command create(Ramp ramp, Robot robot, boolean up) {
        return sequential(
                instant(() -> {
                    if (up) {
                        robot.right_flower.setPosition(FLOWER_RAMP_UP);
                        robot.left_flower.setPosition(FLOWER_RAMP_UP);
                    } else {
                        robot.right_flower.setPosition(FLOWER_RAMP_DOWN);
                        robot.left_flower.setPosition(FLOWER_RAMP_DOWN);
                    }
                })
        );

    }
}
