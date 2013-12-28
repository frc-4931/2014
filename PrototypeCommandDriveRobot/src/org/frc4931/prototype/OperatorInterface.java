/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype;

import org.frc4931.prototype.command.ArcadeDriveWithJoystick;
import org.frc4931.prototype.command.DecreaseMaxDriveSpeed;
import org.frc4931.prototype.command.IncreaseMaxDriveSpeed;
import org.frc4931.prototype.command.TankDriveWithJoysticks;
import org.frc4931.prototype.command.VerboseOutputToggle;
import org.frc4931.prototype.subsystem.LogitechController;
import org.frc4931.prototype.subsystem.LogitechController.DriveStyle;
import org.frc4931.prototype.subsystem.LogitechController.Mode;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow
 * control of the robot.
 * <p>
 * The single instance of this class is created as a static field in the {@link Robot#robotInit()} method.
 * </p>
 */
public final class OperatorInterface {

    private final LogitechController controller = new LogitechController(Robot.Controller.PORT, Mode.D, DriveStyle.TANK);

    public OperatorInterface() {
        // Run these commands each time the button is pressed ...
        controller.getBButton().whenPressed(new IncreaseMaxDriveSpeed(Robot.DriveMotors.DELTA_MAX_DRIVE_SPEED));
        controller.getAButton().whenPressed(new DecreaseMaxDriveSpeed(Robot.DriveMotors.DELTA_MAX_DRIVE_SPEED));
        controller.getYButton().whenPressed(new VerboseOutputToggle());
        controller.getBackButton().whenPressed(new ArcadeDriveWithJoystick());
        controller.getStartButton().whenPressed(new TankDriveWithJoysticks());
    }

    public LogitechController getController() {
        return controller;
    }

}
