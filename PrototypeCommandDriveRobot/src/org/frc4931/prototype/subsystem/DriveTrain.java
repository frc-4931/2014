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
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The drive train, which sets up and uses an {@link ArcadeDriveWithJoystick} command by default.
 */
public class DriveTrain extends Subsystem {

    private static final double MAX_SPEED_FACTOR = 1.0d;
    private static final double MIN_SPEED_FACTOR = 0.0d;

    private final Jaguar leftMotor;
    private final Jaguar rightMotor;
    private final RobotDrive drive;

    private double speedFactor = MAX_SPEED_FACTOR;

    public DriveTrain() {
        // Set up the motors ...
        leftMotor = new Jaguar(Robot.DriveMotors.LEFT_PORT);
        rightMotor = new Jaguar(Robot.DriveMotors.RIGHT_PORT);

        // And the drive controller ...
        drive = new RobotDrive(leftMotor, rightMotor);
        drive.setInvertedMotor(MotorType.kRearLeft, Robot.DriveMotors.LEFT_REVERSED);
        drive.setInvertedMotor(MotorType.kRearRight, Robot.DriveMotors.RIGHT_REVERSED);
        drive.setSafetyEnabled(false);
        setMaxDriveSpeed(Robot.DriveMotors.INITIAL_MAX_DRIVE_SPEED);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDriveWithJoystick());
    }

    /**
     * Drive forward at full power.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void driveStraight() {
        drive.arcadeDrive(1.0, 0.0);
    }

    /**
     * Stop driving.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void stopAllMotors() {
        drive.stopMotor();
    }

    /**
     * Drive using the joystick.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void driveWithArcadeJoystick() {
        drive.arcadeDrive(Robot.operatorInterface.getDriveJoystick());
    }

    /**
     * Increase or decrease the maximum drive speed by the given delta. Calling this method is safe even if the delta is out of
     * range, because the maximum drive speed will never be set smaller than 0.0 or larger than 1.0.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param delta the change in the maximum drive speed, between -1.0 and 1.0
     */
    public void changeMaxDriveSpeed( double delta ) {
        setMaxDriveSpeed(speedFactor + delta);
    }

    /**
     * Set the maximum drive speed by the given delta.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param newSpeed the new maximum drive speed, between 0.0 and 1.0.
     */
    public void setMaxDriveSpeed( double newSpeed ) {
        // Make sure the new speed is in range ...
        newSpeed = Math.max(newSpeed, MIN_SPEED_FACTOR);
        newSpeed = Math.min(newSpeed, MAX_SPEED_FACTOR);
        speedFactor = newSpeed;
        drive.setMaxOutput(newSpeed);
    }

    public void updateStatus() {
        SmartDashboard.putNumber("Drive (Left)", leftMotor.getSpeed());
        SmartDashboard.putNumber("Drive (Right)", rightMotor.getSpeed());
    }

}
