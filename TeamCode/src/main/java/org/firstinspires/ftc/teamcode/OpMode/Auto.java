package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

@Autonomous(name = "Auto", group = "Autos")
public class Auto extends OpMode {

    Robot robot;

    int selectedAuto = 0;

    final String[] autoNames = {
            "Solo Auto",
            "Blue Close",
            "Blue Far",
            "Red Close",
            "Red Far"
    };

    Object auto;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
    }

    @Override
    public void init_loop() {

        if (gamepad1.dpadDownWasPressed()) {
            selectedAuto++;

            if (selectedAuto >= autoNames.length) {
                selectedAuto = 0;
            }
        }

        if (gamepad1.dpadUpWasPressed()) {
            selectedAuto--;

            if (selectedAuto < 0) {
                selectedAuto = autoNames.length - 1;
            }
        }

        telemetry.addData("Selected Auto", autoNames[selectedAuto]);
        telemetry.addData("Controls", "D-Pad Up/Down = Select");
        telemetry.update();
    }

    @Override
    public void start() {



    @Override
    public void stop() {
    }
}