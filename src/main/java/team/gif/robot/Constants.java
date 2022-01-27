package team.gif.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
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
    
    public static class Shooter {
        public static final double FLYWHEEL_kP = 0.18;
        public static final double FLYWHEEL_kI = 0.0;
        public static final double FLYWHEEL_kD = 0.0;
        public static final double FLYWHEEL_kF = 0.032;
        public static final int FLYWHEEL_IZONE = 5000;
        
        public static final double FLYWHEEL_VELOCITY_TOLERANCE = 200;
        public static final double FLYWHEEL_ACCELERATION_TOLERANCE = 40;
    }
}
