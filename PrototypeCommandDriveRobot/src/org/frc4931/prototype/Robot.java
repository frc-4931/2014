/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype;

import org.frc4931.prototype.command.DriveForwardAndBackward;
import org.frc4931.prototype.command.RunTests;
import org.frc4931.prototype.subsystem.DriveTrain;
import org.frc4931.prototype.subsystem.LogitechController;
import org.frc4931.prototype.subsystem.TalonDriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A robot with an arcade style joystick and two motors all controlled through the command framework.
 * <p>
 * The Virtual Machine is configured to automatically run this class via the "resources/META-INF/MANIFEST.MF" file, which must
 * contain an entry with the name of this class and the qualified path of this class. For example:
 * </p>
 * <code>
 * MIDlet-Name: Robot
 * ...
 * MIDlet-1: Robot, , org.frc4931.prototype.Robot
 * </code>
 */
public class Robot extends IterativeRobot {

    /**
     * The drive joystick's input port and button assignments.
     */
    public static final class Controller {
        /** The port for the arcade-style drive joystick */
        public static final int PORT = 1;
    }

    /**
     * The drive motors' output ports and related constants.
     */
    public static final class DriveMotors {
        /** The port for the left motor */
        public static final int LEFT_PORT = 2;
        /** The port for the right motor */
        public static final int RIGHT_PORT = 1;

        /** Flag specifying whether the left drive motor is reversed */
        public static final boolean LEFT_REVERSED = true;
        /** Flag specifying whether the right drive motor is reversed */
        public static final boolean RIGHT_REVERSED = true;

        /** The position of the left motor in the DriveTrain class */
        public static final MotorType LEFT_POSITION = MotorType.kRearRight;
        /** The position of the right motor in the DriveTrain class */
        public static final MotorType RIGHT_POSITION = MotorType.kRearLeft;

        /** Initial speed Limiting (uses a number from 0.0 to 1.0 to set a maximum speed) */
        public static final double INITIAL_MAX_DRIVE_SPEED = 0.8; // 1.0;
        /** The percentage that the max drive speed is changed */
        public static final double DELTA_MAX_DRIVE_SPEED = 0.05;
    }

    protected static DriveTrain createDriveTrain() {
        // return new JaguarDriveTrain();
        return new TalonDriveTrain();
    }

    /**
     * The single (static) operator interface.
     */
    public static OperatorInterface operatorInterface;

    // -----------------------------------------------------
    // Define each controlled subsystem ...
    // -----------------------------------------------------
    public static DriveTrain driveTrain = null;

    /**
     * Flag that states whether debug/verbose messages should be written
     */
    private static boolean verbose;

    /**
     * The single {@link Command} instance that should be run in {@link #autonomousPeriodic() autonomous mode}.
     */
    private Command autonomousCommand;

    /**
     * The single {@link Command} instance that should be run in {@link #testPeriodic() test mode}.
     */
    private Command testCommand;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {
        driveTrain = createDriveTrain();
        // Initialize the operator interface, which binds our commands to the operator interface ...
        operatorInterface = new OperatorInterface();

        // Register each subsystem with the SmartDashboard so it can show what command(s) the subsystems are running
        SmartDashboard.putData(driveTrain);

        // Register the command scheduler ...
        SmartDashboard.putData(Scheduler.getInstance());
    }

    /**
     * This function is called once when autonomous operation begins.
     */
    public void autonomousInit() {
        print("Entering autonomous mode");
        // We could decide which autonomous command we should use, but use one for now ...
        autonomousCommand = new DriveForwardAndBackward();
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        // Schedule the autonomous command that should run until completion ...
        printDebug("Running autonomous operation " + autonomousCommand.getName() + "...");
        autonomousCommand.start();
    }

    /**
     * This function is called once when operator control begins.
     */
    public void teleopInit() {
        updateStatus();
        print("Entering teleop mode");
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }

    /**
     * This function is called once when test mode begins.
     */
    public void testInit() {
        print("Entering test mode");
        testCommand = new RunTests();
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {
        printDebug("Running tests.");
        testCommand.start();
    }

    /**
     * This function is called once the robot is disabled.
     */
    public void disabledInit() {
        print("Entering disabled mode!");
    }

    /**
     * This function is called periodically while disabled.
     */
    public void disabledPeriodic() {
    }

    // -----------------------------------------------------
    // Utility methods ...
    // -----------------------------------------------------

    public static boolean isVerboseOutputEnabled() {
        return verbose;
    }

    public static void enableVerboseOutput( boolean enable ) {
        verbose = enable;
    }

    public static void toggleVerboseOutput() {
        verbose = !verbose;
    }

    public static void print( String message ) {
        System.out.println(message);
    }

    public static void printDebug( String message ) {
        if (verbose) System.out.println(message);
    }

    public static void updateStatus() {
        // Add our data to the "SmartDashboard".
        LogitechController controller = operatorInterface.getController();

        // First, report the joystick values ...
        SmartDashboard.putNumber("Drive Joystick X", controller.getX());
        SmartDashboard.putNumber("Drive Joystick Y", controller.getY());
        SmartDashboard.putNumber("Drive Joystick Twist", controller.getTwist());

        // Then, report the drive train status (e.g., motor speeds) ...
        driveTrain.updateStatus();
    }
}
