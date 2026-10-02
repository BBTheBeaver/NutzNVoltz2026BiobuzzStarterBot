package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Configs.Globals.Constants.*;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

public class Flywheel {

    private final DcMotorEx motor;

    public Flywheel(DcMotorEx motor) {
        this.motor = motor;
    }

    // Spins up the flywheel
    public void spinUp() {
        motor.setVelocity(LAUNCHER_TARGET_VELOCITY);
    }

    // Checks whether the flywheel is ready to shoot
    public boolean isAtSpeed() {
        return motor.getVelocity() >= LAUNCHER_MIN_VELOCITY;
    }

    // Stops the flywheel
    public void stop() {
        motor.setVelocity(0);
    }

    // Returns the current flywheel velocity
    public double getVelocity() {
        return motor.getVelocity();
    }
}