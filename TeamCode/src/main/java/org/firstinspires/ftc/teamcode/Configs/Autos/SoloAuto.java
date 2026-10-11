package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.groups.Groups.deadline;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.*;

import com.pedropathing.api.PoseFactory;
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

    private final PoseFactory mirroredFactory = PoseFactory.radians().mirrorX(70.75).mirrorY(70.75);
    private final boolean blue;
    public SoloAuto(boolean blue) {
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
        return m(SOLO_START);
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
        return curve(m(SOLO_INTAKE_START), m(SOLO_INTAKECURVE), m(SOLO_INTAKE)).linear(m(SOLO_INTAKE_START), m(SOLO_INTAKE));
    }

    private Path SHOOT() {
        return curve(m(SOLO_INTAKE), m(SOLO_SHOOTCURVE), m(SOLO_SHOOT)).linear(m(SOLO_INTAKE), m(SOLO_SHOOT));
    }

    private Path FLOWER() {
        return curve(m(SOLO_SHOOT), m(SOLO_FLOWERCURVE), m(SOLO_FLOWER)).linear(m(SOLO_SHOOT), m(SOLO_FLOWER));
    }

    private Path SHOOT2() {
        return curve(m(SOLO_SHOOT), m(SOLO_SHOOT2CURVE), m(SOLO_SHOOT2)).linear(m(SOLO_FLOWER), m(SOLO_SHOOT2));
    }

    private Path FLOWER2() {
        return curve(m(SOLO_SHOOT2), m(SOLO_FLOWER2CURVE1), m(SOLO_FLOWER2CURVE2), m(SOLO_FLOWER2)).linear(m(SOLO_SHOOT2), m(SOLO_FLOWER2));
    }

    private Path SHOOT3() {
        return line(m(SOLO_FLOWER2), m(SOLO_SHOOT3)).linear(m(SOLO_FLOWER2), m(SOLO_SHOOT3));
    }

    private Path PARK() {
        return line(m(SOLO_SHOOT3), m(SOLO_PARK)).linear(m(SOLO_SHOOT3), m(SOLO_PARK));
    }
}