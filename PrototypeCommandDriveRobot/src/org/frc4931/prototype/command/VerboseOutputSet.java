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
public class VerboseOutputSet extends CommandBase {

    private final boolean enable;

    public VerboseOutputSet( boolean enable ) {
        super();
        this.enable = enable;
    }

    protected void execute() {
        if (!enable) Robot.printDebug(toString());
        Robot.enableVerboseOutput(enable);
        if (enable) Robot.printDebug(toString());
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    public String toString() {
        return (enable ? "Turn on" : "Turn off") + " debug outputs";
    }
}
