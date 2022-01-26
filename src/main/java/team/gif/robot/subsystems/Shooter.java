package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.Constants;
import team.gif.robot.RobotMap;

public class Shooter extends SubsystemBase {
	
	private final TalonSRX flywheel = new TalonSRX(RobotMap.MOTOR_TALON_ONE);
	private double kP = Constants.Shooter.FLYWHEEL_kP;
	private double kI = Constants.Shooter.FLYWHEEL_kI;
	private double kD = Constants.Shooter.FLYWHEEL_kD;
	private double kF = Constants.Shooter.FLYWHEEL_kF;
	
	public Shooter() {
		super();
		
		flywheel.configFactoryDefault();
		
		flywheel.setNeutralMode(NeutralMode.Brake);
		flywheel.setInverted(InvertType.InvertMotorOutput);
		
		// Configure soft and hard limits
		flywheel.configForwardSoftLimitEnable(false);
		flywheel.configReverseSoftLimitEnable(false);
		flywheel.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
		flywheel.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
		
		// Configure the sensor
		flywheel.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.QuadEncoder, 0, 0);
//		flywheel.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)
		flywheel.setSensorPhase(true);
		flywheel.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms);
//		flywheel.configVelocityMeasurementWindow(64);
		
		// Configure PID settings
		flywheel.config_kP(0, Constants.Shooter.FLYWHEEL_kP);
		flywheel.config_kI(0, Constants.Shooter.FLYWHEEL_kI);
		flywheel.config_kD(0, Constants.Shooter.FLYWHEEL_kD);
		flywheel.config_kF(0, Constants.Shooter.FLYWHEEL_kF);
//		flywheel.config_IntegralZone(0, Constants.Shooter.FLYWHEEL_IZONE);
//		flywheel.configMaxIntegralAccumulator(0, 10000);
		flywheel.selectProfileSlot(0, 0);
	}
	
	
	/**
	 * Set initial values for all SmartDashboard outputs, so that they immediately
	 * appear on the screen.
	 */
	public void initDiagnostics() {
		SmartDashboard.putNumber("Flywheel/kP", kP);
		SmartDashboard.putNumber("Flywheel/kI", kI);
		SmartDashboard.putNumber("Flywheel/kD", kD);
		SmartDashboard.putNumber("Flywheel/kF", kF);
		
		SmartDashboard.putNumber("Flywheel Velocity", flywheel.getSelectedSensorVelocity());
		SmartDashboard.putBoolean("Flywheel/Is in Tolerance", false);
		SmartDashboard.putNumber("Flywheel/Setpoint", 0);
		SmartDashboard.putNumber("Flywheel/Error", 0);
		SmartDashboard.putNumber("Flywheel/kP_Gain", 0);
		SmartDashboard.putNumber("Flywheel/kI_Gain", 0);
		SmartDashboard.putNumber("Flywheel/kD_Gain", 0);
		SmartDashboard.putNumber("Flywheel/kF_Gain", 0);
	}
	
	
	/**
	 * Update SmartDashboard outputs based on current control mode.
	 */
	public void updateDiagnostics() {
		SmartDashboard.putNumber("Flywheel Velocity", flywheel.getSelectedSensorVelocity());
		SmartDashboard.putBoolean("Flywheel/Is in Tolerance", isWithinTolerance());
		
		if (flywheel.getControlMode() == ControlMode.Velocity) {
			SmartDashboard.putNumber("Flywheel/Setpoint", flywheel.getClosedLoopTarget());
			SmartDashboard.putNumber("Flywheel/Error", flywheel.getClosedLoopError());
			SmartDashboard.putNumber("Flywheel/kP_Gain", flywheel.getClosedLoopError() * kP);
			SmartDashboard.putNumber("Flywheel/kI_Gain", flywheel.getIntegralAccumulator() * kI);
			SmartDashboard.putNumber("Flywheel/kD_Gain", flywheel.getErrorDerivative() * kD);
			SmartDashboard.putNumber("Flywheel/kF_Gain", flywheel.getClosedLoopTarget() * kF);
		}
	}
	
	
	public void setPercentVelocity(double vel)  {
		flywheel.set(TalonSRXControlMode.PercentOutput, vel);
	}
	
	
	public void setPidVelocity(double setpoint) {
		flywheel.set(TalonSRXControlMode.Velocity, setpoint);
	}
	
	
	public void fetchPidConstants() {
		kP = SmartDashboard.getNumber("Flywheel/kP", kP);
		kI = SmartDashboard.getNumber("Flywheel/kI", kI);
		kD = SmartDashboard.getNumber("Flywheel/kD", kD);
		kF = SmartDashboard.getNumber("Flywheel/kF", kF);
	}
	
	
	public double getVelocity() {
		return flywheel.getSelectedSensorVelocity();
	}
	
	
	public boolean isWithinTolerance() {
		if (flywheel.getControlMode() != ControlMode.Velocity) {
			return false;
		}
		
		boolean isWithinVelTolerance = Math.abs(flywheel.getClosedLoopError()) < Constants.Shooter.FLYWHEEL_VELOCITY_TOLERANCE;
		boolean isWithinAccTolerance = Math.abs(flywheel.getErrorDerivative()) < Constants.Shooter.FLYWHEEL_ACCELERATION_TOLERANCE;
		
		return isWithinVelTolerance && isWithinAccTolerance;
	}
	
}
