/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;
import edu.wpi.first.wpilibj.Timer;

/**
 * A command that is used to zero out the controller axes inputs. This method should be used only when the controller axes are
 * physically in their neutral position.
 */
public class ZeroControllerInputs extends CommandBase {

    public ZeroControllerInputs() {
    }

    protected void execute() {
        Robot.print("*** Prepare to zero controller inputs ... DO NOT TOUCH CONTROLLER BUTTONS OR JOYSTICKS");
        for (int i = 3; i != 0; --i) {
            Robot.print("  " + i + " seconds ...");
            Timer.delay(1.0d);
        }
        Robot.print("*** Zeroing controller inputs");
        Robot.operatorInterface.getController().zeroAxisReadings();
        Robot.print("*** Completed zeroing controller inputs.");
    }

    protected boolean isFinished() {
        return true; // finishes immediately
    }

    protected void end() {
        // do nothing
    }

    public String toString() {
        return "Zero controller inputs";
    }
}
