package team.gif.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

/**
 * Describes the functionality of the NEO motor
 * Can use voltage as input or RPM
 */
public class NEOShooter extends SubsystemBase {
    private static NEOShooter instance = null;

    // creates a singleton class (only 1 instance can be instantiated)
    public static NEOShooter getInstance() {
        if (instance == null) {
            instance = new NEOShooter();
        }
        return instance;
    }

    private static final CANSparkMax shooterMotor = new CANSparkMax(RobotMap.MOTOR_SPARKMAX_ONE, CANSparkMaxLowLevel.MotorType.kBrushless);
    private static final CANPIDController shooterPIDController = shooterMotor.getPIDController();

    private NEOShooter() {
        super();

        shooterMotor.restoreFactoryDefaults();
        shooterMotor.enableVoltageCompensation(12);
        //shooterMotor.setInverted(true); // C:false P:true
        shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

        shooterPIDController.setP(0.00003); //0.0007
        shooterPIDController.setFF(0.00025); //0.000175

        shooterMotor.setSmartCurrentLimit(40,40);

        shooterMotor.burnFlash();
        //https://www.chiefdelphi.com/t/spark-max-current-limit/354333/3
    }

    public void   setVoltage(double voltage) {
        shooterMotor.setVoltage(voltage);
    }
    public void   setRPM (double setPoint) {
        shooterPIDController.setReference(setPoint, ControlType.kVelocity);
    }
    public double getRPM(){return shooterMotor.getEncoder().getVelocity();}
}