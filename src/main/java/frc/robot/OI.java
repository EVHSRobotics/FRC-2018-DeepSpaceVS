/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.AutoIntake;
import frc.robot.commands.AutoOuttake;
import frc.robot.commands.CameraToggle;
import frc.robot.commands.ClawSetPoint;
import frc.robot.commands.ElevatorSetPoint;
import frc.robot.commands.Extend1;
import frc.robot.commands.Extend2;
import frc.robot.commands.ShiftGearLow;
import frc.robot.drivePaths.DriveCargoBayRight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public static Joystick joyThrottle = new Joystick(0);
  public static Joystick joyTurn = new Joystick(1);
  public static Joystick joyXBox = new Joystick(2);
  public static Joystick joySet = new Joystick(3);

  public static JoystickButton buttonTh5 = new JoystickButton(joySet, 2);
  public static JoystickButton buttonTh3 = new JoystickButton(joySet, 3);
  public static JoystickButton buttonTh4 = new JoystickButton(joySet, 4);

  // xbox controller buttons
  public static JoystickButton ABox = new JoystickButton(joyXBox, 1);
  public static JoystickButton BBox = new JoystickButton(joyXBox, 2);
  public static JoystickButton XBox = new JoystickButton(joyXBox, 3);
  public static JoystickButton YBox = new JoystickButton(joyXBox, 4);
  public static JoystickButton StartBut = new JoystickButton(joyXBox, 8);

  public static JoystickButton bumperLt = new JoystickButton(joyXBox, 5);
  public static JoystickButton bumperRt = new JoystickButton(joyXBox, 6);

  public static POVButton dUP = new POVButton(joyXBox, 0, 0);
  public static POVButton dRIGHT = new POVButton(joyXBox, 90, 2);
  public static POVButton dDOWN = new POVButton(joyXBox, 180, 4);
  public static POVButton dLEFT = new POVButton(joyXBox, 270, 6);

  // throttle
  public static JoystickButton topLeft = new JoystickButton(joyThrottle, 5);
  public static JoystickButton bottomLeft = new JoystickButton(joyThrottle, 3);
  public static JoystickButton topRight = new JoystickButton(joyThrottle, 6);
  public static JoystickButton bottomRight = new JoystickButton(joyThrottle, 4);

  public static JoystickButton trigger = new JoystickButton(joyThrottle, 1);
  public static JoystickButton thumbButton = new JoystickButton(joyThrottle, 2);

  // wheel buttons
  public static JoystickButton AWheel = new JoystickButton(joyTurn, 3);
  public static JoystickButton BWheel = new JoystickButton(joyTurn, 4);
  public static JoystickButton XWheel = new JoystickButton(joyTurn, 1);
  public static JoystickButton YWheel = new JoystickButton(joyTurn, 2);

  public static POVButton dLTWheel = new POVButton(joyTurn, 270, 6);
  public static POVButton dUPWheel = new POVButton(joyTurn, 0, 0);
  public static POVButton dRTWheel = new POVButton(joyTurn, 90, 2);
  public static POVButton dDOWNWheel = new POVButton(joyTurn, 180, 4);

  public static JoystickButton bacWheelButtonRT = new JoystickButton(joyTurn, 8);
  public static JoystickButton bacWheelButtonLT = new JoystickButton(joyTurn, 7);

  static {
   

    // xBox
    //bumperLt.whenPressed(new Extend1()); //prongs
    bumperRt.whenPressed(new Extend2());
    
    // if(StartBut.get()){
      YBox.whenPressed(new ElevatorSetPoint(Config.elevatorHighHatch));
      BBox.whenPressed(new ElevatorSetPoint(Config.elevatorMidHatch));
      ABox.whenPressed(new ElevatorSetPoint(Config.elevatorLowHatch));
     
    dUP.whenPressed(new ClawSetPoint(Config.claw90));
    dDOWN.whenPressed(new ClawSetPoint(Config.clawZero));
    dLEFT.whenPressed(new AutoIntake());


    //throttle
   // trigger.whileHeld(new ShiftGearLow());
    bottomLeft.whenPressed(new CameraToggle());

    //wheel
    XWheel.whenPressed(new DriveCargoBayRight());
    // buttonT6.whenPressed(new ElevatorSetPoint(Config.elevatorCargo));
    bacWheelButtonLT.whenPressed(new ShiftGearLow());

  }
}
