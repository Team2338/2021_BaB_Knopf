
package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystems describe functionality of a particular feature of
 * the robot. It contains methods for commands (and other methods)
 *    to interact with the subsystem.
 * Example: An Elevator subsysytem might have commands which
 *    set a zero position setZeroPosition()
 *    get position getPosition()
 *    move hanger to a specific height setHeight()
 *
 * Describe the Subsystem functionality here
 */
public class ExampleSubsystem extends SubsystemBase {
    private static ExampleSubsystem instance = null;

    // creates a singleton class (only 1 instance can be instantiated)
    public static ExampleSubsystem getInstance() {
        if (instance == null) {
            instance = new ExampleSubsystem();
        }
    return instance;
    }

    public ExampleSubsystem(){

    }
}
