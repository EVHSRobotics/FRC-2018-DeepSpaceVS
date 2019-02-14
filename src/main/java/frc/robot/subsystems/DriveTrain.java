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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PIDTemplate;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX left1, left2, left3, right1, right2, right3;
  DoubleSolenoid shifter;
  Value fast = Value.kForward;
  Value slow = Value.kReverse;
  boolean isFast = false;
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

      //PIDTemplate.configTalon(left1, true);
      //PIDTemplate.configTalon(right1, true);
      shifter = new DoubleSolenoid(RobotMap.shifterUp, RobotMap.shifterDown);
      applyShift("slow");
    }
/**
   * sets speed of motors
   * 
   * @param mode  - set control mode of motor
   * @param left  - set left speed
   * @param right - set right speed
   */
  public void drive(ControlMode mode, double left, double right){
    left1.set(mode, left);
    right1.set(mode, right);

    SmartDashboard.putNumber("encoder left", left1.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("encoder right", right1.getSelectedSensorPosition(0));
}
/** toggle shift based on OI button */
  public void toggleShift(){
    if(isFast) shifter.set(slow);
    else shifter.set(fast);
    isFast = !isFast;
  }
  
  /**
   * sets gear value to desired gear state
   * 
   * @param gear - desired gear state
   */
  public void applyShift(String gear){
    if(gear.equals("fast")){
     
      shifter.set(fast);
      System.out.println("shifted to fast");
      double P_Drive_HIGH = 0;// 0.35;
      double I_Drive_HIGH = 0; // 1.0E-4;
      double D_Drive_HIGH = 0; // 0.11;
      double F_Drive_HIGH = 0;
      double targetSpeed_Drive_FAST = 0;

      // PIDTemplate.updatePID(left1, P_Drive_HIGH, I_Drive_HIGH, D_Drive_HIGH, F_Drive_HIGH, targetSpeed_Drive_FAST);
      // PIDTemplate.updatePID(right1, P_Drive_HIGH, I_Drive_HIGH, D_Drive_HIGH, F_Drive_HIGH, targetSpeed_Drive_FAST);
      }else if(gear.equals("slow")){
      
      shifter.set(slow);
      System.out.println("shifted to slow");

      double P_Drive_LOW = 0;
      double I_Drive_LOW = 0;
      double D_Drive_LOW = 0;
      double F_Drive_LOW = .0996;
      double targetSpeed_Drive_LOW = 0;

     // PIDTemplate.updatePID(left1, P_Drive_LOW, I_Drive_LOW, D_Drive_LOW, F_Drive_LOW,targetSpeed_Drive_LOW) ;
     // PIDTemplate.updatePID(right1, P_Drive_LOW, I_Drive_LOW, D_Drive_LOW, F_Drive_LOW,targetSpeed_Drive_LOW) ;
    
    } 
  }

   /** get right value encoders */
   public int getRtEncoders() {
    return right1.getSelectedSensorPosition(0);
  }

  /** get left value encoders */
  public int getLtEncoders() {
    return left1.getSelectedSensorPosition(0);
  }
  
}
