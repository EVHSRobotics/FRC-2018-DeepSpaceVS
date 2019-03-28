/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
cargo mid - 26000
cargo high - 54800 
cargo intake - 5700
hatch low - 5900
hatch mid - 33700
hatch high - 57400
*/
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
 private double target;
 private double error = 0;
 double speed = .55;
 double lowSpeed = .45;
 double totalDistance;
 double currentDistance;
 double endSpeed = .33;

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
    System.out.println("starting elevator set point");
    totalDistance = Math.abs(target - elevator.getPos());
    currentDistance = totalDistance;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // currentDistance = Math.abs(target - elevator.getPos());
    // double inputSpeed = 0;
    // if (currentDistance < 1000){
    //   inputSpeed = endSpeed;
    // }else if(currentDistance > 1000 && currentDistance < 5000){
    //   inputSpeed = lowSpeed;
    // }else{
    //   inputSpeed = speed;
    // }
    if (target < elevator.getPos()){
      speed = Math.abs(speed) * -1;
    }
    elevator.drive(speed ,ControlMode.PercentOutput);
    
    System.out.println("elevator pos: "+ elevator.getPos());
    System.out.println("elevator speed: " + elevator.getVel());
    
    
    // if(Math.abs(elevator.getPos() - target) < 5500){
    //   speed = .3;
    // }
    
  
  SmartDashboard.putString("elevator start", "starting elevator");

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean isFinished = false;

  return elevator.isDone(target);

  

  }

  @Override
  protected void end() {
    
    elevator.end();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    // elevator.end();  
  }
 
}
