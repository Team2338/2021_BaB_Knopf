package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Robot;

public class IdleShooter extends CommandBase {
	
	public IdleShooter() {
		super();
		addRequirements(Robot.shooter);
	}
	
	@Override
	public void initialize() {
		// Do nothing
	}
	
	@Override
	public void execute() {
		Robot.shooter.setPercentVelocity(0);
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public void end(boolean interrupted) {
		// Do nothing
	}
	
}
