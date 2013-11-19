/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * Pauses the robot for the specified number of seconds.
 */
public class WaitCommand extends CommandBase {

    private final double timeInSeconds;

    public WaitCommand( double timeInSeconds ) {
        this.timeInSeconds = timeInSeconds;
        setTimeout(timeInSeconds);
    }

    protected void execute() {
        Robot.printDebug(toString());
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    public String toString() {
        return "Pause for " + timeInSeconds + " seconds";
    }
}
