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
   
    claw.drive( ControlMode.PercentOutput, value*.5);
    SmartDashboard.putNumber("Claw encoder: ", claw.getPos());
    SmartDashboard.putNumber("Claw voltage: ", claw.getBusVoltage());
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
