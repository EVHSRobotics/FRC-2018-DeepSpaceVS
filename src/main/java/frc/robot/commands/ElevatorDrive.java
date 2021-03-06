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
    // double holdValue = .15; // was .4
		// double multiplier = 1;
		// if (elevator.getPos() > -1500) {
		// 	holdValue = 0;
		// } else {
		// 	multiplier = .9;
		// }
    // elevator.drive(-(value * multiplier + holdValue), ControlMode.PercentOutput);
    elevator.drive(value*.8, ControlMode.PercentOutput);
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getPos());
    SmartDashboard.putNumber("Elevator voltage: ", elevator.getVoltage());

    

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
