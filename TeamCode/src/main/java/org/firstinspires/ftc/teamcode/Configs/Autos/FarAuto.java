package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.commands.Commands.waitMs;
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
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Flywheel;
import org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

public class FarAuto implements AutoRoutine{

    private final PoseFactory mirroredFactory = PoseFactory.radians().mirrorX(70.75).mirrorY(70.75);
    private final boolean blue;
    public FarAuto(boolean blue) {
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
        return m(FAR_START);
    }

    @Override
    public Command build(Robot robot) {
        Flywheel flywheel = new Flywheel(robot.launcher);
        Intake intake = new Intake(robot.intake);

        return sequential(

                waitMs(6000),
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
        return curve(m(FAR_START), m(FAR_FLOWERCURVE), m(FAR_FLOWER)).linear(m(FAR_START), m(FAR_FLOWER));
    }

    private Path SHOOT(){
        return curve(m(FAR_FLOWER), m(FAR_SHOOTCURVE), m(FAR_SHOOT)).linear(m(FAR_FLOWER), m(FAR_SHOOT));
    }
    private Path PARK(){
        return curve(m(FAR_SHOOT), m(FAR_PARKCURVE), m(FAR_PARK)).linear(m(FAR_SHOOT), m(FAR_PARK));
    }
}
