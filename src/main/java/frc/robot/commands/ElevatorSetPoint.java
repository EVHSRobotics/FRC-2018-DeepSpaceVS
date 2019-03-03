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
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.SubsystemNames;

public class ElevatorSetPoint extends Command {
 private double target = 0;
 private double error = 0;
 double speed = .75;
 

 private Elevator elevator;
  public ElevatorSetPoint(double target) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // requires(Robot.getSubsystem(SubsystemNames.ELEVATOR));
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    elevator = (Elevator) Robot.getSubsystem(SubsystemNames.ELEVATOR);

	// 	if (elevator.getPos() > target) {
	// 		System.out.println("Using up target speed");
	// 	//	elevator.updateTargetSpeed(Config.elevatorUpSpeed);
	// 	} else {
	// 		System.out.println("Using down target speed");
	// //		elevator.updateTargetSpeed(Config.elevatorDownSpeed);
  // 	}

  
		//elevator.drive(target, ControlMode.MotionMagic);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    elevator.drive(speed, ControlMode.PercentOutput);
    if(Math.abs(elevator.getPos() - target) < 5500){
      speed = .3;
    }
    
  
  SmartDashboard.putString("elevator start", "starting elevator");

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   boolean isFinished = false;
  if(Math.abs(elevator.getPos() - target) < 1500)  isFinished = true;
  else isFinished = false;

  // elevator.isDone();

   if(OI.buttonX3.get()) isFinished = true;

   return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    elevator.end();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    elevator.end();  
  }
 
}
