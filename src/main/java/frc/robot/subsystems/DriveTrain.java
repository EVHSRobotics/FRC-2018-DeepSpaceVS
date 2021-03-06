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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
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
  VictorSPX v_left2, v_left3, v_right2, v_right3;
  DoubleSolenoid shifter;
  Value fast = Value.kForward;
  Value slow = Value.kReverse;
  boolean isFast = false;
  private String gearState;

  boolean jayMode = false;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new JoystickDrive());

  }

  public DriveTrain(){
      

   // initTalonDrive();
    initVictorDrive();
      setNeutralMode(NeutralMode.Brake, false);

		  

      PIDTemplate.configTalon(left1, true);
      PIDTemplate.configTalon(right1, true);

      left1.setSelectedSensorPosition(0);
      right1.setSelectedSensorPosition(0);

      shifter = new DoubleSolenoid(RobotMap.shifterUp, RobotMap.shifterDown);
      applyShift("high");
    }

    /**sets up the motors for a driveTrain with all talons */
    public void initTalonDrive(){
      left1 = new TalonSRX(RobotMap.leftT1);
      left2 = new TalonSRX(RobotMap.leftT2);
      left3 = new TalonSRX(RobotMap.leftT3);
   

      right1 = new TalonSRX(RobotMap.rightT1);
      right2 = new TalonSRX(RobotMap.rightT2);
      right3 = new TalonSRX(RobotMap.rightT3);

      right1.setInverted(false);
      right2.setInverted(false);
      right3.setInverted(false);

      

      left2.follow(left1);
      left3.follow(left1);
      right2.follow(right1);
      right3.follow(right1);

    }

    /** sets up motors for a drive train with talons and victors */
    public void initVictorDrive(){
      left1 = new TalonSRX(RobotMap.leftT1);
      v_left2 = new VictorSPX(RobotMap.leftT2);
      v_left3 = new VictorSPX(RobotMap.leftT3);

      right1 = new TalonSRX(RobotMap.rightT1);
      v_right2 = new VictorSPX(RobotMap.rightT2);
      v_right3 = new VictorSPX(RobotMap.rightT3);

      right1.setInverted(true);
      v_right2.setInverted(true);
      v_right3.setInverted(true);

      v_left2.follow(left1);
      v_left3.follow(left1);
      v_right2.follow(right1);
      v_right3.follow(right1);

    }
/**
   * sets speed of motors
   * 
   * @param mode  - set control mode of motor
   * @param left  - set left speed
   * @param right - set right speed
   */
  public void drive(ControlMode mode, double left, double right){
   
    if(mode.equals(ControlMode.Velocity) || mode.equals(ControlMode.MotionMagic)){
      left *= getDriveConstant();
      right *= getDriveConstant();
    }

    left1.set(mode, left);
    right1.set(mode, right);

    SmartDashboard.putNumber("encoder left", left1.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("encoder right", right1.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("turn Multiplier", getTurnMultiplier());
}

/** stops all motors by setting power to 0 */
public void stop(){
  drive(ControlMode.PercentOutput, 0, 0);
}
/** toggle shift based on OI button */
  public void toggleShift(){
    if(isFast) applyShift("slow");
    else if(!isFast) applyShift("fast");
    isFast = !isFast;
  }
  
  /**
   * sets gear value to desired gear state
   * 
   * @param gear - desired gear state
   */
  public void applyShift(String gear){
    if(gear.equals("fast")){
      gearState = "fast";
      shifter.set(fast);
      
      System.out.println("shifted to fast");
      double P_Drive_HIGH = .35;// 0.35;
      double I_Drive_HIGH = 1.0E-4;
      double D_Drive_HIGH = 0.11; // 0.11;
      double F_Drive_HIGH = 0.1042;
      double targetSpeed_Drive_FAST = 8350;

       PIDTemplate.updatePID(left1, P_Drive_HIGH, I_Drive_HIGH, D_Drive_HIGH, F_Drive_HIGH, targetSpeed_Drive_FAST);
       PIDTemplate.updatePID(right1, P_Drive_HIGH, I_Drive_HIGH, D_Drive_HIGH, F_Drive_HIGH, targetSpeed_Drive_FAST);
      }else if(gear.equals("slow")){
      gearState = "slow";
      shifter.set(slow);
      System.out.println("shifted to slow");

      double P_Drive_LOW = .45;
      double I_Drive_LOW = 1.8E-4;
      double D_Drive_LOW = 2;
      double F_Drive_LOW = 0.1042;
      double targetSpeed_Drive_LOW = Config.slowTarget;

      PIDTemplate.updatePID(left1, P_Drive_LOW, I_Drive_LOW, D_Drive_LOW, F_Drive_LOW,targetSpeed_Drive_LOW) ;
      PIDTemplate.updatePID(right1, P_Drive_LOW, I_Drive_LOW, D_Drive_LOW, F_Drive_LOW,targetSpeed_Drive_LOW) ;
    
    } 
  }

  /** set mode of talons*/
  public void setNeutralMode(NeutralMode mode, boolean allTalons){
    if(allTalons){
      left1.setNeutralMode(mode);
      left2.setNeutralMode(mode);
      left3.setNeutralMode(mode);
      right1.setNeutralMode(mode);
      right2.setNeutralMode(mode);
      right3.setNeutralMode(mode);
    }else{
      left1.setNeutralMode(mode);
      v_left2.setNeutralMode(mode);
      v_left3.setNeutralMode(mode);
      right1.setNeutralMode(mode);
      v_right2.setNeutralMode(mode);
      v_right3.setNeutralMode(mode);
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

  public int getAvgEncoders(){
   return (getRtEncoders() + getLtEncoders() ) / 2;
  }

  public void resetEncoders(){
    right1.setSelectedSensorPosition(0);
    left1.setSelectedSensorPosition(0);
  }
  
  public void setTurnMultiplier(double multiplier){
    Config.turnMultiplier = multiplier;
  }
  
  public double getTurnMultiplier(){
    return Config.turnMultiplier;
  }

  public double getDriveConstant(){
    if(gearState.equals("slow")) return Config.slowTarget;
    else if(gearState.equals("fast")) return Config.fastTarget;
    return 0;
  }
  
  public String getGearState(){
    return gearState;
  }
  
  public void setJayMode(boolean jayMode){
    this.jayMode = jayMode;
  }

  public boolean getJayMode(){
    return jayMode;
  }
}
