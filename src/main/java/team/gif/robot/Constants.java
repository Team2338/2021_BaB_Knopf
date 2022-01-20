

package team.gif.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 *
 * set kF to get close to the target, just under (set kP = 0)
 *
 */
public final class Constants {
    public static class Climber {
        public static final double CLIMBER_MAX_POSITION = 500000;
        public static final double CLIMBER_ASCEND_POSITION = 15000;
        public static final double ELEVATOR_UP_UNLOADED_VOLTAGE = 0.8;
        public static final double ELEVATOR_DOWN_LOADED_VOLTAGE = -0.8;
        public static final double CLIMBER_HOLD_LOADED_VOLTAGE = -0.4;
        public static final double CLIMBER_FALL_POSITION = 250000;
        public static final double CLIMBER_LOADED_DROP_VOLTAGE = 0.2;

    }
}
