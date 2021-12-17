package team.gif.robot;

public abstract class RobotMap {

    // ** CAN IDs **
    // Motors
    public static final int MOTOR_TALON_ONE = 4;
    public static final int MOTOR_SPARKMAX_ONE = 23;

    //pigeon when connected in CAN path
    public static final int PIGEON = 99;

    // ** Joysticks **
    public static final int DRIVER_CONTROLLER_ID = 0;
    public static final int AUX_CONTROLLER_ID = 1;
    public static final int TEST_CONTROLLER_ID = 2;

    // ** DIO ports **
    public static final int LIMIT_ID = 0;

}
