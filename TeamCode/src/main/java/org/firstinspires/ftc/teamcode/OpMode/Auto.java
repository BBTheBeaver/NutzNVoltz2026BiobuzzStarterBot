package org.firstinspires.ftc.teamcode.OpMode;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.Autos.AutoRoutine;
import org.firstinspires.ftc.teamcode.Configs.Autos.SoloAuto;
import org.firstinspires.ftc.teamcode.Configs.Autos.FarAuto;

@Autonomous(name = "Main Auto", group = "Autos")
public class Auto extends OpMode {

    private Robot robot;
    private final AutoRoutine[] autos = {
            new SoloAuto(),
            new FarAuto()
            // Add more autos here
    };

    private int selectedIndex = 0;
    private AutoRoutine selectedAuto;

    private boolean upLast = false;
    private boolean downLast = false;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        Scheduler.reset();
        selectedAuto = autos[selectedIndex];
    }

    @Override
    public void init_loop() {
        // Select an auto with the D-pad
        if (gamepad1.dpad_down && !downLast) {
            selectedIndex = (selectedIndex + 1) % autos.length;
        }

        if (gamepad1.dpad_up && !upLast) {
            selectedIndex = (selectedIndex - 1 + autos.length) % autos.length;
        }

        upLast = gamepad1.dpad_up;
        downLast = gamepad1.dpad_down;

        selectedAuto = autos[selectedIndex];

        telemetry.addData("Selected Auto", selectedAuto.getClass().getSimpleName());
        telemetry.addLine("D-pad up/down to select");
        telemetry.addLine("Press START to run");
        telemetry.update();
    }

    @Override
    public void start() {
        robot.follower.setPose(selectedAuto.getStartPose());
        Scheduler.schedule(selectedAuto.build(robot));
    }

    @Override
    public void loop() {
        robot.periodic();
        Scheduler.execute();
    }
}