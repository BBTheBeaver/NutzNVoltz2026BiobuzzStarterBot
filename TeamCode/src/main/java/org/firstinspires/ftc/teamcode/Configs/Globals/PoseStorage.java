package org.firstinspires.ftc.teamcode.Configs.Globals;

import com.pedropathing.math.Pose;

public class PoseStorage {

    public static Pose lastAutoPose = null;

    // Saves the robot's current pose
    public static void save(Pose pose) {
        lastAutoPose = new Pose(pose.x(), pose.y(), pose.heading());
    }
}