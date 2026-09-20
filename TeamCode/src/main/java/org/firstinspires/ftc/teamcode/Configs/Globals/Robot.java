package org.firstinspires.ftc.teamcode.Configs.Globals;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Robot {
    public final Follower follower;
    public final List<LynxModule> hubs;
    public final Timer loop = new Timer();
    public double loops = 0, lastloop = 0, loopTime = 0;

    // Non-drivetrain hardware
    public final DcMotorEx launcher;
    public final DcMotor intake;
    public final CRServo leftIntakeServo;
    public final CRServo rightIntakeServo;
    public final CRServo windmillServo;

    // Launcher velocity targets (ticks/sec)
    public static final int LAUNCHER_TARGET_VELOCITY = 1250; //2678 RPM
    public static final int LAUNCHER_MIN_VELOCITY = 1200; //2571 RPM

    public Robot(HardwareMap hw){
        follower = Constants.createFollower(hw);
        hubs = hw.getAll(LynxModule.class);
        for(LynxModule hub:hubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // ----- Non-drivetrain hardware init -----
        intake = hw.get(DcMotor.class, "intake");
        launcher = hw.get(DcMotorEx.class, "launcher");
        windmillServo = hw.get(CRServo.class, "windmillServo");
        leftIntakeServo = hw.get(CRServo.class, "left_intake_servo");
        rightIntakeServo = hw.get(CRServo.class, "right_intake_servo");
        //blablablablablasblablablalb

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));

        leftIntakeServo.setPower(0);
        rightIntakeServo.setPower(0);
        windmillServo.setPower(0);

        rightIntakeServo.setDirection(DcMotorSimple.Direction.REVERSE);
        windmillServo.setDirection(DcMotorSimple.Direction.REVERSE);
        // -----------------------------------------

        loop.reset();
        periodic();
    }

    public void periodic(){
        loops++;
        if(loops > 10){
            double now = loop.get(TimeUnit.MICROSECONDS);
            loopTime = (now-lastloop) / loops;
            lastloop = now;
            loops = 0;
        }
        follower.update();
    }
    public double getLoopTimeMs() { return loopTime;}
    public double getLoopTimeHz() { return 1000/loopTime;}

}