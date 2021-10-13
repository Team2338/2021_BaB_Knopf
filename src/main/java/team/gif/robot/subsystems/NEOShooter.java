package team.gif.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

public class NEOShooter extends SubsystemBase {
    private static NEOShooter instance = null;

    public static NEOShooter getInstance() {
        if (instance == null) {
            instance = new NEOShooter();
        }
        return instance;
    }

    private static final CANSparkMax shooterMotor = new CANSparkMax(RobotMap.MOTOR_SPARKMAX_ONE, CANSparkMaxLowLevel.MotorType.kBrushless);
    private static final CANPIDController shooterPIDController = shooterMotor.getPIDController();

    public void setVoltage(double voltage) {
        shooterMotor.setVoltage(voltage);
    }
    public void setRPM (double setPoint) {
        shooterPIDController.setReference(setPoint, ControlType.kVelocity);
    }

    private NEOShooter() {
        super();

        shooterMotor.restoreFactoryDefaults();
        shooterMotor.enableVoltageCompensation(12);
        //shooterMotor.setInverted(true); // C:false P:true
        shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

        shooterPIDController.setP(0.0007); //0.0007
        shooterPIDController.setFF(0.000175); //0.000175
        shooterPIDController.setOutputRange(0, 1);

        shooterMotor.setSmartCurrentLimit(40,40);

        shooterMotor.burnFlash();
        //https://www.chiefdelphi.com/t/spark-max-current-limit/354333/3
    }
}