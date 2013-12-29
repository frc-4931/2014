/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * Controls the drive train to stop all motors immediately.
 */
public class StopDriving extends CommandBase {

    /**
     * Create a new command to stop the robot.
     */
    public StopDriving() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.print(toString());
    }

    protected void execute() {
        Robot.driveTrain.stopAllMotors();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    public String toString() {
        return "Stop all motors";
    }

}
