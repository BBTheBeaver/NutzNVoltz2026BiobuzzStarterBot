package org.firstinspires.ftc.teamcode.OpMode.Tunning;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class PIDF extends OpMode {

    double highvelocity = 1500;
    double lowvelocity = 1500;
    double curTargetVelocity = highvelocity;
    double F = 0;
    double P = 0;
    double stepSizes[] = {10, 1, 0.1, 0.01, 0.001, 0.0001};
    int stepindex = 1;

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

}
