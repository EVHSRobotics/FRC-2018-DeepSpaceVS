/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivePaths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ClawSetPoint;
import frc.robot.commands.DriveStraight;
import frc.robot.Config;

import frc.robot.commands.ElevatorSetPoint;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.In;

public class DriveCargoBayLeft extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DriveCargoBayLeft() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    System.out.println("running auto");
    addSequential(new ClawSetPoint(Config.claw90));
    addSequential(new DriveStraight(-.4, 70000));
    addSequential(new GyroTurn(110, true, .9));
    addSequential(new DriveStraight(.5,  13648));
  
    addSequential(new ElevatorSetPoint(Config.elevatorLow)); //TODO get encoder value for this pos
  //  addSequential(new In(.8)); //use banner to determine when to stop intake
  }
}
