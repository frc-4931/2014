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
        // First, determine whether we're logging verbose output already ...
        boolean wasVerbose = Robot.isVerboseOutputEnabled();

        // Then always enable verbose logging for the tests ...
        addSequential(new SetVerboseOutput(true));

        // Add these in sequence, though we can also add some commands or groups in parallel
        addSequential(new DriveAtSpeedForTime(1.00, 3.0d)); // drive forward at 100% power for 3 seconds
        addSequential(new DriveAtSpeedForTime(0.50, 3.0d)); // drive forward at 50% power for 3 seconds
        addSequential(new DriveAtSpeedForTime(0.25, 3.0d)); // drive forward at 25% power for 3 seconds
        addSequential(new StopDriving());
        addSequential(new WaitCommand(3.0d)); // wait for 3 seconds
        addSequential(new DriveAtSpeedForTime(-0.50, 3.0d)); // drive backward at 50% power for 3 seconds
        addSequential(new StopDriving());

        // Switch verbose back to what it was before we ran the tests ...
        addSequential(new SetVerboseOutput(wasVerbose));
    }

    public String toString() {
        return "Run the tests";
    }
}
