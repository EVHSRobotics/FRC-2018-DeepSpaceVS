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
  double holdValue; 
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
    double value = -OI.joyXBox.getRawAxis(1);
    value = Math.abs(value) < .05 ? 0 : value;
    
    // if(value == 0){
    //   Robot.compressor.start();
    // }else{
    //   Robot.compressor.stop();
    // }
   // was .4
		double multiplier = 1;
		if (elevator.getPos() > 4000) {
			elevator.setHoldVal(.2);
    } 
    else {
      elevator.setHoldVal(0);
    }
    // elevator.drive(-(value * multiplier + holdValue), ControlMode.PercentOutput);
    elevator.drive(value*.5 + .1, ControlMode.PercentOutput);
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getPos());
    SmartDashboard.putNumber("Elevator throttle: ", value);

    

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
