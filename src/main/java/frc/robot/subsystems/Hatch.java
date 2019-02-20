/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.HatchDrive;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DoubleSolenoid extend1, extend2;
  boolean isExtend1 = false;
  boolean isExtend2 = false;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HatchDrive());
  }

  public Hatch(){
    extend1 = new DoubleSolenoid(RobotMap.hatchExtend1, RobotMap.hatchExtend2);
    extend2 = new DoubleSolenoid(RobotMap.hatchExtend1_1, RobotMap.hatchExtend2_2);
  }

  public void extend1(){
    extend1.set(Value.kForward);
  }

  public void extend2(){
    extend2.set(Value.kForward);
  }

  public void toggleHatch1(){
    System.out.println("toggle hatch extend 1");
    SmartDashboard.putBoolean("hatch 2 extended", isExtend1);
    if(isExtend1) extend1.set(Value.kForward);
    else if(!isExtend1) extend1.set(Value.kReverse);
    isExtend1 = !isExtend1;
  }
  public void toggleHatch2(){
    System.out.println("toggle hatch extend 2");
    SmartDashboard.putBoolean("hatch 2 extended", isExtend2);
    if(isExtend2) extend2.set(Value.kForward);
    else if(!isExtend2) extend2.set(Value.kReverse);
    isExtend2 = !isExtend2;
  }
}
