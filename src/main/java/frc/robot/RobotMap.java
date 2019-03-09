/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static int leftT1 = 8;
  public static int leftT2 = 2;
  public static int leftT3 = 7;
  public static int rightT1 = 6;
  public static int rightT2 = 4;
  public static int rightT3 = 3;




  public static int elevatorMaster = 11;
  public static int elevatorSlave = 12;

  public static int clawMaster = 10; //inverted
  public static int clawSlave = 9;

  public static int intakeMaster = 1;

  public static int shifterUp = 1;
  public static int shifterDown = 2;

  public static int hatchExtend1 = 3;
  public static int hatchExtend2 = 4;

  public static int hatchExtend1_1 = 5;
  public static int hatchExtend2_2 = 6;

  public static int spikeRelay = 0;

}
