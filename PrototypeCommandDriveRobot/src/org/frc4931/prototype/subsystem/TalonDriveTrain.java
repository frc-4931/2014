/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.subsystem;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.command.ArcadeDriveWithJoystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * The drive train that uses two {@link Talon} motors, and which sets up and uses an {@link ArcadeDriveWithJoystick} command by
 * default.
 */
public class TalonDriveTrain extends DriveTrain {

    public TalonDriveTrain() {
        super(new Talon(Robot.DriveMotors.LEFT_PORT), new Talon(Robot.DriveMotors.RIGHT_PORT));
    }

    protected Talon leftMotor() {
        return (Talon)leftMotor;
    }

    protected Talon rightMotor() {
        return (Talon)rightMotor;
    }

    protected double currentLeftSpeed() {
        return leftMotor().getSpeed();
    }

    protected double currentRightSpeed() {
        return rightMotor().getSpeed();
    }
}
