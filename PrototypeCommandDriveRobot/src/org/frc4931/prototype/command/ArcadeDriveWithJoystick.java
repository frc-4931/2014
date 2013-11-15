/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.subsystem.DriveTrain;

/**
 * A command that is used to drive with the single joystick. This is created as the default command in the {@link DriveTrain}.
 */
public class ArcadeDriveWithJoystick extends CommandBase {

    public ArcadeDriveWithJoystick() {
        requires(Robot.driveTrain);
    }

    protected void execute() {
        Robot.driveTrain.driveWithArcadeJoystick();
    }

    protected boolean isFinished() {
        return false; // never finishes
    }

    protected void end() {
        Robot.driveTrain.stopAllMotors();
    }
}
