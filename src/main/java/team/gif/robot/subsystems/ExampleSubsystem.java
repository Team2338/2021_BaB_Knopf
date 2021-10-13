
package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
    private static ExampleSubsystem instance = null;

    public static ExampleSubsystem getInstance() {
        if (instance == null) {
            instance = new ExampleSubsystem();
        }
    return instance;
    }

    public ExampleSubsystem(){

    }
}
