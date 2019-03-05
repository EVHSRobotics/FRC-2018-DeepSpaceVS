/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;

public class DriveStraight extends Command {

	private DriveTrain drive;
	private double speed, heading, distance, ogDistance;
  private double startingDistance;
  
  
 /**
  * drive straight for a given distance at a given speed
  @param speed - measured in power from 0 to 1
  @param distance - measured in inches
  */
	public DriveStraight(double speed, double distance) {
	//	requires(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
		this.distance = distance;
		this.ogDistance = distance;
		this.speed = speed;
		this.heading = heading;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
   
		drive = (DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN);
    drive.resetEncoders();
		//this.distance = Config.cyclesToInchesFactor * ogDistance;
		this.distance = ogDistance;
		startingDistance = drive.getAvgEncoders();
		
    drive.setNeutralMode(NeutralMode.Brake);
    
   
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	//	double angle = Robot.getSensors().getNavX().getAngle();
  //	double diff = (heading - angle) / 90;
    
    drive.drive(ControlMode.PercentOutput, speed, speed);
    
  }
  

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println("encoders: " + drive.getAvgEncoders() + " start dist: " + startingDistance + " distanace: " + distance);
		return Math.abs(drive.getAvgEncoders() - startingDistance) > Math.abs(distance);
	}

	// Called once after isFinished returns true
	protected void end() {
    System.out.println("Reached " + distance);
    drive.resetEncoders();
		drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
