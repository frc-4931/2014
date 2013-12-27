/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2013. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.frc4931.prototype;

import org.frc4931.prototype.LogitechController.DriveStyle;
import org.frc4931.prototype.LogitechController.Mode;
import edu.wpi.first.wpilibj.IterativeRobot;
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
     * The drive joystick's input port.
     */
    public static final class Inputs {
        public static final int CONTROLLER_PORT = 1;
    }

    public static final class LeftDriveMotor {
        public static final int OUTPUT_PORT = 2;
        public static final boolean INVERTED = true;
        public static final MotorType POSITION = MotorType.kRearRight;
    }

    public static final class RightDriveMotor {
        public static final int OUTPUT_PORT = 1;
        public static final boolean INVERTED = true;
        public static final MotorType POSITION = MotorType.kRearLeft;
    }

    /**
     * The input for the robot. We use separate joysticks here, but really we'd only need on Joystick object (which really
     * represents the Logitech controller.
     */
    private LogitechController controller;
    private RobotDrive drive;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {
        // We use separate Joytstick instances since it's actually just one Logitech controller, but we'll actually
        // use separate but specific axes for the left and right side ...
        controller = new LogitechController(Inputs.CONTROLLER_PORT, Mode.D, DriveStyle.TANK);
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
            controller.drive(drive);
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
