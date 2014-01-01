/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.subsystem;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.command.ArcadeDriveWithJoystick;
import org.frc4931.prototype.device.Throttle;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The drive train that uses two {@link Jaguar} motors, and which sets up and uses an {@link ArcadeDriveWithJoystick} command by
 * default.
 */
public class JaguarDriveTrain extends DriveTrain {

    /**
     * Create a new instance of the drive train.
     * 
     * @param throttle the optional analog throttle; may be null if there is no analog throttle
     */
    public JaguarDriveTrain( Throttle throttle ) {
        super(new Jaguar(Robot.DriveMotors.LEFT_PORT), new Jaguar(Robot.DriveMotors.RIGHT_PORT), throttle);
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

    public void addInLiveWindow() {
        LiveWindow.addActuator(getName(), "left motor", leftMotor());
        LiveWindow.addActuator(getName(), "right motor", rightMotor());
    }
}
