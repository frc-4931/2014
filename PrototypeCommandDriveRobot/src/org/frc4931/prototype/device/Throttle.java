/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.device;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * A mechanical throttle
 */
public class Throttle implements NamedSendable {

    private final String name;
    private final AnalogChannel slider;
    private final double voltageRange;
    private final double speedChangeBeforeDetection;
    private double currentSpeed;
    private ITable table;

    public Throttle( String name,
                     int sliderChannel,
                     double voltageRange,
                     int percentChangeBeforeDetection ) {
        this.name = name;
        this.slider = new AnalogChannel(sliderChannel);
        this.voltageRange = Math.abs(voltageRange);
        this.speedChangeBeforeDetection = inRange(percentChangeBeforeDetection / 100);
    }

    public String getName() {
        return this.name;
    }

    public String getSmartDashboardType() {
        return getName();
    }

    public void initTable( ITable table ) {
        this.table = table;
        if (table != null) {
            table.putNumber("Volts", slider.getVoltage());
            table.putNumber("Speed", currentSpeed);
        }
    }

    public ITable getTable() {
        return table;
    }

    /**
     * Get the current measured speed.
     * 
     * @return the current speed; always between 0.0 and 1.0 inclusive.
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Detect whether the current voltage measured by the throttle causes a measurable change in the {@link #getCurrentSpeed()
     * current speed}.
     * 
     * @return true if the {@link #getCurrentSpeed() current speed} has changed, or false otherwise
     */
    public boolean hasChanged() {
        double voltage = inRange(slider.getVoltage());
        double newMaxSpeed = Math.abs(voltage) / voltageRange;
        double diffInSpeed = Math.abs(currentSpeed - newMaxSpeed);
        if (diffInSpeed >= speedChangeBeforeDetection) {
            // Record the new speed ...
            currentSpeed = newMaxSpeed;
            return true;
        }
        return false;
    }

    /**
     * Determine the value in the range 0.0 to 1.0, inclusive.
     * 
     * @param value the original value
     * @return the in-range value; always between 0.0 and 1.0 inclusive
     */
    protected static double inRange( double value ) {
        return Math.max(0.0, Math.min(1.0d, Math.abs(value / 100)));
    }
}
