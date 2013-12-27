/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2013. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.frc4931.prototype;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;

/**
 * A simple iterative robot that uses a two joysticks and two motors. The {@link IterativeRobot} superclass will automatically
 * invoke these methods based upon the current mode of the robot:
 * <ul>
 * <li>{@link #robotInit()} - called when the robot is first started up, useful for any initialization code</li>
 * <li>{@link #autonomousPeriodic()} - called periodically during autonomous mode</li>
 * <li>{@link #teleopPeriodic()} - called periodically during tele-operation mode</li>
 * </ul>
 * <p>
 * The Virtual Machine is configured to automatically run this class via the "resources/META-INF/MANIFEST.MF" file, which must
 * contain an entry with the name of this class and the qualified path of this class. For example:
 * </p>
 * <code>
 * MIDlet-Name: TankDriveRobot
 * ...
 * MIDlet-1: TankDriveRobot, , org.frc4931.prototype.TankDriveRobot
 * </code>
 */
public class TankDriveRobot extends IterativeRobot {

    /**
     * The drive joystick's input port and button assignments.
     * <p>
     * The Logitech controller has the following axis assignments in <em>D MODE</em>:
     * <ul>
     * <li>Axis 1 - Left Joystick horizontal (X) axis</li>
     * <li>Axis 2 - Left Joystick vertical (Y) axis</li>
     * <li>Axis 3 - Right Joystick horizontal (X) axis</li>
     * <li>Axis 4 - Right Joystick vertical (Y) axis</li>
     * <li>Axis 5 - D-Pad Horizontal</li>
     * <li>Axis 6 - D-Pad Vertical</li>
     * <ul>
     * In <em>X MODE</em>:
     * <ul>
     * <li>Axis 1 - Left Joystick horizontal (X) axis</li>
     * <li>Axis 2 - Left Joystick vertical (Y) axis</li>
     * <li>Axis 3 - Left Trigger</li>
     * <li>Axis 4 - Right Joystick horizontal (X) axis</li>
     * <li>Axis 5 - Right Joystick vertical (Y) axis</li>
     * <li>Axis 6 - Right Trigger</li>
     * <ul>
     */
    public static final class Inputs {
        public static final int CONTROLLER_PORT = 1;
    }

    public static final class LeftDriveMotor {
        public static final int JOYSTICK_AXIS = 2; // D-MODE
        public static final int OUTPUT_PORT = 2;
        public static final boolean INVERTED = true;
        public static final MotorType POSITION = MotorType.kRearRight;
    }

    public static final class RightDriveMotor {
        public static final int JOYSTICK_AXIS = 4; // D-MODE
        public static final int OUTPUT_PORT = 1;
        public static final boolean INVERTED = true;
        public static final MotorType POSITION = MotorType.kRearLeft;
    }

    /**
     * The input for the robot. We use separate joysticks here, but really we'd only need on Joystick object (which really
     * represents the Logitech controller.
     */
    private Joystick controller;
    private RobotDrive drive;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {
        // We use separate Joytstick instances since it's actually just one Logitech controller, but we'll actually
        // use separate but specific axes for the left and right side ...
        controller = new Joystick(Inputs.CONTROLLER_PORT);
        drive = new RobotDrive(LeftDriveMotor.OUTPUT_PORT, RightDriveMotor.OUTPUT_PORT);
        drive.setInvertedMotor(RightDriveMotor.POSITION, RightDriveMotor.INVERTED);
        drive.setInvertedMotor(LeftDriveMotor.POSITION, LeftDriveMotor.INVERTED);
    }

    public void autonomousInit() {
        System.out.println("Entering autonomous mode");
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        // does not support autonomous mode
    }

    public void teleopInit() {
        System.out.println("Entering teleop mode");
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        while (isOperatorControl() && isEnabled()) {
            drive.tankDrive(controller, LeftDriveMotor.JOYSTICK_AXIS, controller, RightDriveMotor.JOYSTICK_AXIS);
            // drive.tankDrive(leftJoystick, rightJoystick);
            Timer.delay(0.01d);
        }
    }

    public void disabledInit() {
        System.out.println("Entering disabled mode");
    }

    public void disabledPeriodic() {
    }

    public void testInit() {
        System.out.println("Entering test mode");
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {

    }

}
