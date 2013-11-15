/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2013. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.frc4931.prototype;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * A simple iterative robot that basically does nothing. The {@link IterativeRobot} superclass will automatically invoke these
 * methods based upon the current mode of the robot:
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
 * MIDlet-Name: SimpleIterativeRobot
 * ...
 * MIDlet-1: SimpleIterativeRobot, , org.frc4931.prototype.SimpleIterativeRobot
 * </code>
 */
public class SimpleIterativeRobot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {

    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {

    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {

    }

}
