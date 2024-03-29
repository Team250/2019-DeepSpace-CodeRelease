// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc250.FRCDeepSpace2019.subsystems;


import org.usfirst.frc250.FRCDeepSpace2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import badlog.lib.BadLog;
import org.usfirst.frc250.FRCDeepSpace2019.LogUtil;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class HatchFloorPickup extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private VictorSP hFPSMotor;
    private DoubleSolenoid hFPSSolenoid;
    private DigitalInput hatchFloorLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private boolean isDeployed = false;

    public HatchFloorPickup() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hFPSMotor = new VictorSP(2);
        addChild("HFPSMotor",hFPSMotor);
        hFPSMotor.setInverted(true);
        
        hFPSSolenoid = new DoubleSolenoid(0, 2, 5);
        addChild("HFPSSolenoid",hFPSSolenoid);
        
        
        hatchFloorLimit = new DigitalInput(5);
        addChild("HatchFloorLimit",hatchFloorLimit);
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hFPSSolenoid.set(Value.kReverse);

        //Badlog*********************************************************
        BadLog.createTopic("Hatch/Hatch Floor Pickup Motor", "%", () -> hFPSMotor.getSpeed(), "hide");
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean isDeployed(){
        return isDeployed;
    }

    public void deploy(){
        isDeployed = true;
        hFPSSolenoid.set(Value.kForward);
    }

    public void retract(){
        isDeployed = false;
        hFPSSolenoid.set(Value.kReverse);
    }

    public void setHatchBeltSpeed(double speed) {
		hFPSMotor.set(speed);
	}

	public double getIntakeSpeed() {
		return hFPSMotor.get();
	}
}

