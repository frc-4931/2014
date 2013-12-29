/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * A command that is used to change the drive style to the specified style.
 */
public class ToggleDriveStyle extends CommandBase {

    public ToggleDriveStyle() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.print(toString());
    }

    protected void execute() {
        Robot.driveTrain.changeDefaultDriveStyle();
    }

    protected boolean isFinished() {
        return true; // finishes immediately
    }

    protected void end() {
        // do nothing
    }

    public String toString() {
        return "Toggling drive style";
    }
}
