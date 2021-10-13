/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.NEOShooter;

/**
 * An example command that uses an example subsystem.
 */
public class RunNEOShooterVoltage extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public RunNEOShooterVoltage() {
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize(){
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        NEOShooter.getInstance().setVoltage(2.5);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        NEOShooter.getInstance().setVoltage(0);
    }
}
