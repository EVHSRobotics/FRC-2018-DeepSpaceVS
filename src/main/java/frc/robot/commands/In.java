/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SubsystemNames;
public class In extends Command {
  Intake intake;
  double speed;
  DigitalInput banner;
  public In(double value) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   // requires(Robot.getSubsystem(SubsystemNames.INTAKE));
    speed = value;
    //banner = Robot.getSensors().getBanner();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    intake = (Intake) Robot.getSubsystem(SubsystemNames.INTAKE);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   // SmartDashboard.putBoolean("banner", banner.get());
    
  //  if(banner.get()){
  //     speed = 0.2;
  //  }

   intake.drive(ControlMode.PercentOutput, speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   // return !banner.get();
    return intake.getShouldIntakeAutoRun();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intake.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    intake.stop();
  }
}
