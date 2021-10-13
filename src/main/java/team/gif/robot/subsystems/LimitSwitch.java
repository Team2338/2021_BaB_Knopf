package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import team.gif.robot.RobotMap;

/**
 * Describes the functionality of the 1 limit switch
 *
 */
public class LimitSwitch implements Subsystem {
    private static LimitSwitch instance = null;

    // creates a singleton class (only 1 instance can be instantiated)
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
