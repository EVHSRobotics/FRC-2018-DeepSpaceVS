/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsystemNames;
import edu.wpi.first.wpilibj.I2C;

/**
 * Add your docs here.
 */
public class SensorBoard {
    private AHRS navX;
    private Ultrasonic ultra;
    private DriveTrain drive;

    private double ultraRange = -1;
    private boolean ultraRunning = true;

    public SensorBoard(){
        ultra = new Ultrasonic(1,0);
        ultra.setEnabled(true);
        ultra.ping();

        if(ultra.isRangeValid()) ultra.getRangeInches();

        navX = new AHRS(I2C.Port.kMXP);

        drive = (DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN);

    }

    public AHRS getNavX() {
		return navX;
	}

    public Ultrasonic getUltra(){
        return ultra;
    }
	public double getUltraDistance() {
		return ultraRange;
	}

}
