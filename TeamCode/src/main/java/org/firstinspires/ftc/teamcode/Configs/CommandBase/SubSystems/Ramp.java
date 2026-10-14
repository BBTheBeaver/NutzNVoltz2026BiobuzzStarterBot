package org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;

public class Ramp {

    private final Servo servo;
    private final Servo servo2;


    public Ramp(Servo servo, Servo servo2) {
        this.servo = servo;
        this.servo2 = servo2;
    }

    // Turns on the intake
    public void setDown() {
        servo.setPosition(90.0/300.0);
        servo2.setPosition(90.0/300.0);
    }

    public void setUp (){
        servo.setPosition(0.0/300.0);
        servo2.setPosition(0.0/300.0);
    }

}
