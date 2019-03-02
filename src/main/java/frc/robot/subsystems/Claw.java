/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClawDriver;

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX masterTalon;
  VictorSPX slave;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClawDriver());
  }

  public Claw(){
    masterTalon = new TalonSRX(RobotMap.clawMaster);
    slave = new VictorSPX(RobotMap.clawSlave);

    masterTalon.setInverted(true);
    slave.setInverted(false);
    slave.follow(masterTalon);
    resetEncoder();


  }

  public void drive( ControlMode mode, double value){
    masterTalon.set(mode, value);
  }
  public void resetEncoder() {
    //startPos = masterTalon.getSelectedSensorPosition(0);
    masterTalon.setSelectedSensorPosition(0);
  }
  

  public double getPos() {
    
    //return masterTalon.getSelectedSensorPosition(0) - startPos;
    return masterTalon.getSelectedSensorPosition(0);
	}

	public double getVel() {
    
		return masterTalon.getSelectedSensorVelocity(0);
  }
  
  public double getBusVoltage(){
    return masterTalon.getBusVoltage();

  }

  public void stop(){
    drive(ControlMode.PercentOutput, 0);
  }


}
