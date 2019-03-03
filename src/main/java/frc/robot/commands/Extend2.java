/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Hatch;
import frc.robot.*;
import frc.robot.subsystems.SubsystemNames;
public class Extend2 extends Command {
  Hatch hatch;
  public Extend2() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatch = (Hatch)(Robot.getSubsystem(SubsystemNames.HATCH));
    System.out.println("shifting hatch 2");
    //hatch.toggleHatch2();
    hatch.extendPunch();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
