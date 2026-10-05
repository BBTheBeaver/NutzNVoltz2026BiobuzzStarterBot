package org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;
public class SpinFlywheel {
    public static Command create(Flywheel flywheel, Robot robot) {
        return sequential(
                instant(flywheel::spinUp)
        );
    }
}
