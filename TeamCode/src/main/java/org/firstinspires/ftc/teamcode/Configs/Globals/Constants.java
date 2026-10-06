package org.firstinspires.ftc.teamcode.Configs.Globals;

public class Constants {
    public static final int LAUNCHER_TARGET_VELOCITY = 1150; //2678 RPM
    public static final int LAUNCHER_MIN_VELOCITY = 1100; //2571 RPM

    public static final double INTAKE_FORWARD = 1.0;
    public static final double INTAKE_REVERSE = -0.5;

    // ─── Goal pose [x_inches, y_inches] (Pedro field coords) ─────────────────
    // Blue alliance default — mirrored for Red in TeleOp initialize()
    public static double[] GOAL_POSE_BLUE = {84.0,  95.0};//-50 For the other side
    public static double[] GOAL_POSE_RED  = {58.0, 47.0};//-50 for the other side

    public static double OTHERSIDE;

}
