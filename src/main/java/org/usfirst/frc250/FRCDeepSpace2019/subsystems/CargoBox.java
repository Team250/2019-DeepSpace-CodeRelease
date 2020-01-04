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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class CargoBox extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DoubleSolenoid cargoLockSolenoid;
    private DoubleSolenoid cargoPuncherSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private boolean isCargoLock = true;

    public CargoBox() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        cargoLockSolenoid = new DoubleSolenoid(0, 7, 0);
        addChild("CargoLockSolenoid",cargoLockSolenoid);
        
        
        cargoPuncherSolenoid = new DoubleSolenoid(1, 0, 7);
        addChild("CargoPuncherSolenoid",cargoPuncherSolenoid);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        cargoLockSolenoid.set(Value.kReverse);
        cargoPuncherSolenoid.set(Value.kForward);
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
        SmartDashboard.putBoolean("Cargo Lock", this.isCargoLocked());

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public boolean isCargoLocked(){
        return isCargoLock;
    }

    public void lockCargo(){
        isCargoLock = true;
        cargoLockSolenoid.set(Value.kReverse);
    }

    public void unlockCargo(){
        isCargoLock = false;
        cargoLockSolenoid.set(Value.kForward);
    }

    public void cargoPunchOut(){
        cargoPuncherSolenoid.set(Value.kReverse);
    }

    public void cargoPunchIn(){
        cargoPuncherSolenoid.set(Value.kForward);
    }

}

