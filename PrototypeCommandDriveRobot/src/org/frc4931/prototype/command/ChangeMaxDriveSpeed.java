/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * A abstract command used to increase or decrease the maximum speed of the robot.
 * 
 * @see IncreaseMaxDriveSpeed
 * @see DecreaseMaxDriveSpeed
 */
public abstract class ChangeMaxDriveSpeed extends CommandBase {

    protected final double delta;

    protected ChangeMaxDriveSpeed( String name,
                                   double delta ) {
        super(name + "(" + delta + ")");
        this.delta = delta;
        requires(Robot.driveTrain);
    }

    protected void execute() {
        Robot.printDebug(toString());
        Robot.driveTrain.changeMaxDriveSpeed(delta);
    }

    protected boolean isFinished() {
        return true; // finishes immediately
    }

    protected void end() {
        // nothing to do
    }
}
