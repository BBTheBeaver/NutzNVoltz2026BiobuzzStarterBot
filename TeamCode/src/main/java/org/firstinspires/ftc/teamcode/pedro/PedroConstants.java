package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class PedroConstants {



    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("lf");
        c.frontRightName.set("rf");
        c.backLeftName.set("lr");
        c.backRightName.set("rr");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(4.838731720691591);
        c.yPodOffset.set(-0.36596328254759786);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.21684050545719893);
                Controller secondaryTranslationalForward = Controller.proportional(0.08011677268141798);
                Controller primaryTranslationalLateral = Controller.proportional(0.29534684014066376);
                Controller secondaryTranslationalLateral = Controller.proportional(0.1091227656190611);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.012859272003525175));
                c.brake.set(Controller.proportionalFeedforward(0.010930381202996399));

                c.headingFeedback.set(Controller.proportional(4.177265613465861));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.06781300792480005, 0.003083714035020887));

                c.linearBrakeCoefficients.set(Matrix.diag(0.11794210815437911, 0.08519195741349704));
                c.quadraticBrakeCoefficients.set(Matrix.diag(4.4040176464045587E-4, 2.2912416512986452E-4));

                c.maxAchievableForwardVelocity.set(81.51560556700544);
                c.maxAchievableStrafeVelocity.set(67.78695300398562);
                c.naturalForwardDeceleration.set(29.40846201211878);
                c.naturalStrafeDeceleration.set(50.68621708643528);
            }
    );

    public static Follower createFollower(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }



}