/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Config;
import frc.robot.PIDTemplate;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorDrive;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX talon;

	private double startPos;

	private double targetSpeed = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ElevatorDrive());
  }

  public Elevator(){
    // talon = new TalonSRX(RobotMap.elevator);

		// talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		// talon.setInverted(false);
		// talon.setSensorPhase(true);
		// talon.setNeutralMode(NeutralMode.Brake);
		// talon.enableVoltageCompensation(true);
		// double P = 0; //.24d;
		// double I = 0; //.0001d;
		// double D = 0; // 15d;
		// double F = 0 ;// 1023d / 24000;
		// double targetSpeed = Config.elevatorUpSpeed;

		// PIDTemplate.updatePID(talon, P, I, D, F, targetSpeed);

		// startPos = talon.getSelectedSensorPosition(0);
  }

  // public void updateTargetSpeed(double targetSpeeD){
  //   talon.configMotionCruiseVelocity((int)targetSpeed, 10);
  //   talon.configMotionAcceleration((int)targetSpeed, 10);
  // }

  
	// public boolean isDone() {
	// 	System.out.println("Running is done: error: " + talon.getClosedLoopError(0) + " current pos "
  //       + talon.getSelectedSensorPosition(0) + " current target " + talon.getClosedLoopTarget(0));
        
	// //	return Math.abs(talon.getSelectedSensorPosition(0) - talon.getClosedLoopTarget(0)) < 700; // 700 is not final
	// }

	public void resetEncoder() {
	//	startPos = talon.getSelectedSensorPosition(0);
  }
  
  public void drive(double value, ControlMode mode){
  //  talon.set(mode, value);
  }

  public double getPos() {
    return 0.1;
//		return talon.getSelectedSensorPosition(0) - startPos;
	}

	public double getVel() {
    return 0.1;
	//	return talon.getSelectedSensorVelocity(0);
	}

  public void stop(){
  //  drive(0, ControlMode.PercentOutput);
  }
}
