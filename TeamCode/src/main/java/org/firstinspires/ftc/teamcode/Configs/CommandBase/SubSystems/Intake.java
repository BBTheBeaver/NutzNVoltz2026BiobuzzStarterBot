package org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;

public class Intake {

    private final DcMotor motor;

    public Intake(DcMotor motor) {
        this.motor = motor;
    }

    // Turns on the intake
    public void turnOn() {
        motor.setPower(INTAKE_FORWARD);
    }

    public void turnOnReverse(){
        motor.setPower(INTAKE_REVERSE);
    }

    // Stops the intake
    public void stop() {
        motor.setPower(0);
    }
}
