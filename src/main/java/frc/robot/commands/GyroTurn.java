/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;

public class GyroTurn extends Command {
  double targetTurnVal;
  boolean direction;
  DriveTrain drive;
  double speed;
  AHRS navX;
  public GyroTurn(double turnValue, boolean direction, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.targetTurnVal = turnValue;
    this.direction = direction;
    this.speed = speed;
    try{

      navX = new AHRS(Port.kUSB1);
      Timer.delay(1);
      navX.reset();
      Timer.delay(1);
      
    }catch(NullPointerException e){
      System.out.println(e.getMessage());
    }
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive = (DriveTrain)(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN)); 
   Timer.delay(1);
    navX.reset();
    Timer.delay(1);

    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Current Angle:", navX.getAngle());
    //System.out.println("current angle: " + navX.getAngle());
    if(direction){
      drive.drive(ControlMode.PercentOutput, -speed , 0); //turn right
    } 
    if(!direction) {
      drive.drive(ControlMode.PercentOutput, 0, -speed); //turn left
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   SmartDashboard.putNumber("turn Diff: ", Math.abs(targetTurnVal - Math.abs(navX.getAngle())));
   System.out.println("turn Diff: " +  Math.abs(targetTurnVal - navX.getAngle()));
    return Math.abs( targetTurnVal - Math.abs(navX.getAngle())) < 50;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.drive(ControlMode.PercentOutput, 0 , 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drive.drive(ControlMode.PercentOutput, 0, 0);
  }
}
