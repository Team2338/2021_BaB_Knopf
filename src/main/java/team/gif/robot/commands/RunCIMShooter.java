package team.gif.robot.commands;

import team.gif.robot.Globals;
import team.gif.robot.Robot;
import team.gif.robot.subsystems.CIMShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class RunCIMShooter extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    boolean _forward;

    public RunCIMShooter(boolean isForward) {
        _forward = isForward;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Globals.g_buttonControl = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        double speed = 0.2;

        // Req 12
        if ( Robot.bumpSwitch.getLimitState() == true) {
            speed = speed / 2;
        }

        if(_forward) {
            // Req 1
            CIMShooter.getInstance().setSpeed(speed);
        } else {
            // Req 2
            CIMShooter.getInstance().setSpeed(-speed);
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
        CIMShooter.getInstance().setSpeed(0);
        Globals.g_buttonControl = false;
    }
}