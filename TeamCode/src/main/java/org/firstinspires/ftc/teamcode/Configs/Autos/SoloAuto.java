package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.*;

import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SpinFlywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.Autos.AutoRoutine;
public class SoloAuto implements AutoRoutine {

    @Override
    public Pose getStartPose() {
        return SOLO_START;
    }

    @Override
    public Command build(Robot robot) {
        Flywheel flywheel = new Flywheel(robot.launcher);

        return sequential(
                SpinFlywheel.create(flywheel, robot),
                follow(robot.follower, INTAKE()),
                follow(robot.follower, SHOOT()),
                follow(robot.follower, FLOWERSHOOT()),
                follow(robot.follower, FLOWER()),
                follow(robot.follower, SHOOT2()),
                follow(robot.follower, PARK())
        );
    }

    private Path INTAKE() {
        return curve(SOLO_INTAKE_START, SOLO_INTAKECURVE, SOLO_INTAKE).linear(SOLO_INTAKE_START, SOLO_INTAKE);
    }

    private Path SHOOT() {
        return curve(SOLO_INTAKE, SOLO_SHOOTCURVE, SOLO_SHOOT).linear(SOLO_INTAKE, SOLO_SHOOT);
    }

    private Path FLOWERSHOOT() {
        return curve(SOLO_SHOOT, SOLO_FLOWERSHOOTCURVE, SOLO_FLOWERSHOOT).linear(SOLO_SHOOT, SOLO_FLOWERSHOOT);
    }

    private Path FLOWER() {
        return curve(SOLO_FLOWERSHOOT, SOLO_FLOWER2CURVE, SOLO_FLOWER2).linear(SOLO_FLOWERSHOOT, SOLO_FLOWER2);
    }

    private Path SHOOT2() {
        return line(SOLO_FLOWER2, SOLO_SHOOT2).linear(SOLO_FLOWER2, SOLO_SHOOT2);
    }

    private Path PARK() {
        return line(SOLO_SHOOT2, SOLO_PARK).linear(SOLO_SHOOT2, SOLO_PARK);
    }
}