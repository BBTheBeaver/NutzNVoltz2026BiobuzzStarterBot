package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.*;

import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SetIntake;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.Shoot;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.Autos.AutoRoutine;
public class FarAuto implements AutoRoutine{
    @Override
    public Pose getStartPose() {
        return FAR_START;
    }

    @Override
    public Command build(Robot robot) {
        Flywheel flywheel = new Flywheel(robot.launcher);
        Intake intake = new Intake(robot.intake);

        return sequential(
                Shoot.create(flywheel, robot),

                parallel(
                        SetIntake.create(intake, robot, false),
                        follow(robot.follower, FLOWER())

                ),

                Shoot.create(flywheel, robot),
                follow(robot.follower, SHOOT()),

                follow(robot.follower, PARK())
        );
    }

    private Path FLOWER(){
        return curve(FAR_START, FAR_FLOWERCURVE, FAR_FLOWER).linear(FAR_START, FAR_FLOWER);
    }

    private Path SHOOT(){
        return curve(FAR_FLOWER, FAR_SHOOTCURVE, FAR_SHOOT).linear(FAR_FLOWER, FAR_SHOOT);
    }
    private Path PARK(){
        return curve(FAR_SHOOT, FAR_PARKCURVE, FAR_PARK).linear(FAR_SHOOT, FAR_PARK);
    }
}
