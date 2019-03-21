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
  private double error = 50;
  Claw claw;
  boolean direction = true;

  public ClawSetPoint(double target) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   // requires(Robot.getSubsystem(SubsystemNames.CLAW));
    this.target = target;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    claw = (Claw) Robot.getSubsystem(SubsystemNames.CLAW);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
   protected void execute() {
  //   if(target < claw.getPos()){
  //   claw.drive(ControlMode.PercentOutput, -.3);
  //   }
  //   else{
  //   claw.drive(ControlMode.PercentOutput, .3);
  //   direction = false;
  //   }

    if (target < claw.getPos()) {
      claw.drive(ControlMode.PercentOutput, -.2);
    } else { // was 2000 COMP
      claw.drive(ControlMode.PercentOutput, .45); // was .35 COMP
      direction = false;
    }
    SmartDashboard.putString("claw start", "starting claw");

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean isFinished = false;
    // if(direction){
    // if (claw.getPos() > target)
    // isFinished = false;
    // else if (claw.getPos() < target){
    // isFinished = false;
    // }
    // }
    // else
    // isFinished = true;

    if (OI.XWheel.get())
      return true;

    return Math.abs(target - claw.getPos()) < 350;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    claw.drive(ControlMode.PercentOutput, .4);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    claw.drive(ControlMode.PercentOutput, 0);
  }
}
