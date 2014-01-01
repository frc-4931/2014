/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.device.LogitechController.DriveStyle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command group that makes the robot drive forward at 50% power for 3 seconds, pause for 5 seconds, and then drive backward at
 * 50% power for 3 seconds.
 */
public class DriveForwardAndBackward extends CommandGroup {

    public DriveForwardAndBackward() {
        // Add these in sequence, though we can also add some commands or groups in parallel
        addSequential(new DriveAtSpeedForTime(0.5, 3.0d)); // drive forward at 50% power for 3 seconds
        addSequential(new StopDriving());
        addSequential(new WaitCommand(5.0d)); // wait for 5 seconds
        addSequential(new DriveAtSpeedForTime(-0.5, 3.0d)); // drive backward at 50% power for 3 seconds
        addSequential(new StopDriving());
        addSequential(new SetDriveStyle(DriveStyle.TANK));
    }
}
