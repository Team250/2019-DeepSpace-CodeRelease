/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc250.FRCDeepSpace2019;

import org.usfirst.frc250.FRCDeepSpace2019.Gains;/**
 * Add your docs here.
 */
public class Constants {
	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;

	/**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
     * 
	 * Climbers	     	                                 kP   kI  kD        kF          Iz    PeakOut */
	public final static Gains kGains_Velocit = new Gains( 1.0, 0.002, 10, (1023/(50000/30))*.75,  300,  1.00);

	//Drive
	public final static Gains kDriveGains_Velocit = new Gains( 5.0, 0.000, 0.000, 0.000,  300,  1.00);

}
