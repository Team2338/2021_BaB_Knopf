package team.gif.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import team.gif.robot.commands.IdleShooter;
import team.gif.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static OI oi;
	public static Shooter shooter;
	
	
	/**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Robot init");
		
		shooter = new Shooter();
		shooter.setDefaultCommand(new IdleShooter());
		shooter.initDiagnostics();
		
		SmartDashboard.putData(shooter);
	}
	
	
	/**
	 * This function is called every robot packet, no matter the mode. Use this for items like
	 * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
	 *
	 * <p>This runs after the mode specific periodic functions, but before
	 * LiveWindow and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		
		shooter.updateDiagnostics();
	}
	
	
	@Override
	public void disabledInit() {
	}
	
	
	@Override
	public void disabledPeriodic() {
	}
	
	
	@Override
	public void autonomousInit() {
	}
	
	
	@Override
	public void autonomousPeriodic() {
	}
	
	
	@Override
	public void teleopInit() {
		System.out.println("teleop init");
		
		oi = new OI();
	}
	
	
	@Override
	public void teleopPeriodic() {
	}
	
	
	@Override
	public void testInit() {
	}
	
	
	@Override
	public void testPeriodic() {
	}
	
	
	@Override
	public void simulationInit() {
	}
	
	
	@Override
	public void simulationPeriodic() {
	}
}
