package org.firstinspires.ftc.teamcode.OpMode;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Configs.Autos.SmallSoloAuto;
import org.firstinspires.ftc.teamcode.Configs.Autos.SoloAuto;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;
import org.firstinspires.ftc.teamcode.Configs.Autos.AutoRoutine;
import org.firstinspires.ftc.teamcode.Configs.Autos.FarAuto;
import org.firstinspires.ftc.teamcode.Configs.Globals.PoseStorage;

import java.util.Objects;

@Autonomous(name = "Auto", group = "Autos")
public class Auto extends OpMode {

    private Robot robot;

    final String[] options = {"Blue", "Red"};

    int selectedOption_Alliance = 0;
    private final AutoRoutine[] red_autos = {
            new SoloAuto(false),
            new FarAuto(false),
            new SmallSoloAuto(false)
            // Add more autos here
    };

    private final AutoRoutine[] blue_auto = {
            new SoloAuto(true),
            new FarAuto(true),
            new SmallSoloAuto(true)
    };

    private int selectedIndex = 0;
    private AutoRoutine selectedAuto;

    private boolean alliance_selected = false;
    private boolean upLast = false;
    private boolean downLast = false;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        PoseStorage.lastAutoPose = null;
        Scheduler.reset();
        selectedAuto = red_autos[selectedIndex];
    }

    @Override
    public void init_loop() {




        if(!alliance_selected){
            if (gamepad1.dpad_down && !downLast) selectedOption_Alliance = (selectedOption_Alliance + 1) % options.length;
            if (gamepad1.dpad_up   && !upLast)   selectedOption_Alliance = (selectedOption_Alliance - 1 + options.length) % options.length;
            upLast   = gamepad1.dpad_up;
            downLast = gamepad1.dpad_down;

            if(gamepad1.aWasPressed())
            {
                alliance_selected = true;
            }
            telemetry.addLine("=== ALLIANCE SELECT ===");
            for (int i = 0; i < options.length; i++) {
                telemetry.addData(i == selectedOption_Alliance ? "> " : "  ", options[i]);
            }
            telemetry.addLine("DPAD up/down to switch, A to select");
            telemetry.update();
        }
        boolean isBlue = Objects.equals(options[selectedOption_Alliance], "Blue");
        if (isBlue) {
            // Select an blue auto with the D-pad
            if (gamepad1.dpad_down && !downLast) {
                selectedIndex = (selectedIndex + 1) % blue_auto.length;
            }

            if (gamepad1.dpad_up && !upLast) {
                selectedIndex = (selectedIndex - 1 + blue_auto.length) % blue_auto.length;
            }

            upLast = gamepad1.dpad_up;
            downLast = gamepad1.dpad_down;
            selectedAuto = blue_auto[selectedIndex];
        } else {
            // Select an red auto with the D-pad
            if (gamepad1.dpad_down && !downLast) {
                selectedIndex = (selectedIndex + 1) % red_autos.length;
            }

            if (gamepad1.dpad_up && !upLast) {
                selectedIndex = (selectedIndex - 1 + red_autos.length) % red_autos.length;
            }

            upLast = gamepad1.dpad_up;
            downLast = gamepad1.dpad_down;
            selectedAuto = red_autos[selectedIndex];
        }

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

        PoseStorage.save(robot.follower.pose());
    }

    @Override
    public void stop() {
        if (robot != null) {
            PoseStorage.save(robot.follower.pose());
        }
    }
}