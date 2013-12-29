/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.subsystem;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.command.ArcadeDriveWithJoystick;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The drive train that uses two {@link Jaguar} motors, and which sets up and uses an {@link ArcadeDriveWithJoystick} command by
 * default.
 */
public class JaguarDriveTrain extends DriveTrain {

    public JaguarDriveTrain() {
        super(new Jaguar(Robot.DriveMotors.LEFT_PORT), new Jaguar(Robot.DriveMotors.RIGHT_PORT));
    }

    protected Jaguar leftMotor() {
        return (Jaguar)leftMotor;
    }

    protected Jaguar rightMotor() {
        return (Jaguar)rightMotor;
    }

    protected double currentLeftSpeed() {
        return leftMotor().getSpeed();
    }

    protected double currentRightSpeed() {
        return rightMotor().getSpeed();
    }

    protected void addInLiveWindow( String subsystemName ) {
        LiveWindow.addActuator(subsystemName, "left motor", leftMotor());
        LiveWindow.addActuator(subsystemName, "right motor", rightMotor());
    }
}
