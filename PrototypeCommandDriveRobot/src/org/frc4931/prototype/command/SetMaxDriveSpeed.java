/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * A command used to set the maximum speed of the robot to a specific value.
 * 
 * @see IncreaseMaxDriveSpeed
 * @see DecreaseMaxDriveSpeed
 */
public class SetMaxDriveSpeed extends CommandBase {

    protected final double newMaxSpeed;

    /**
     * Create a new command to set the max speed.
     * 
     * @param newMaxSpeed the new maximum drive speed, between 0.0 and 1.0; if negative, then 0.0 will be used; if greater than
     *        1.0, then 1.0 will be used.
     */
    public SetMaxDriveSpeed( double newMaxSpeed ) {
        this.newMaxSpeed = newMaxSpeed;
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.print(toString());
    }

    protected void execute() {
        Robot.driveTrain.setMaxDriveSpeed(newMaxSpeed);
    }

    protected boolean isFinished() {
        return true; // finishes immediately
    }

    protected void end() {
        // nothing to do
    }
}
