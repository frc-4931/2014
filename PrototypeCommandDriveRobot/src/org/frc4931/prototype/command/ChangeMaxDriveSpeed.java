/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * A command used to increase or decrease the maximum speed of the robot.
 */
public abstract class ChangeMaxDriveSpeed extends CommandBase {

    private final double delta;

    protected ChangeMaxDriveSpeed( String name,
                                   double delta ) {
        super(name + "(" + delta + ")");
        this.delta = delta;
        requires(Robot.driveTrain);
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        // Called repeatedly when this Command is scheduled to run
        Robot.driveTrain.changeMaxDriveSpeed(delta);
    }

    protected boolean isFinished() {
        // Make this return true when this Command no longer needs to run execute()
        return true;
    }

    protected void end() {
        // nothing to do
    }

}
