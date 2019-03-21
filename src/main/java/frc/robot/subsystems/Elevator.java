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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.PIDTemplate;
import frc.robot.RobotMap;
import frc.robot.Config;
import frc.robot.commands.ElevatorDrive;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX masterTalon;
  private VictorSPX slaveVic;

	private double startPos;

  private double targetSpeed = 0;
  private double holdValue = .19;
//peal: 57483
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ElevatorDrive());
  }

  public Elevator(){
    masterTalon = new TalonSRX(RobotMap.elevatorMaster);
    slaveVic = new VictorSPX(RobotMap.elevatorSlave);
    masterTalon.setNeutralMode(NeutralMode.Brake);   
    slaveVic.setNeutralMode(NeutralMode.Brake);


    slaveVic.follow(masterTalon);

    resetEncoder();
   //-2500 up speed
   //3000 down speed

		masterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 11);
		masterTalon.setInverted(false);
		masterTalon.setSensorPhase(false);
		masterTalon.setNeutralMode(NeutralMode.Brake);
		masterTalon.enableVoltageCompensation(true);
		// double P = 0; //.24d;
		// double I = 0;
		// double D =  0;
		// double F =  1023d / 2400000;
		// double targetSpeed = 2400000d; 

		// PIDTemplate.updatePID(masterTalon, P, I, D, F, targetSpeed);

     startPos = masterTalon.getSelectedSensorPosition(0);
     masterTalon.enableVoltageCompensation(true);
     slaveVic.enableVoltageCompensation(true);
  }

  public void updateTargetSpeed(double targetSpeeD){
    masterTalon.configMotionCruiseVelocity((int)targetSpeed, 10);
    masterTalon.configMotionAcceleration((int)targetSpeed, 10);
  }

  
	// public boolean isDone() {
	// 	System.out.println("Running is done: error: " + masterTalon.getClosedLoopError(0) + " current pos "
  //       + masterTalon.getSelectedSensorPosition(0) + " current target " + masterTalon.getClosedLoopTarget(0));
        
	// //	return Math.abs(masterTalon.getSelectedSensorPosition(0) - masterTalon.getClosedLoopTarget(0)) < 700; // 700 is not final
	// }


  
  public void drive(double value, ControlMode mode){
    masterTalon.set(mode, value);
  }

  public double getPos() {
    
    return masterTalon.getSelectedSensorPosition(0) - startPos;
    // return masterTalon.getSelectedSensorPosition(0);
	}

	public double getVel() {
   // return 0.1;
		return masterTalon.getSelectedSensorVelocity(0);
	}

  public double getVoltage(){
    return masterTalon.getBusVoltage();
  }
  

  public void end(){
    drive(0, ControlMode.PercentOutput);
  }

  public double getHoldVal(){
    return holdValue;
  }

  public void setHoldVal(double hold){
      holdValue = hold;
  }

 
  
  public boolean isDone(double target) {
		// System.out.println("Running is done: error: " + talon.getClosedLoopError(0) + " current pos "
		// 		+ talon.getSelectedSensorPosition(0) + " current target " + talon.getClosedLoopTarget(0));
		return Math.abs(masterTalon.getSelectedSensorPosition(0) - target) < 300;
	}

	public void resetEncoder() {
		startPos = masterTalon.getSelectedSensorPosition(0);
	}

}
