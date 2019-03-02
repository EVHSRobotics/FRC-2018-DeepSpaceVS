/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ClawSetPoint;
import frc.robot.commands.ElevatorSetPoint;
import frc.robot.commands.Extend1;
import frc.robot.commands.Extend2;
import frc.robot.commands.ToggleShift;

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
  public static JoystickButton buttonT8 = new JoystickButton(joyTurn, 8);
  public static JoystickButton buttonT7 = new JoystickButton(joyTurn, 7);
  public static JoystickButton buttonT1 = new JoystickButton(joyTurn , 1);
  public static JoystickButton button2 = new JoystickButton(joyThrottle, 2);
  public static JoystickButton buttonT3 = new JoystickButton(joyTurn, 3);
  public static JoystickButton buttonT4 = new JoystickButton(joyTurn, 4);

  public static JoystickButton buttonX1 = new JoystickButton(joyXBox, 1);
  public static JoystickButton buttonX2 = new JoystickButton(joyXBox, 2);
  public static JoystickButton buttonX3 = new JoystickButton(joyXBox, 3);
  public static JoystickButton buttonX4 = new JoystickButton(joyXBox, 4);

  public static JoystickButton buttonTh5 = new JoystickButton(joyThrottle, 5);
  public static JoystickButton buttonTh3 = new JoystickButton(joyThrottle, 3);
  public static JoystickButton buttonTh4 = new JoystickButton(joyThrottle, 4);
  public static JoystickButton buttonTh6 = new JoystickButton(joyThrottle, 6);
  

 
  static {
     buttonT7.whenPressed(new ToggleShift());


     buttonT8.whenPressed(new ToggleShift());

    // button1.whenPressed(new DriveStraight(-Config.slowTarget/6, 70));

    

        buttonT1.whenPressed(new Extend1());
        buttonT4.whenPressed(new Extend2());

       // buttonT3.whenPressed(new ClawSetPoint(Config.clawLow));
       
       
        buttonX4.whenPressed(new ClawSetPoint(Config.claw90));
        buttonX2.whenPressed(new ClawSetPoint(Config.clawZero));
        buttonX1.whenPressed(new ClawSetPoint(Config.clawClimb));

        buttonTh5.whenPressed(new ElevatorSetPoint(Config.elevatorHigh));
        buttonTh3.whenPressed(new ElevatorSetPoint(Config.elevatorMid));
        buttonTh4.whenPressed(new ElevatorSetPoint(Config.elevatorLow));
        buttonTh6.whenPressed(new ElevatorSetPoint(Config.elevatorCargo));


     
   


  }
}
