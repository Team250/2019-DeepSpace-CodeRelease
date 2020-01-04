// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc250.FRCDeepSpace2019;

import java.util.Optional;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SerialPort;

import org.usfirst.frc250.FRCDeepSpace2019.commands.*;
import org.usfirst.frc250.FRCDeepSpace2019.subsystems.*;
import org.usfirst.frc250.FRCDeepSpace2019.LogUtil;


import badlog.lib.BadLog;
import badlog.lib.DataInferMode;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> autonChooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static FrontClimber frontClimber;
    public static RearClimber rearClimber;
    public static HatchFloorPickup hatchFloorPickup;
    public static MainHatchSystem mainHatchSystem;
    public static Shared shared;
    public static DriveTrain driveTrain;
    public static Vision vision;
    public static CargoBox cargoBox;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private UsbCamera visionCamHatch = null;
    private SerialPort visionPort = null;
    public static final int MJPG_STREAM_PORT = 1180;
    public static Utilities utilities;
    public static Prefs2 prefs2;
    private BadLog logger;
    
    //private CommandSelector autoChooser;

    public void autonChoices() {
        autonChooser.addOption("Auton Left Rocket", new AutonLeftRocket());
        autonChooser.addOption("Auton Left Cargo", new AutonLeftCargo());
        autonChooser.addOption("Auton Right Rocket", new AutonRightRocket());
        autonChooser.addOption("Auton Right Cargo", new AutonRightCargo());
        autonChooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
    }
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    //ahrs = new AHRS(SerialPort.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

    @Override
    public void robotInit() {

        prefs2 = new Prefs2();
        startTimeNS = System.nanoTime();
		lastLog = System.currentTimeMillis();
		String session = LogUtil.genSessionName();
		System.out.println("Info: Starting session " + session);
		logger = BadLog.init("/u/FRC2019BadlogTest/" + session + ".bag");

		BadLog.createValue("Start Time", LogUtil.getTimestamp());
		BadLog.createValue("Event Name", Optional.ofNullable(DriverStation.getInstance().getEventName()).orElse(""));
		BadLog.createValue("Match Type", DriverStation.getInstance().getMatchType().toString());
		BadLog.createValue("Match Number", "" + DriverStation.getInstance().getMatchNumber());
		BadLog.createValue("Alliance", DriverStation.getInstance().getAlliance().toString());
		BadLog.createValue("Location", "" + DriverStation.getInstance().getLocation());
		BadLog.createTopicSubscriber("Time", "s", DataInferMode.DEFAULT, "hide", "delta", "xaxis");
		BadLog.createTopicStr("System/Browned Out", "bool", () -> LogUtil.fromBool(RobotController.isBrownedOut()));
		BadLog.createTopic("System/Battery Voltage", "V", () -> RobotController.getBatteryVoltage());
		BadLog.createTopicStr("System/FPGA Active", "bool", () -> LogUtil.fromBool(RobotController.isSysActive()));
		BadLog.createTopic("Match Time", "s", () -> DriverStation.getInstance().getMatchTime());
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontClimber = new FrontClimber();
        SmartDashboard.putData(frontClimber);
        rearClimber = new RearClimber();
        SmartDashboard.putData(rearClimber);
        hatchFloorPickup = new HatchFloorPickup();
        SmartDashboard.putData(hatchFloorPickup);
        mainHatchSystem = new MainHatchSystem();
        SmartDashboard.putData(mainHatchSystem);
        shared = new Shared();
        SmartDashboard.putData(shared);
        driveTrain = new DriveTrain();
        SmartDashboard.putData(driveTrain);
        vision = new Vision();
        SmartDashboard.putData(vision);
        cargoBox = new CargoBox();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        //jevois();

        autonChoices();
        SmartDashboard.putData("Auto mode", autonChooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonChoices();
        autonomousCommand = autonChooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    private void periodic() {
        double currentTime = ((double) (System.nanoTime() - startTimeNS)) / 1_000_000_000d;

        // Run command based tasks
        Scheduler.getInstance().run();

        BadLog.publish("Time", currentTime);
        logger.updateTopics();
        
        // Only log once every 250ms in disabled, to save disk space
        long currentMS = System.currentTimeMillis();
        if (!DriverStation.getInstance().isDisabled() || (currentMS - lastLog) >= 250) {
            lastLog = currentMS;
            logger.log();
        }
    }

    public void jevois(){
        int retry = 0;
        while(visionPort == null && retry++ < 10){
            try {
                visionCamHatch = CameraServer.getInstance().startAutomaticCapture("HatchCam", 0);
                visionCamHatch.setVideoMode(PixelFormat.kMJPEG, 320, 240, 115200);
                System.out.println("Creating Jevois Serial Port ...");
                visionPort = new SerialPort(115200, SerialPort.Port.kUSB);
                System.out.println("Success!");
            } catch (Exception e) {
                System.out.println("Failed");
                e.printStackTrace();
                Timer.delay(500);
                System.out.println("Retry" + Integer.toString(retry));
            }
        }
    }

}
