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
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;

public class EncoderTurn extends Command {
  private DriveTrain drive;
	private double revs, inchs;
	private double leftTarget, rightTarget;
  public EncoderTurn(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
		this.inchs = 79.25 * angle / 360d;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive = ((DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
    this.revs = drive.inchesToCycles(inchs);
		drive.setNeutralMode(NeutralMode.Coast);
		// System.out.println("Driving");
		drive.drive( ControlMode.MotionMagic, -revs, revs);
		leftTarget = (-revs * drive.getDriveConstant()) + drive.getLtEncoders();
		rightTarget = (revs * drive.getDriveConstant()) + drive.getRtEncoders();
		
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(drive.getLtEncoders() - leftTarget) < 300
				&& Math.abs(drive.getRtEncoders() - rightTarget) < 300) || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.setNeutralMode(NeutralMode.Brake);
	//	drive.drive(drive.getAvgEncoder(), drive.getAvgEncoder(), ControlMode.Position);
		drive.drive( ControlMode.Velocity, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drive.setNeutralMode(NeutralMode.Brake);

  }
}
