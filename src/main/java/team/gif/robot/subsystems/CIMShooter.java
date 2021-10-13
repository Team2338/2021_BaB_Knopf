package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

/**
 * Describes the functionality of the CIM motor
 * Calling it the CIMShooter
 */
public class CIMShooter extends SubsystemBase {
    private static CIMShooter instance = null;

    // creates a singleton class (only 1 instance can be instantiated)
    public static CIMShooter getInstance() {
        if (instance == null) {
            instance = new CIMShooter();
        }
        return instance;
    }

    private static final TalonSRX CIMShooterMotor   = new TalonSRX(RobotMap.MOTOR_TALON_ONE);

    public CIMShooter() {
        CIMShooterMotor.setNeutralMode( NeutralMode.Brake);
    }

    // sets speed from -1 to 1
    public void setSpeed(double speed) {
        CIMShooterMotor.set(ControlMode.PercentOutput, speed);
    }
}
