/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.SubsystemNames;

public class ClawDriver extends Command {
  Claw claw;
  double speed;
  double holdVal;

  public ClawDriver() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.getSubsystem(SubsystemNames.CLAW));

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    claw = (Claw) Robot.getSubsystem(SubsystemNames.CLAW);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double value = -OI.joyXBox.getRawAxis(5);
    value = Math.abs(value) < .05 ? 0 : value;

  //default hold with ball
  
    if(claw.getPos() < -370){
      holdVal = .11;
    }
   // holdVal = .16;
    if(claw.getPos() < -1000){ //increase hold value as claw passes mid angle
      holdVal = .15;
    }
   
    if(claw.getPos() < -2700){
      holdVal = .18;
    }
    value = value*.5 + holdVal;
    System.out.println("claw value: " + value);
    claw.drive(ControlMode.PercentOutput, value);
     //if not past limits
    //  if (claw.getPos() < -50) {
    //   holdVal = .16;

    // }
   
    SmartDashboard.putNumber("Claw encoder: ", claw.getPos());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    claw.drive( ControlMode.PercentOutput, 0);
    }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    claw.drive( ControlMode.PercentOutput, 0);

  }
}
