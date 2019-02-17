/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.Robot;

public class TurnOneEighty extends Command {
  DriveTrain drive;
  boolean stop = false;
  public TurnOneEighty() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive = (DriveTrain)(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
    drive.resetEncoders();
    stop = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(drive.getLtEncoders() - drive.getRtEncoders() < 40000) {
      drive.drive(ControlMode.PercentOutput, .75, -.75);
     } else if(drive.getLtEncoders() - drive.getRtEncoders() < 45000) drive.drive(ControlMode.PercentOutput, .75, -.75);
    else {
      stop = true;
      drive.stop();
     
    }
   // drive.drive(ControlMode.PercentOutput, .75, -.75);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   // drive.resetEncoders();
    return stop;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.resetEncoders();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
