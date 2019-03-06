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
import frc.robot.Robot;
import frc.robot.SensorBoard;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SubsystemNames;

public class IntakeTillBanner extends Command {
  private SensorBoard board;
  private double  speed;
  private boolean bannerDetected;
	private Intake intake;
	private long startTime;
  private boolean shouldTimeOut;
  private boolean trigger;
  private DigitalInput banner;
	
  public IntakeTillBanner(boolean trigger, double speed, boolean shouldTimeOut) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.speed = speed;
    this.trigger = trigger;
    this.shouldTimeOut = shouldTimeOut;
   // this.board = Robot.getSensors();
    banner = new DigitalInput(0);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    intake = (Intake)Robot.getSubsystem(SubsystemNames.INTAKE);
    startTime = System.nanoTime();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    intake.drive(ControlMode.PercentOutput, speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if((banner.get() == false)) {
      System.out.println("Finishing due to ultra trigger");
    }
    if((shouldTimeOut && (System.nanoTime() - startTime)/1E9d > 5)) {
      System.out.println("Finishing due to time out");
    }
      return (banner.get() == false) || (shouldTimeOut && (System.nanoTime() - startTime)/1E9d > 5);
  
    
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
  }
}
