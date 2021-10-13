
package team.gif.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import team.gif.robot.commands.CIMJoystickControl;
import team.gif.robot.subsystems.LimitSwitch;
import team.gif.robot.subsystems.drivers.Pigeon;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static OI oi;
  public static LimitSwitch bumpSwitch;
  public static Pigeon myPigeon;
  public static WPI_TalonSRX myTalon;

  public static CIMJoystickControl CIMJoystickControlCommand = null;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    System.out.println("robot init");

    bumpSwitch = new LimitSwitch();

    myTalon = new WPI_TalonSRX(RobotMap.MOTOR_TALON_ONE);

    myPigeon = new Pigeon(myTalon);
    myPigeon.resetPigeonPosition(); // set initial heading of pigeon to zero degrees

    // Req 7
    ShuffleboardTab   tab  = Shuffleboard.getTab("SmartDashboard");
    tab.add("BotHead",(x)->{x.setSmartDashboardType("Gyro");x.addDoubleProperty("Value", ()->getCompassHeading(),null);});

    Globals.g_buttonControl = false;
    CIMJoystickControlCommand = new CIMJoystickControl();
  }

  // wrapper function to get the compass heading from the pigeon instance
  public double getCompassHeading(){
    return myPigeon.getCompassHeading();
  };
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

    // Req 3
//    System.out.println(bumpSwitch.getLimitState());

    // Req 6
    SmartDashboard.putBoolean("BumpSwitch", bumpSwitch.getLimitState());

    // Req 4
//    System.out.println(myPigeon.get360Heading());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    System.out.println("teleop init");

    oi = new OI();

    CIMJoystickControlCommand.schedule();
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
  public void simulationPeriodic(){
  }
}