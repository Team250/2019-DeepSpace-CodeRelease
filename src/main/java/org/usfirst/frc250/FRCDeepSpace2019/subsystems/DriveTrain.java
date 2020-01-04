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

import org.usfirst.frc250.FRCDeepSpace2019.LogUtil;
import org.usfirst.frc250.FRCDeepSpace2019.Prefs2;
import org.usfirst.frc250.FRCDeepSpace2019.Robot;
import org.usfirst.frc250.FRCDeepSpace2019.commands.*;
import org.usfirst.frc250.FRCDeepSpace2019.Constants;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import badlog.lib.BadLog;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	private boolean turnDisabled = false;
	private boolean halfSpeed = false;
	private boolean isReversed = false;
	private boolean velocityMode = false;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX driveRightTalon;
    private WPI_TalonSRX driveLeftTalon;
    private WPI_VictorSPX driveRightVictor;
    private WPI_VictorSPX driveLeftVictor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public DriveTrain() {

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveRightTalon = new WPI_TalonSRX(1);
        
        
        
        driveLeftTalon = new WPI_TalonSRX(2);
        
        
        
        driveRightVictor = new WPI_VictorSPX(3);
        
        
        
        driveLeftVictor = new WPI_VictorSPX(4);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		// Reset motor controller to factory default so we're starting from a known base
		driveRightTalon.configFactoryDefault();
		driveRightVictor.configFactoryDefault();
		driveLeftTalon.configFactoryDefault();
		driveLeftVictor.configFactoryDefault();

		// enable brake mode by default
		driveRightTalon.setNeutralMode(NeutralMode.Brake);
		driveRightVictor.setNeutralMode(NeutralMode.Brake);
		driveLeftTalon.setNeutralMode(NeutralMode.Brake);
		driveLeftVictor.setNeutralMode(NeutralMode.Brake);

		// set followers
		driveRightVictor.follow(driveRightTalon);
		driveLeftVictor.follow(driveLeftTalon);

		// set inversions
		driveRightTalon.setInverted(true);
		driveRightVictor.setInverted(InvertType.FollowMaster);
		driveLeftTalon.setInverted(false);
		driveLeftVictor.setInverted(InvertType.FollowMaster);

		driveRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		driveLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		driveRightTalon.setSensorPhase(true);
		driveLeftTalon.setSensorPhase(true);

		driveRightTalon.configNominalOutputForward(0, Constants.kTimeoutMs);
		driveRightTalon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		driveRightTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
		driveRightTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		driveLeftTalon.configNominalOutputForward(0, Constants.kTimeoutMs);
		driveLeftTalon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		driveLeftTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
		driveLeftTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		driveRightTalon.config_kF(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kF, Constants.kTimeoutMs); //TODO: Verify
		driveRightTalon.config_kP(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kP, Constants.kTimeoutMs);
		driveRightTalon.config_kI(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kI, Constants.kTimeoutMs);
		driveRightTalon.config_kD(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kD, Constants.kTimeoutMs);

		driveLeftTalon.config_kF(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kF, Constants.kTimeoutMs); //TODO: Verify
		driveLeftTalon.config_kP(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kP, Constants.kTimeoutMs);
		driveLeftTalon.config_kI(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kI, Constants.kTimeoutMs);
		driveLeftTalon.config_kD(Constants.kPIDLoopIdx, Constants.kDriveGains_Velocit.kD, Constants.kTimeoutMs);

		driveRightTalon.configMotionCruiseVelocity(Robot.prefs2.getInt("Drive Max Velocity"), Constants.kTimeoutMs);
		driveRightTalon.configMotionAcceleration(Robot.prefs2.getInt("Drive Max Acceleration"), Constants.kTimeoutMs);

		driveLeftTalon.configMotionCruiseVelocity(Robot.prefs2.getInt("Drive Max Velocity"), Constants.kTimeoutMs);
		driveLeftTalon.configMotionAcceleration(Robot.prefs2.getInt("Drive Max Acceleration"), Constants.kTimeoutMs);

		zeroDistance();

		BadLog.createTopic("Drivetrain/Talon Right Current", "A", () -> driveRightTalon.getOutputCurrent(), "hide",
				"join:Drivetrain/Output Currents");
		BadLog.createTopic("Drivetrain/Talon Left Current", "A", () -> driveLeftTalon.getOutputCurrent(), "hide",
				"join:Drivetrain/Output Currents");
		BadLog.createTopic("Drivetrain/Talon Right Speed", "Feet/Second",
				() -> Math.abs(driveRightTalon.getSelectedSensorVelocity() / 200.0 * 10 / 12), "hide",
				"join:Drivetrain/Speed");
		BadLog.createTopic("Drivetrain/Talon Left Speed", "Feet/Second",
				() -> Math.abs(driveLeftTalon.getSelectedSensorVelocity() / 200.0 * 10 / 12), "hide",
				"join:Drivetrain/Speed");
		BadLog.createTopic("Drivetrain/Talon Right Position", "Inches",
				() -> driveRightTalon.getSelectedSensorPosition() / 200.0, "hide", "join:Drivetrain/Position");
		BadLog.createTopic("Drivetrain/Talon Left Position", "Inches",
				() -> driveLeftTalon.getSelectedSensorPosition() / 200.0, "hide", "join:Drivetrain/Position");
		BadLog.createTopicStr("Drivetrain/Drive Forward", "Bool", () -> LogUtil.fromBool(this.isReversed()), "hide");
	
	System.out.println("Drivetrain Subsystem Initialized");
	}

	@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop
		SmartDashboard.putNumber("Drive Right Encoder Values", this.getCurrentPositionRight());
		SmartDashboard.putNumber("Drive Left Encoder Values", this.getCurrentPositionLeft());
		SmartDashboard.putBoolean("Drive Direction", this.isReversed()); 
		SmartDashboard.putBoolean("Drive Half Speed", this.isHalfPower());
		SmartDashboard.putBoolean("Turn Disabled", this.isTurnDisabled());
	}

	public int getCurrentPositionLeft() {
		return driveLeftTalon.getSelectedSensorPosition(0);
	}

	public int getCurrentPositionRight() {
		return driveRightTalon.getSelectedSensorPosition(0);
	}

	public double getTargetPositionLeft() {
		return driveLeftTalon.getClosedLoopTarget(0);
	}

	public double getTargetPositionRight() {
		return driveRightTalon.getClosedLoopTarget(0);
	}

	public void drive(double L, double R) {
		driveRightTalon.set(ControlMode.PercentOutput, R);
		driveLeftTalon.set(ControlMode.PercentOutput, L);
	}

	private int inchesToEncoderPulses(double inches) {
		return (int) (inches * Robot.prefs2.getDouble("Encoder Ticks Per Inch Driven"));
	}

	private void _driveDistanceRight(int pulses) {
		driveRightTalon.set(ControlMode.MotionMagic, pulses);
	}

	private void _driveDistanceLeft(int pulses) {
		driveLeftTalon.set(ControlMode.MotionMagic, pulses);
	}

	public void driveDistance(double distanceLeft, double distanceRight) {
		_driveDistanceLeft((int) (getCurrentPositionLeft()
				+ distanceLeft * Robot.prefs2.getDouble("Encoder Ticks Per Inch Driven")));
		_driveDistanceRight((int) (getCurrentPositionRight()
				+ distanceRight * Robot.prefs2.getDouble("Encoder Ticks Per Inch Driven")));
	}

	// disableTurning mode stops accepting input from turning axis
	// Y = Front-to-Back, X=Side-to-Side,  Z=Rotate
	public void disableTurning(boolean disabled) {
		turnDisabled = disabled;
	}

	// returns if disableTurning mode is enabled or not
	public boolean isTurnDisabled() {
		return turnDisabled;
	}

	public void setHalfPower(boolean b) {
		halfSpeed = b;
	}

	public boolean isHalfPower() {
		return halfSpeed;
	}

	public void setReverse(boolean r) {
		isReversed = r;
	}

	public boolean isReversed() {
		return isReversed;
	}

	// zeros distance value on encoders
	public void zeroDistance() {
		driveLeftTalon.setSelectedSensorPosition(0, 0, Robot.prefs2.getInt("CAN Sensor Timeout MS"));
		driveRightTalon.setSelectedSensorPosition(0, 0, Robot.prefs2.getInt("CAN Sensor Timeout MS"));
	}

	// returns current electrical current draw
	public double[] getCurrentCurrentDraw() {
		double[] currents = new double[4];
		currents[0] = driveLeftTalon.getOutputCurrent();
		currents[1] = driveRightTalon.getOutputCurrent();
		return currents;
	}

	// returns current speed
	public double[] getCurrentSpeed() {
		double[] speeds = new double[2];
		speeds[0] = driveLeftTalon.getSelectedSensorVelocity(0);
		speeds[1] = driveRightTalon.getSelectedSensorVelocity(0);
		return speeds;
	}

	public boolean isAtTargets() {
		double leftDelta = Math.abs(Math.abs(getTargetPositionLeft()) - Math.abs(getCurrentPositionLeft()));
		double rightDelta = Math.abs(Math.abs(getTargetPositionRight()) - Math.abs(getCurrentPositionRight()));
		int threshold = Robot.prefs2.getInt("Drive MM Target Threshold");

		if (leftDelta <= threshold && rightDelta <= threshold) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isVelocityMode(){ 
		return velocityMode;
	}

	public void setVelocityMode(boolean b){
		if(b && !isVelocityMode()){ 
			velocityMode = true;	// Entering Velocity mode
			driveLeftTalon.set(ControlMode.Velocity, driveLeftTalon.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
			driveRightTalon.set(ControlMode.Velocity, driveRightTalon.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
		} else if(!b && isVelocityMode()){
			velocityMode = false; // Leaving velocity Mode
			driveLeftTalon.set(ControlMode.PercentOutput, driveLeftTalon.getMotorOutputPercent());
			driveRightTalon.set(ControlMode.PercentOutput, driveRightTalon.getMotorOutputPercent());
		}
	}



	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

}
