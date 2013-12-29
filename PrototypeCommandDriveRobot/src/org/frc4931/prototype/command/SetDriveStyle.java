/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.subsystem.LogitechController.DriveStyle;

/**
 * A command that is used to set the drive style to the specified style.
 */
public class SetDriveStyle extends CommandBase {

    private final DriveStyle style;

    public SetDriveStyle( DriveStyle style ) {
        this.style = style;
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.print(toString());
    }

    protected void execute() {
        Robot.driveTrain.changeDefaultDriveStyleTo(style);
    }

    protected boolean isFinished() {
        return true; // finishes immediately
    }

    protected void end() {
        // do nothing
    }

    public String toString() {
        return "Change drive style to " + style;
    }
}
