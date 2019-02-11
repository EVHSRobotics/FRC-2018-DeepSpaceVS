/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX left1, left2, left3, right1, right2, right3;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new JoystickDrive());

  }

  public DriveTrain(){
      left1 = new TalonSRX(RobotMap.leftT1);
      left2 = new TalonSRX(RobotMap.leftT2);
      left3 = new TalonSRX(RobotMap.leftT3);

      right1 = new TalonSRX(RobotMap.rightT1);
      right2 = new TalonSRX(RobotMap.rightT2);
      right3 = new TalonSRX(RobotMap.rightT3);

      right1.setInverted(true);
      right2.setInverted(true);
      right3.setInverted(true);

      left2.follow(left1);
      left3.follow(left1);
      right2.follow(right1);
      right3.follow(right1);


      NeutralMode mode = NeutralMode.Brake;

		  left1.setNeutralMode(mode);
      left2.setNeutralMode(mode);
      left3.setNeutralMode(mode);
      right1.setNeutralMode(mode);
      right2.setNeutralMode(mode);
      right3.setNeutralMode(mode);
  }

  public void drive(ControlMode mode, double left, double right){
    left1.set(mode, left);
    right1.set(mode, right);
}

}
