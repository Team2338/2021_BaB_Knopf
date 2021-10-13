/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import team.gif.robot.Globals;
import team.gif.robot.Robot;
import team.gif.robot.subsystems.CIMShooter;
import team.gif.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Responds to left joystick input for speed of the CIM
 */
public class CIMJoystickControl extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public CIMJoystickControl() {
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Need to ignore the joystick if the user is pushing the buttons which also
        // control speed
        if(!Globals.g_buttonControl) {
            // max the output to 30%, otherwise motor goes off rails
            // get the joystick reading
            double currPercent = Robot.oi.driver.getY(GenericHID.Hand.kLeft);
            // get the sign (positive or negative)
            int sign =  (currPercent > 0) ? 1 : -1;
            // use the sign and the absolute value of the reading maxed at 30%
            currPercent = sign *  Math.min(Math.abs(currPercent),0.3);

            // Req 5
            CIMShooter.getInstance().setSpeed(currPercent);
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
}
