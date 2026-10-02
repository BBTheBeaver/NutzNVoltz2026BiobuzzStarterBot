package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.deadline;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.*;

import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SetIntake;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.Shoot;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SpinFlywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

public class SoloAuto implements AutoRoutine {

    @Override
    public Pose getStartPose() {
        return SOLO_START;
    }

    @Override
    public Command build(Robot robot) {
        Flywheel flywheel = new Flywheel(robot.launcher);
        Intake intake = new Intake(robot.intake);

        return deadline(
                sequential(
                        Shoot.create(flywheel, robot),
                        parallel(
                                SetIntake.create(intake, robot, false),
                                follow(robot.follower, INTAKE())
                        ),

                        follow(robot.follower, SHOOT()),
                        Shoot.create(flywheel, robot),

                        parallel(
                                SetIntake.create(intake, robot, false),
                                follow(robot.follower, FLOWER())
                        ),

                        follow(robot.follower, SHOOT2()),
                        Shoot.create(flywheel, robot),

                        parallel(
                                SetIntake.create(intake, robot, false),
                                follow(robot.follower, FLOWER2())
                        ),

                        follow(robot.follower, SHOOT3()),
                        Shoot.create(flywheel, robot),

                        follow(robot.follower, PARK())
                ),
                SpinFlywheel.create(flywheel, robot)
        );
    }

    private Path INTAKE() {
        return curve(SOLO_INTAKE_START, SOLO_INTAKECURVE, SOLO_INTAKE).linear(SOLO_INTAKE_START, SOLO_INTAKE);
    }

    private Path SHOOT() {
        return curve(SOLO_INTAKE, SOLO_SHOOTCURVE, SOLO_SHOOT).linear(SOLO_INTAKE, SOLO_SHOOT);
    }

    private Path FLOWER() {
        return curve(SOLO_SHOOT, SOLO_FLOWERCURVE, SOLO_FLOWER).linear(SOLO_SHOOT, SOLO_FLOWER);
    }

    private Path SHOOT2() {
        return curve(SOLO_SHOOT, SOLO_SHOOT2CURVE, SOLO_SHOOT2).linear(SOLO_FLOWER, SOLO_SHOOT2);
    }

    private Path FLOWER2() {
        return curve(SOLO_SHOOT2, SOLO_FLOWER2CURVE1, SOLO_FLOWER2CURVE2, SOLO_FLOWER2).linear(SOLO_SHOOT2, SOLO_FLOWER2);
    }

    private Path SHOOT3() {
        return line(SOLO_FLOWER2, SOLO_SHOOT3).linear(SOLO_FLOWER2, SOLO_SHOOT3);
    }

    private Path PARK() {
        return line(SOLO_SHOOT3, SOLO_PARK).linear(SOLO_SHOOT3, SOLO_PARK);
    }
}