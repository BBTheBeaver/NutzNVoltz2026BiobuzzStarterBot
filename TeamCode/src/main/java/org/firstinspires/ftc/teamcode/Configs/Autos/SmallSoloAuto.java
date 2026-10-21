package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.deadline;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.FAR_START;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_FLOWER;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_FLOWERCURVE;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_PARK;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_PARKCURVE;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_SHOOT;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_SHOOTCURVE;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.SMALL_SOLO_START;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SetIntake;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SetRamp;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.Shoot;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.Commands.SpinFlywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Ramp;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

public class SmallSoloAuto implements AutoRoutine {
    private final PoseFactory mirroredFactory = PoseFactory.radians().mirrorX(70.75).mirrorY(70.75);
    private final boolean blue;
    public SmallSoloAuto(boolean blue) {
        this.blue = blue;
    }
    private Pose m(Pose pose) {
        if (blue) {
            return mirroredFactory.of(pose.x(), pose.y(), pose.heading());
        }
        return pose;
    }
    @Override
    public Pose getStartPose() {
        return m(SMALL_SOLO_START);
    }

    @Override
    public Command build(Robot robot) {
        Flywheel flywheel = new Flywheel(robot.launcher);
        Intake intake = new Intake(robot.intake);
        Ramp ramp = new Ramp(robot.right_flower, robot.left_flower);

        return deadline(
                sequential(
                        waitMs(3000),
                        Shoot.create(robot),



                        parallel(
                                SetRamp.down(ramp),
                                SetIntake.create(intake, robot, false),
                                follow(robot.follower, FLOWER())
                        ),

                        instant(intake::turnOn),
                        waitMs(2000),
                        instant(intake::stop),


                        parallel(
                                follow(robot.follower, SHOOT()),
                                SetRamp.up(ramp)
                        ),




                        Shoot.create(robot),

                        follow(robot.follower, PARK())

                ),
                SpinFlywheel.create(flywheel)
        );
    }

    //private Path INTAKE() {
    //    return curve(m(SMALL_SOLO_START), m(SMALL_SOLO_INTAKECURVE1), m(SMALL_SOLO_INTAKECURVE2), m(SMALL_SOLO_INTAKE)).linear(m(SMALL_SOLO_START), m(SMALL_SOLO_INTAKE));
    //}
    private Path FLOWER() {
        return curve(m(SMALL_SOLO_SHOOT), m(SMALL_SOLO_FLOWERCURVE), m(SMALL_SOLO_FLOWER)).linear(m(SMALL_SOLO_START), m(SMALL_SOLO_FLOWER));
    }
    private Path SHOOT() {
        return curve(m(SMALL_SOLO_FLOWER), m(SMALL_SOLO_SHOOTCURVE), m(SMALL_SOLO_SHOOT)).linear(m(SMALL_SOLO_FLOWER), m(SMALL_SOLO_SHOOT));
    }



    private Path PARK() {
        return curve(m(SMALL_SOLO_SHOOT), m(SMALL_SOLO_PARKCURVE), m(SMALL_SOLO_PARK)).linear(m(SMALL_SOLO_SHOOT), m(SMALL_SOLO_PARK));
    }
}

