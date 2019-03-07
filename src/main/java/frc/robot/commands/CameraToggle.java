/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;

public class CameraToggle extends Command {
  DriveTrain drive;
  public CameraToggle() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive = (DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(drive.getCamera1Run()){
      Robot.getCam1().start();
      drive.setCamera1(false);
    }else{
      Robot.getCam1().stop();
      Robot.getCam2().start();
      drive.setCamera1(true);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
