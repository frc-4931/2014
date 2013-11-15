/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype;

import org.frc4931.prototype.subsystem.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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

    // ----------------------------------------------
    // Define the physical robot design constants ...
    // ----------------------------------------------

    public static final class DriveJoystick {
        /** The port for the arcade-style drive joystick */
        public static final int PORT = 1;

        /** The button number on the drive joystick that increases the speed. */
        public static final int INCREASE_SPEED_BUTTON = 3;
        /** The button number on the drive joystick that decreases the speed. */
        public static final int DECREASE_SPEED_BUTTON = 4;
    }

    public static final class DriveMotors {
        /** The port for the left motor */
        public static final int LEFT_PORT = 1;
        /** The port for the right motor */
        public static final int RIGHT_PORT = 2;

        /** Flag specifying whether the left drive motor is reversed */
        public static final boolean LEFT_REVERSED = false;
        /** Flag specifying whether the right drive motor is reversed */
        public static final boolean RIGHT_REVERSED = false;

        /** Initial speed Limiting (uses a number from 0.0 to 1.0 to set a maximum speed) */
        public static final double INITIAL_MAX_DRIVE_SPEED = 1.0;
        /** The percentage that the max drive speed is changed */
        public static final double DELTA_MAX_DRIVE_SPEED = 0.05;
    }

    // -----------------------------------------------------
    // Define the single operator interface ...
    // -----------------------------------------------------
    public static OperatorInterface operatorInterface;

    // -----------------------------------------------------
    // Define each controlled subsystem ...
    // -----------------------------------------------------
    public static final DriveTrain driveTrain = new DriveTrain();

    // --------------------------------------------------------------
    // Define a single instance of the autonomous command (group) ...
    // --------------------------------------------------------------
    // SomeCommandGroup autonomousCommand = new SomeCommandGroup();

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {
        // Initialize the operator interface, which binds our commands to the operator interface ...
        operatorInterface = new OperatorInterface();

        // Register each subsystem with the SmartDashboard so it can show what command(s) the subsystems are running
        SmartDashboard.putData(driveTrain);

        // Register the command scheduler ...
        SmartDashboard.putData(Scheduler.getInstance());
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        // Schedule the autonomous command that should run until completion ...
        // autonomousCommand.start();
    }

    /**
     * This function is called once when operator control begins.
     */
    public void teleopInit() {
        updateStatus();
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }

    public void updateStatus() {
        // Add our data to the "SmartDashboard".
        Joystick driveJoystick = operatorInterface.getDriveJoystick();

        // First, report the joystick values ...
        SmartDashboard.putNumber("Drive Joystick X", driveJoystick.getX());
        SmartDashboard.putNumber("Drive Joystick Y", driveJoystick.getY());
        SmartDashboard.putNumber("Drive Joystick Twist", driveJoystick.getTwist());

        // Then, report the drive train status (e.g., motor speeds) ...
        driveTrain.updateStatus();
    }
}
