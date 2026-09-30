import org.firstinspires.ftc.teamcode.Configs.Globals.Poses;
import org.firstinspires.ftc.teamcode.Configs.Globals.Robot;

public class SoloAuto {

    Robot robot;

    public SoloAuto(Robot robot) {
        this.robot = robot;
    }

    public void init() {
        robot.follower.setPose(Poses.SOLO_START);
    }

    public void run() {
        // Solo autonomous code
    }
}