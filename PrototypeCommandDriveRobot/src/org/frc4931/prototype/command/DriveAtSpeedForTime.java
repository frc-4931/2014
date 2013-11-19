/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * Controls the drive train to make the robot drive forward or backward at the specified fraction of full power for the specified
 * number of seconds.
 */
public class DriveAtSpeedForTime extends CommandBase {

    private final double speedFactor;
    private final double timeInSeconds;

    /**
     * Create a new drive command.
     * 
     * @param speedFactor the fraction of full-speed to drive, ranging from -1.0 (backward at full power) to 1.0 (forward at full
     *        power)
     * @param timeInSeconds the time in seconds
     */
    public DriveAtSpeedForTime( double speedFactor,
                                double timeInSeconds ) {
        this.speedFactor = speedFactor;
        this.timeInSeconds = timeInSeconds;
        setTimeout(timeInSeconds);
        requires(Robot.driveTrain);
    }

    protected void execute() {
        Robot.printDebug(toString());
        Robot.driveTrain.driveStraight(speedFactor);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.driveTrain.stopAllMotors();
    }

    public String toString() {
        return "Drive at " + (int)(speedFactor * 100.0d) + "% speed for " + timeInSeconds + " seconds";
    }

}
