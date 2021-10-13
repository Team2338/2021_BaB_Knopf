package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import team.gif.robot.RobotMap;

public class LimitSwitch implements Subsystem {
    private static LimitSwitch instance = null;

    public static LimitSwitch getInstance() {
        if (instance == null) {
            instance = new LimitSwitch();
        }
        return instance;
    }

    private DigitalInput _limitSwitch = new DigitalInput(RobotMap.LIMIT_ID);

    // pressed = true, not pressed = false
    public boolean getLimitState() {
        return !_limitSwitch.get();
    }
}
