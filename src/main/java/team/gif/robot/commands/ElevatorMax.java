/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.CIMShooter;

/**
 * Describe the Command functionality here
 */
public class ElevatorMax extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      CIMShooter.getInstance().setSpeed(0.8);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return CIMShooter.getInstance().getClimberPos() > 24000;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      CIMShooter.getInstance().setSpeed(0);
  }
}
