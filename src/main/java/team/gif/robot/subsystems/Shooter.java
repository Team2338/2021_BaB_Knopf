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
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.Constants;
import team.gif.robot.RobotMap;

public class Shooter extends SubsystemBase {
	
	private final TalonSRX flywheel = new TalonSRX(RobotMap.MOTOR_TALON_ONE);
	private double kP = Constants.Shooter.FLYWHEEL_kP;
	private double kI = Constants.Shooter.FLYWHEEL_kI;
	private double kD = Constants.Shooter.FLYWHEEL_kD;
	private double kF = Constants.Shooter.FLYWHEEL_kF;
	private Runnable updateDiagnostics;
	private Runnable updatePidConstants;
	
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
		flywheel.setSensorPhase(false);
		flywheel.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms);
//		flywheel.configVelocityMeasurementWindow(64);
		
		// Configure PID settings
		flywheel.config_kP(0, kP);
		flywheel.config_kI(0, kI);
		flywheel.config_kD(0, kD);
		flywheel.config_kF(0, kF);
//		flywheel.config_IntegralZone(0, Constants.Shooter.FLYWHEEL_IZONE);
//		flywheel.configMaxIntegralAccumulator(0, 10000);
		flywheel.selectProfileSlot(0, 0);
	}
	
	/**
	 * Set initial values for all SmartDashboard outputs, so that they immediately
	 * appear on the screen.
	 */
	public void initDiagnostics() {
		ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
		
		NetworkTableEntry velocityEntry = tab.add("Velocity", 0)
				.withWidget(BuiltInWidgets.kGraph)
				.withSize(3, 2)
				.withPosition(0, 0)
				.getEntry();
		NetworkTableEntry toleranceEntry = tab.add("Is in Tolerance", false)
				.withWidget(BuiltInWidgets.kBooleanBox)
				.getEntry();
		NetworkTableEntry setpointEntry = tab.add("Setpoint", 0).getEntry();
		NetworkTableEntry errorEntry = tab.add("Error", 0).getEntry();
		NetworkTableEntry pGainEntry = tab.add("Gains", 0)
				.withWidget(BuiltInWidgets.kGraph)
				.getEntry();
//		NetworkTableEntry iGainEntry = tab.add("kI_Gain", 0).getEntry();
//		NetworkTableEntry dGainEntry = tab.add("kD_Gain", 0).getEntry();
//		NetworkTableEntry fGainEntry = tab.add("kF_Gain", 0).getEntry();
		
		this.updateDiagnostics = () -> {
			velocityEntry.setDouble(this.getVelocity());
			
			if (flywheel.getControlMode() == ControlMode.Velocity) {
				toleranceEntry.setBoolean(this.isWithinTolerance());
				setpointEntry.setDouble(flywheel.getClosedLoopTarget());
				errorEntry.setDouble(flywheel.getClosedLoopError());
				pGainEntry.setDouble(this.getGainP());
//				iGainEntry.setDouble(this.getGainI());
//				dGainEntry.setDouble(this.getGainD());
//				fGainEntry.setDouble(this.getGainF());
				
				pGainEntry.setDoubleArray(new double[] { this.getGainP(), this.getGainI(), this.getGainD(), this.getGainF() });
			}
		};
		
		// Add entries to configure PIDF constants on the fly
		ShuffleboardLayout layout = tab
				.getLayout("PIDF Constants", BuiltInLayouts.kList)
				.withSize(1, 2);
		NetworkTableEntry pEntry = layout.add("kP", kP).getEntry();
		NetworkTableEntry iEntry = layout.add("kI", kI).getEntry();
		NetworkTableEntry dEntry = layout.add("kD", kD).getEntry();
		NetworkTableEntry fEntry = layout.add("kF", kF).getEntry();
		
		this.updatePidConstants = () -> {
			kP = pEntry.getDouble(kP);
			kI = iEntry.getDouble(kI);
			kD = dEntry.getDouble(kD);
			kF = fEntry.getDouble(kF);
			flywheel.config_kP(0, kP);
			flywheel.config_kI(0, kI);
			flywheel.config_kD(0, kD);
			flywheel.config_kF(0, kF);
		};
	}
	
	/**
	 * Update SmartDashboard outputs based on current control mode.
	 */
	public void updateDiagnostics() {
		this.updateDiagnostics.run();
	}
	
	public void setPercentVelocity(double vel)  {
		flywheel.set(TalonSRXControlMode.PercentOutput, vel);
	}
	
	public void setPidVelocity(double setpoint) {
		flywheel.set(TalonSRXControlMode.Velocity, setpoint);
	}
	
	public void updatePidConstants() {
		this.updatePidConstants.run();
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
	
	private double getGainP() {
		return normalizeGain(kP * flywheel.getClosedLoopError());
	}
	
	private double getGainI() {
		return normalizeGain(kI * flywheel.getIntegralAccumulator());
	}
	
	private double getGainD() {
		return normalizeGain(kD * flywheel.getErrorDerivative());
	}
	
	private double getGainF() {
		return normalizeGain(kF * flywheel.getClosedLoopTarget());
	}
	
	private double normalizeGain(double rawGain) {
		return Math.min(rawGain / 1023, 1.0);
	}
	
}
