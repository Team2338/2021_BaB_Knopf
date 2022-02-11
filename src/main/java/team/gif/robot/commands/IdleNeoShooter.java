package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Robot;

public class IdleNeoShooter extends CommandBase {
	
	public IdleNeoShooter() {
		super();
		addRequirements(Robot.neoShooter);
	}
	
	@Override
	public void initialize() {
		Robot.neoShooter.setVoltage(0);
	}
	
	@Override
	public void execute() {
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
