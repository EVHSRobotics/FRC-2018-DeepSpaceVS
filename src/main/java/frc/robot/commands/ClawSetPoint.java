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

public class ClawSetPoint extends Command {
  private double target = 0;
  private double error = 0;
  Claw claw;
  double startPos = 0;
  boolean isFinished = false;
  double speed;

  private boolean isGoingDown = true;
  public ClawSetPoint(double target) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.target = target;
  
    
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    claw = (Claw) Robot.getSubsystem(SubsystemNames.CLAW);
    startPos = claw.getPos();
    if(startPos < target){
      isGoingDown = false;
      System.out.println("is going down is false");
    }else{
      isGoingDown = true;
      System.out.println("is going down is true");
      
    }

    SmartDashboard.putBoolean("claw running: ", isFinished);

    SmartDashboard.putNumber("claw target: ", target);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //speed = .7;

    if(isGoingDown) speed = -.3;
    else speed = .7;

    SmartDashboard.putBoolean("isGoingDown : " , isGoingDown);


    claw.drive(ControlMode.PercentOutput, speed);

    SmartDashboard.putString("claw start", "starting claw");
  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   
    

    if(Math.abs(claw.getPos() - target) < 50)  isFinished = true;
    else isFinished = false;
    if(Math.abs(claw.getPos()) > Math.abs(target)) isFinished = true;
    if (OI.buttonX3.get()){
     return true;
    }

    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    claw.drive(ControlMode.PercentOutput, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
     claw.drive(ControlMode.PercentOutput, 0);
  }
}
