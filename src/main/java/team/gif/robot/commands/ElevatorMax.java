/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Constants;
import team.gif.robot.Robot;
import team.gif.robot.subsystems.CIMShooter;

/**
 * Describe the Command functionality here
 */
public class ElevatorMax extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  public ElevatorMax() {
      super();
      addRequirements(Robot.cimShooter);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      System.out.println("here .........");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      Robot.cimShooter.setSpeed(Constants.Climber.ELEVATOR_UP_UNLOADED_VOLTAGE);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return Robot.cimShooter.getClimberPos() > Constants.Climber.CLIMBER_MAX_POSITION;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

      System.out.println("here .........again");
      Robot.cimShooter.setSpeed(0);
  }
}
