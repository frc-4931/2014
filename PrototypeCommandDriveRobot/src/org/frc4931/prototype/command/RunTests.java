/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command group that tests various functions of the robot.
 */
public class RunTests extends CommandGroup {

    public RunTests() {
        boolean wasVerbose = Robot.isVerboseOutputEnabled();
        addSequential(new VerboseOutputSet(true));

        // Add these in sequence, though we can also add some commands or groups in parallel
        addSequential(new DriveAtSpeedForTime(1.0, 3.0d)); // drive forward at 100% power for 3 seconds
        addSequential(new DriveAtSpeedForTime(0.5, 3.0d)); // drive forward at 50% power for 3 seconds
        addSequential(new WaitCommand(3.0d)); // wait for 3 seconds

        // Switch verbose back ...
        if (!wasVerbose) addSequential(new VerboseOutputSet(false));
    }

    public String toString() {
        return "Run the tests";
    }
}
