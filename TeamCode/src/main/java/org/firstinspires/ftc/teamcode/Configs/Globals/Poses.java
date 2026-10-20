package org.firstinspires.ftc.teamcode.Configs.Globals;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class Poses {

    private static final PoseFactory poseFactory = PoseFactory.degrees();

    //Test start for heading lock
    public static final Pose BLUE_RESET = poseFactory.of(132, 132, 270);
    public static final Pose RED_RESET = poseFactory.of(9.5, 9.5, 90);

    // Solo sequence start
    public static final Pose SOLO_START = poseFactory.of(56.6, 10, 90);

    // Solo red sequence
    public static final Pose SOLO_INTAKE_START = poseFactory.of(56.6, 10, 270);
    public static final Pose SOLO_INTAKE = poseFactory.of(9, 10, 180);
    public static final Pose SOLO_INTAKECURVE = poseFactory.of(40.8476, 17.6552, 0);
    public static final Pose SOLO_INTAKECURVE2 = poseFactory.of(39.9137, 13.6411, 0);
    public static final Pose SOLO_SHOOT = poseFactory.of(59.9528, 125.3071, 90);
    public static final Pose SOLO_SHOOTCURVE = poseFactory.of(26.9214, 102.8536, 0);
    public static final Pose SOLO_FLOWER = poseFactory.of(47.3891, 126.0402, 90);
    public static final Pose SOLO_FLOWERCURVE = poseFactory.of(54.6604, 124.4476, 0);
    public static final Pose SOLO_SHOOT2 = poseFactory.of(59.9528, 125.3071, 90);
    public static final Pose SOLO_SHOOT2CURVE = poseFactory.of(54.6604, 124.4476, 0);
    public static final Pose SOLO_FLOWER2 = poseFactory.of(17.0675, 47.4028, 180);
    public static final Pose SOLO_FLOWER2CURVE1 = poseFactory.of(19.8127, 96.1901, 0);
    public static final Pose SOLO_FLOWER2CURVE2 = poseFactory.of(23.8948, 33.85, 0);
    public static final Pose SOLO_SHOOT3 = poseFactory.of(56.6, 10, 270);
    public static final Pose SOLO_PARK = poseFactory.of(9.8939, 92.3844, 180);

    //Small solo sequence start
    public static final Pose SMALL_SOLO_START = poseFactory.of(56.6, 10, 270);

    //Small solo sequence
    //public static final Pose SMALL_SOLO_INTAKE = poseFactory.of(9, 10, 180);
    //public static final Pose SMALL_SOLO_INTAKECURVE1 = poseFactory.of(40.8476, 17.6552, 0);
    //public static final Pose SMALL_SOLO_INTAKECURVE2 = poseFactory.of(39.9137, 13.6411, 0);
    public static final Pose SMALL_SOLO_SHOOT = poseFactory.of(56.6, 10, 270);
    public static final Pose SMALL_SOLO_SHOOTCURVE = poseFactory.of(48.8075, 29.5943, 0);
    public static final Pose SMALL_SOLO_FLOWER = poseFactory.of(17.0675, 47.4028, 180);
    public static final Pose SMALL_SOLO_FLOWERCURVE = poseFactory.of(29.9019, 47.9481, 0);
    public static final Pose SMALL_SOLO_PARK = poseFactory.of(12.0142, 92.4005, 0);
    public static final Pose SMALL_SOLO_PARKCURVE = poseFactory.of(9.6976, 59.3839, 0);





    // Far red sequence start
    public static final Pose FAR_START = poseFactory.of(64.3425, 131.7, 90);

    // Far red sequence
    public static final Pose FAR_FLOWER = poseFactory.of(46.9906, 127.0566, 90);
    public static final Pose FAR_FLOWERCURVE = poseFactory.of(58.9226, 121.4925, 0);
    public static final Pose FAR_SHOOT = poseFactory.of(64.3425, 131.7, 90);
    public static final Pose FAR_SHOOTCURVE = poseFactory.of(58.9226, 121.4925, 0);
    public static final Pose FAR_PARK = poseFactory.of(10.592, 120.4429, 360);
    public static final Pose FAR_PARKCURVE = poseFactory.of(43.0561, 105.4618, 0);
}