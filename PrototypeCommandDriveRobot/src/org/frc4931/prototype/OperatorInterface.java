/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype;

import org.frc4931.prototype.command.DecreaseMaxDriveSpeed;
import org.frc4931.prototype.command.IncreaseMaxDriveSpeed;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow
 * control of the robot.
 */
public final class OperatorInterface {

    private final Joystick driveJoystick = new Joystick(Robot.DriveJoystick.PORT);
    private final Button increaseDriveSpeedButton = new JoystickButton(driveJoystick, Robot.DriveJoystick.INCREASE_SPEED_BUTTON);
    private final Button decreaseDriveSpeedButton = new JoystickButton(driveJoystick, Robot.DriveJoystick.DECREASE_SPEED_BUTTON);

    public OperatorInterface() {
        // Run these commands each time the button is pressed ...
        increaseDriveSpeedButton.whenPressed(new IncreaseMaxDriveSpeed(Robot.DriveMotors.DELTA_MAX_DRIVE_SPEED));
        decreaseDriveSpeedButton.whenPressed(new DecreaseMaxDriveSpeed(Robot.DriveMotors.DELTA_MAX_DRIVE_SPEED));
    }

    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

}
