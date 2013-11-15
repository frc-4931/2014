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

    public static final class InputPorts {
        public static final int LEFT_JOYSTICK = 1;
        public static final int RIGHT_JOYSTICK = 1;
    }

    public static final class OutputPorts {
        public static final int LEFT_MOTOR = 1;
        public static final int RIGHT_MOTOR = 2;
    }

    /**
     * The input for the robot. In this case, the X-axis is selected by default for the turn axis and the Y-axis is selected for
     * the speed axis.
     */
    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private RobotDrive drive;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {
        leftJoystick = new Joystick(InputPorts.LEFT_JOYSTICK);
        rightJoystick = new Joystick(InputPorts.RIGHT_JOYSTICK);
        drive = new RobotDrive(OutputPorts.LEFT_MOTOR, OutputPorts.RIGHT_MOTOR);
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        // does not support autonomous mode
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        while (isOperatorControl() && isEnabled()) {
            drive.tankDrive(leftJoystick, rightJoystick);
            Timer.delay(0.01d);
        }
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {

    }

}
