package team.gif.robot.commands;

import team.gif.robot.Globals;
import team.gif.robot.Robot;
import team.gif.robot.subsystems.CIMShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sends the appropriate speed to the CIM
 */
public class RunCIMShooter extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    // used to determine if the motor is to spin forward or reverse
    // based on the inbound parameter
    boolean _forward;

    public RunCIMShooter(boolean isForward) {
        super();
//        addRequirements(CIMShooter.getInstance());
        _forward = isForward;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // set this global variable to true so other classes
        // // know we have control of the joystick
        Globals.g_buttonControl = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        double speed = 0.8;

        // Req 9
        if ( Robot.bumpSwitch.getLimitState() == true) {
            speed = speed / 2;
        }

        if(_forward) {
            // Req 3
            Robot.cimShooter.setSpeed(speed);
        } else {
            // Req 4
            Robot.cimShooter.setSpeed(-speed);
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.cimShooter.setSpeed(0);
        Globals.g_buttonControl = false;
    }
}