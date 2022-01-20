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

/**
 * Describe the Command functionality here
 */
public class LowerElevator extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private boolean isHoldState = false;
  // Called when the command is initially scheduled.
  public LowerElevator() {
      super();
      addRequirements(Robot.cimShooter);
  }
  @Override
  public void initialize() {
    //Climb
      System.out.println("Ascending");
      isHoldState = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double climberPos = Robot.cimShooter.getClimberPos();
      if(climberPos < Constants.Climber.CLIMBER_ASCEND_POSITION) {
          isHoldState = true;
      }

      if (climberPos < 5000) {
          Robot.cimShooter.setSpeed(0);
          return;
      }

      if (isHoldState) {
          Robot.cimShooter.setSpeed(Constants.Climber.CLIMBER_HOLD_LOADED_VOLTAGE);
          return;
      }

      Robot.cimShooter.setSpeed(Constants.Climber.ELEVATOR_DOWN_LOADED_VOLTAGE);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      System.out.println("Ascending complete " + interrupted);
  }
}
