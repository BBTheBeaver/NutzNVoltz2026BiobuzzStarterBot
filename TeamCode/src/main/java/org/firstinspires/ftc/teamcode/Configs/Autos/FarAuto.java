package org.firstinspires.ftc.teamcode.Configs.Autos;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Poses.*;

import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.Autos.AutoRoutine;
public class FarAuto implements AutoRoutine{
    @Override
    public Pose getStartPose() {
        return FAR_START;
    }

    @Override
    public Command build(Robot robot) {
        return sequential(
                follow(robot.follower, FAR_FLOWERSHOOT()),
                follow(robot.follower, FAR_PARK())
        );
    }

    private Path FAR_FLOWERSHOOT(){
        return curve(FAR_START, FAR_FLOWERSHOOTCURVE, FAR_FLOWERSHOOT).linear(FAR_START, FAR_FLOWERSHOOT);
    }

    private Path FAR_PARK(){
        return line(FAR_FLOWERSHOOT, FAR_PARK).linear(FAR_FLOWERSHOOT, FAR_PARK);
    }
}
