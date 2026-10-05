package org.firstinspires.ftc.teamcode.Configs.Globals;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class Poses {

    private static final PoseFactory poseFactory = PoseFactory.degrees();

    //Test start for heading lock
    public static final Pose Start = poseFactory.of(8, 8, 270);

    // Solo sequence start
    public static final Pose SOLO_START = poseFactory.of(56.6, 9, 270);

    // Solo sequence
    public static final Pose SOLO_INTAKE_START = poseFactory.of(56.6, 9, 270);
    public static final Pose SOLO_INTAKE = poseFactory.of(9, 9, 180);
    public static final Pose SOLO_INTAKECURVE = poseFactory.of(40.8476, 17.6552, 0);
    public static final Pose SOLO_SHOOT = poseFactory.of(59.9528, 125.3071, 90);
    public static final Pose SOLO_SHOOTCURVE = poseFactory.of(22.5162, 101.7857, 0);
    public static final Pose SOLO_FLOWER = poseFactory.of(50.1925, 129.9114, 90);
    public static final Pose SOLO_FLOWERCURVE = poseFactory.of(54.6604, 124.4476, 0);
    public static final Pose SOLO_SHOOT2 = poseFactory.of(59.9528, 125.3071, 90);
    public static final Pose SOLO_SHOOT2CURVE = poseFactory.of(54.6604, 124.4476, 0);
    public static final Pose SOLO_FLOWER2 = poseFactory.of(11.5033, 50.8005, 180);
    public static final Pose SOLO_FLOWER2CURVE1 = poseFactory.of(19.8127, 96.1901, 0);
    public static final Pose SOLO_FLOWER2CURVE2 = poseFactory.of(21.225, 29.0443, 0);
    public static final Pose SOLO_SHOOT3 = poseFactory.of(56.6, 9, 270);
    public static final Pose SOLO_PARK = poseFactory.of(9.4934, 107.0684, 180);




    // Far sequence start
    public static final Pose FAR_START = poseFactory.of(64.3425, 132.5812, 90);

    // Far sequence
    public static final Pose FAR_FLOWER = poseFactory.of(51.2623, 130.1269, 90);
    public static final Pose FAR_FLOWERCURVE = poseFactory.of(58.9226, 121.4925, 0);
    public static final Pose FAR_SHOOT = poseFactory.of(7.2547, 104.4241, 360);
    public static final Pose FAR_SHOOTCURVE = poseFactory.of(58.9226, 121.4925, 0);
    public static final Pose FAR_PARK = poseFactory.of(10.592, 120.4429, 360);
    public static final Pose FAR_PARKCURVE = poseFactory.of(35.4472, 110.134, 0);
}