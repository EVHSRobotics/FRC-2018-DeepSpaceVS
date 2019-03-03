/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;

public class Turn extends Command {
  double turnVal;
  boolean right;
  boolean left;
  DriveTrain drive;
  double speed;
  public Turn(boolean right, boolean left, double turnVal, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.right = right;
    this.left = left;
    this.turnVal = turnVal;
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive = (DriveTrain)(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN)); 
    //drive.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(right) drive.drive(ControlMode.PercentOutput, -speed, speed);
    if(left) drive.drive(ControlMode.PercentOutput, 0, speed);

  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(right){
      System.out.println("right check: " + Math.abs(drive.getRtEncoders() -turnVal));
      return Math.abs(drive.getRtEncoders() -turnVal) < 100;
    }if(left){

      System.out.println("left check: " + Math.abs(Math.abs(drive.getLtEncoders()) - turnVal));

      return Math.abs((drive.getLtEncoders()) - turnVal) < 1000;

    }
    return false;
  }

  // Called once after isFinished returns true
  @Override

  protected void end() {
    drive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drive.stop();
  }
}
