/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.subsystem;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.command.ArcadeDriveWithJoystick;
import org.frc4931.prototype.command.TankDriveWithJoysticks;
import org.frc4931.prototype.subsystem.LogitechController.DriveStyle;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The drive train, which sets up and uses an {@link ArcadeDriveWithJoystick} command by default.
 */
public abstract class DriveTrain extends Subsystem {

    private static final double MAX_SPEED_FACTOR = 1.0d;
    private static final double MIN_SPEED_FACTOR = 0.0d;

    protected final SpeedController leftMotor;
    protected final SpeedController rightMotor;
    private final RobotDrive drive;

    private volatile double speedFactor = MAX_SPEED_FACTOR;

    protected DriveTrain( SpeedController leftMotor,
                          SpeedController rightMotor ) {
        // Set up the motors ...
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        // And the drive controller ...
        drive = new RobotDrive(leftMotor, rightMotor);
        drive.setInvertedMotor(Robot.DriveMotors.LEFT_POSITION, Robot.DriveMotors.LEFT_REVERSED);
        drive.setInvertedMotor(Robot.DriveMotors.RIGHT_POSITION, Robot.DriveMotors.RIGHT_REVERSED);
        drive.setSafetyEnabled(false);
        setMaxDriveSpeed(Robot.DriveMotors.INITIAL_MAX_DRIVE_SPEED);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TankDriveWithJoysticks());
        // setDefaultCommand(new ArcadeDriveWithJoystick());
    }

    /**
     * Drive forward or backward.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param speedFactor the fraction of full-speed to drive, ranging from -1.0 (backward at full power) to 1.0 (forward at full
     *        power)
     */
    public void driveStraight( double speedFactor ) {
        drive.tankDrive(speedFactor, speedFactor);
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
     * Drive using the left joystick in arcade-style.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void driveWithArcadeJoystick() {
        Robot.operatorInterface.getController().setStyle(DriveStyle.ARCADE_LEFT).drive(drive);
    }

    /**
     * Drive using two joysticks in tank style.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void driveWithTankJoysticks() {
        Robot.operatorInterface.getController().setStyle(DriveStyle.TANK).drive(drive);
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

    protected abstract double currentLeftSpeed();

    protected abstract double currentRightSpeed();

    public void updateStatus() {
        SmartDashboard.putNumber("Drive Motor (Left)", currentLeftSpeed());
        SmartDashboard.putNumber("Drive Motor (Right)", currentRightSpeed());
        SmartDashboard.putNumber("Drive Max Speed", speedFactor);
    }

}
