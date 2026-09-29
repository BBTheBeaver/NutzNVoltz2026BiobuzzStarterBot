package org.firstinspires.ftc.teamcode.Configs.Globals;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class Poses {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    //-----Start Poses----------------------------------------------------------
    private final Pose SOLO_START = poseFactory.of(56.6, 9, 270);

    //-----Solo sequences--------------------------------------------------------
    private final Pose point1 = poseFactory.of(9, 9, 180);
    private final Pose point1Control1 = poseFactory.of(40.8476, 17.6552, 0);
    private final Pose point2 = poseFactory.of(59.9528, 125.3071, 90);
    private final Pose point2Control1 = poseFactory.of(18.7784, 78.2913, 0);
    private final Pose point3 = poseFactory.of(50.1925, 129.9114, 90);
    private final Pose point3Control1 = poseFactory.of(54.6604, 124.4476, 0);
    private final Pose point4 = poseFactory.of(11.5033, 50.8005, 180);
    private final Pose point4Control1 = poseFactory.of(21.6811, 36.9203, 0);
    private final Pose point5 = poseFactory.of(56.6, 9, 270);
    private final Pose point6 = poseFactory.of(9.4934, 107.0684, 180);
}
