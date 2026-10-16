package org.firstinspires.ftc.teamcode.Configs.Globals;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class Poses {

    private static final PoseFactory poseFactory = PoseFactory.degrees();

    //Test start for heading lock
    public static final Pose BLUE_START = poseFactory.of(0, 0, 90);
    public static final Pose RED_START = poseFactory.of(140, 0, 90);

    // Solo sequence start
    public static final Pose SOLO_START = poseFactory.of(56.6, 10, 90);

    // Solo red sequence
    public static final Pose SOLO_INTAKE_START = poseFactory.of(56.6, 10, 270);
    public static final Pose SOLO_INTAKE = poseFactory.of(9, 9, 180);
    public static final Pose SOLO_INTAKECURVE = poseFactory.of(63.141, 29.7934, 0);
    public static final Pose SOLO_INTAKECURVE2 = poseFactory.of(39.2462, 10.5708, 0);
    public static final Pose SOLO_SHOOT = poseFactory.of(59.9528, 125.3071, 90);
    public static final Pose SOLO_SHOOTCURVE = poseFactory.of(26.9214, 102.8536, 0);
    public static final Pose SOLO_FLOWER = poseFactory.of(47.9231, 128.443, 90);
    public static final Pose SOLO_FLOWERCURVE = poseFactory.of(54.6604, 124.4476, 0);
    public static final Pose SOLO_SHOOT2 = poseFactory.of(59.9528, 125.3071, 90);
    public static final Pose SOLO_SHOOT2CURVE = poseFactory.of(54.6604, 124.4476, 0);
    public static final Pose SOLO_FLOWER2 = poseFactory.of(14.1731, 47.7302, 180);
    public static final Pose SOLO_FLOWER2CURVE1 = poseFactory.of(19.8127, 96.1901, 0);
    public static final Pose SOLO_FLOWER2CURVE2 = poseFactory.of(23.8948, 33.85, 0);
    public static final Pose SOLO_SHOOT3 = poseFactory.of(56.6, 10, 270);
    public static final Pose SOLO_PARK = poseFactory.of(9.8939, 92.3844, 180);




    // Far red sequence start
    public static final Pose FAR_START = poseFactory.of(64.3425, 131.7, 90);

    // Far red sequence
    public static final Pose FAR_FLOWER = poseFactory.of(46.8571, 130.1269, 90);
    public static final Pose FAR_FLOWERCURVE = poseFactory.of(58.9226, 121.4925, 0);
    public static final Pose FAR_SHOOT = poseFactory.of(64.3425, 131.7, 90);
    public static final Pose FAR_SHOOTCURVE = poseFactory.of(58.9226, 121.4925, 0);
    public static final Pose FAR_PARK = poseFactory.of(10.592, 120.4429, 360);
    public static final Pose FAR_PARKCURVE = poseFactory.of(43.0561, 105.4618, 0);
}