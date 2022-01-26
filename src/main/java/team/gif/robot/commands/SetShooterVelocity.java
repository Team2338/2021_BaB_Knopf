package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Robot;

/**
 * Uses PID to set the velocity of the flywheel
 */
public class SetShooterVelocity extends CommandBase {
	
	public SetShooterVelocity() {
		super();
		addRequirements(Robot.shooter);
	}
	
	@Override
	public void initialize() {
		Robot.shooter.fetchPidConstants();
		Robot.shooter.setPidVelocity(16000);
	}
	
	@Override
	public void execute() {
		// Do nothing
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public void end(boolean interrupted) {
		Robot.shooter.setPercentVelocity(0);
	}
	
}
