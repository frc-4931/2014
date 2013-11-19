/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The base class for all of our {@link Command} implementation classes.
 */
public abstract class CommandBase extends Command {

    public CommandBase( String name ) {
        super(name);
    }

    public CommandBase() {
        super();
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     * <p>
     * By default, this method does nothing.
     * </p>
     */
    protected void initialize() {
        // do nothing
    }

    /**
     * Called when the command ends because somebody called {@link Command#cancel() cancel()} or another command shared the same
     * requirements as this one, and booted it out.
     * <p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being used in the command.
     * </p>
     * <p>
     * By default, this simply calls the {@link Command#end() end()} method.
     * </p>
     */
    protected void interrupted() {
        end();
    }
}
