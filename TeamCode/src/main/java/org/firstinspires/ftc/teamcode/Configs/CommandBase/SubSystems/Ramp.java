package org.firstinspires.ftc.teamcode.Configs.CommandBase.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;
import static org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;

public class Ramp {

    private final Servo servo;


    public Ramp(Servo servo) {
        this.servo = servo;
    }

    // Turns on the intake
    public void setDown() {
        servo.setPosition(FLOWER_RAMP_DOWN);
    }

    public void setUp (){
        servo.setPosition(FLOWER_RAMP_UP);
    }

}
