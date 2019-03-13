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
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.SubsystemNames;

public class ElevatorDrive extends Command {
  Elevator elevator;
  double holdVal; 
  public ElevatorDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.getSubsystem(SubsystemNames.ELEVATOR));
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    elevator = (Elevator) Robot.getSubsystem(SubsystemNames.ELEVATOR);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double joyVal = OI.joyXBox.getRawAxis(1);
    joyVal = Math.abs(joyVal) < .05 ? 0 : joyVal;

   
    
  //   if(elevator.getPos() > 100){ //should try to go against throttle
  //     holdVal = -.07;
  //   }
  //  // holdVal = .16;
  //   if(elevator.getPos() > 150){ //increase hold value as claw passes mid angle
  //     holdVal = -.16;
  //   }
  //   if(elevator.getPos() > 55000 && joyVal > 0){
  //     joyVal = 0;
  //     holdVal = -.17;
  //   }

  //  if(elevator.getPos() > 2700 && joyVal > 0){ //was -2700 on practiceBot
  //     holdVal = -.18;
  //     joyVal = 0;
  //   }
    // elevator.drive(-(value * multiplier + holdValue), ControlMode.PercentOutput);
    elevator.drive(joyVal*.9 + holdVal, ControlMode.PercentOutput);
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getPos());
    SmartDashboard.putNumber("Elevator throttle: ", joyVal);

    

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
