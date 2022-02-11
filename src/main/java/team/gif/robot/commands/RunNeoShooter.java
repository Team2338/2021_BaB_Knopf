package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Robot;

public class RunNeoShooter extends CommandBase {
	
	public RunNeoShooter() {
		super();
		addRequirements(Robot.neoShooter);
	}
	
	@Override
	public void initialize() {
		Robot.neoShooter.setVoltage(6);
	}
	
	@Override
	public void execute() {}
	
	@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public void end(boolean interrupted) {
		// Do nothing
	}
	
}
