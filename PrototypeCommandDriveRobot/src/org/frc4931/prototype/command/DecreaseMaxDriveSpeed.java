/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

/**
 * A command used to decrease the maximum speed of the robot.
 */
public class DecreaseMaxDriveSpeed extends ChangeMaxDriveSpeed {

    public DecreaseMaxDriveSpeed( double delta ) {
        super(DecreaseMaxDriveSpeed.class.getName(), -delta);
    }

    public String toString() {
        return "Drive with arcade-style joystick";
    }
}
