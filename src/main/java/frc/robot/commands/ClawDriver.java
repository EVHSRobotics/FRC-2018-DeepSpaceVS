/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.SubsystemNames;

public class ClawDriver extends Command {
  Claw claw;
  double speed;
  double holdVal;
  double joyVal;

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
    double value = OI.joyXBox.getRawAxis(5);
    joyVal = Math.abs(value) < .05 ? 0 : value;

  //default hold with ball
  
    if(claw.getPos() > 240){ //should try to go against throttle
      holdVal = -.07;
    }
   // holdVal = .16;
    if(claw.getPos() > 1000){ //increase hold value as claw passes mid angle
      holdVal = -.16;
    }
   
    // if(claw.getPos() > 2700 && joyVal < 0){ //was -2700 on practiceBot
    //   holdVal = -.18;
    //   joyVal = 0;
    // }
    // if(Math.abs(claw.getPos()) > 2700 && joyVal < 0) {
		// 	joyVal = 0;
		// }
    speed = joyVal*.5 + holdVal;
    System.out.println("claw value: " + speed);
    claw.drive(ControlMode.PercentOutput, speed);


    
   
    SmartDashboard.putNumber("Claw encoder: ", claw.getPos());
    SmartDashboard.putNumber("Claw power: ", speed);
    SmartDashboard.putNumber("Claw Joy Value", joyVal);
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
