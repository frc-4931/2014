/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * Toggle whether the program outputs verbose messages.
 * 
 * @see Robot#printDebug(String)
 * @see Robot#print(String)
 */
public class ToggleVerboseOutput extends CommandBase {

    public ToggleVerboseOutput() {
        super();
    }

    protected void execute() {
        Robot.print(toString());
        Robot.toggleVerboseOutput();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        // do nothing
    }

    public String toString() {
        return "Toggle debug outputs";
    }
}
