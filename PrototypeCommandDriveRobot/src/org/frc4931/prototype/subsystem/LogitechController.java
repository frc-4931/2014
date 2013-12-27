/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.subsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Handle input from the Logitech Controller, which has two physical modes.
 * <p>
 * The Logitech controller has the following axis assignments in <em>D MODE</em>:
 * <ul>
 * <li>Axis 1 - Left Joystick horizontal (X) axis</li>
 * <li>Axis 2 - Left Joystick vertical (Y) axis</li>
 * <li>Axis 3 - Right Joystick horizontal (X) axis</li>
 * <li>Axis 4 - Right Joystick vertical (Y) axis</li>
 * <li>Axis 5 - D-Pad Horizontal</li>
 * <li>Axis 6 - D-Pad Vertical</li>
 * </ul>
 * In <em>X MODE</em>:
 * <ul>
 * <li>Axis 1 - Left Joystick horizontal (X) axis</li>
 * <li>Axis 2 - Left Joystick vertical (Y) axis</li>
 * <li>Axis 3 - Left Trigger</li>
 * <li>Axis 4 - Right Joystick horizontal (X) axis</li>
 * <li>Axis 5 - Right Joystick vertical (Y) axis</li>
 * <li>Axis 6 - Right Trigger</li>
 * </ul>
 * <table>
 * <thead>
 * <th>Button #</th>
 * <th>Labels on X</th>
 * <th>Labels on D</th> </thead> <row>
 * <td>1</td>
 * <td>A</td>
 * <td>X</td> </row> <row>
 * <td>2</td>
 * <td>B</td>
 * <td>A</td> </row> <row>
 * <td>3</td>
 * <td>X</td>
 * <td>B</td> </row> <row>
 * <td>4</td>
 * <td>Y</td>
 * <td>Y</td> </row> <row>
 * <td>5</td>
 * <td>LB</td>
 * <td>LB</td> </row> <row>
 * <td>6</td>
 * <td>RB</td>
 * <td>RB</td> </row> <row>
 * <td>7</td>
 * <td>Back</td>
 * <td>LT</td> </row> <row>
 * <td>8</td>
 * <td>Start</td>
 * <td>RT</td> </row> <row>
 * <td>9</td>
 * <td>Left axis</td>
 * <td>Back</td> </row> <row>
 * <td>10</td>
 * <td>Right axis</td>
 * <td>Start</td> </row> <row>
 * <td>11</td>
 * <td>n/a</td>
 * <td>Left axis</td> </row> <row>
 * <td>12</td>
 * <td>n/a</td>
 * <td>Right axis</td> </row>
 * </table>
 */
public class LogitechController extends Joystick {

    protected static final class DModeAxes {
        public static final int LEFT_JOYSTICK_HORIZONTAL = 1;
        public static final int LEFT_JOYSTICK_VERTICAL = 2;
        public static final int RIGHT_JOYSTICK_HORIZONTAL = 3;
        public static final int RIGHT_JOYSTICK_VERTICAL = 4;
        public static final int D_PAD_HORIZONTAL = 5;
        public static final int D_PAD_VERTICAL = 6;
    }

    protected static final class XModeAxes {
        public static final int LEFT_JOYSTICK_HORIZONTAL = 1;
        public static final int LEFT_JOYSTICK_VERTICAL = 2;
        public static final int LEFT_TRIGGER = 3;
        public static final int RIGHT_JOYSTICK_HORIZONTAL = 4;
        public static final int RIGHT_JOYSTICK_VERTICAL = 5;
        public static final int RIGHT_TRIGGER = 6;
    }

    protected static final class DModeButtons {
        public static final int X = 1;
        public static final int A = 2;
        public static final int B = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int LT = 7;
        public static final int RT = 8;
        public static final int BACK = 9;
        public static final int START = 10;
        public static final int LEFT_AXIS = 11;
        public static final int RIGHT_AXIS = 12;
    }

    protected static final class XModeButtons {
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int BACK = 7;
        public static final int START = 8;
        public static final int LEFT_AXIS = 9;
        public static final int RIGHT_AXIS = 10;
    }

    /**
     * The default physical mode of the controller is "D".
     */
    public static final Mode DEFAULT_MODE = Mode.D;
    /**
     * The default drive style of the controller is tank.
     */
    public static final DriveStyle DEFAULT_STYLE = DriveStyle.TANK;

    /**
     * The physical mode of the controller as set with the physical switch on the underside of the controller.
     */
    public static final class Mode {
        /** The X-mode */
        public static final Mode X = new Mode("X");
        /** The D-mode */
        public static final Mode D = new Mode("D");
        private final String value;

        private Mode( String value ) {
            this.value = value;
        }

        public int hashCode() {
            return value.hashCode();
        }

        public boolean equals( Object obj ) {
            return obj instanceof Mode && ((Mode)obj).value.equals(this.value);
        }

        public String toString() {
            return value;
        }
    }

    /**
     * The drive style of the controller.
     */
    public static final class DriveStyle {
        /**
         * Arcade-style driving, which uses the left joystick where the vertical axis controls forward and backward speed and the
         * horizontal axis controls turn speed/rate.
         */
        public static final DriveStyle ARCADE_LEFT = new DriveStyle("ARCADE-LEFT");
        /**
         * Arcade-style driving, which uses the right joystick where the vertical axis controls forward and backward speed and the
         * horizontal axis controls turn speed/rate.
         */
        public static final DriveStyle ARCADE_RIGHT = new DriveStyle("ARCADE-RIGHT");
        /**
         * Tank-style driving, which uses the vertical axes of the left and right joysticks to control the left and right motor
         * speeds, respectively.
         */
        public static final DriveStyle TANK = new DriveStyle("TANK");
        private final String value;

        private DriveStyle( String value ) {
            this.value = value;
        }

        public int hashCode() {
            return value.hashCode();
        }

        public boolean equals( Object obj ) {
            return obj instanceof DriveStyle && ((DriveStyle)obj).value.equals(this.value);
        }

        public String toString() {
            return value;
        }
    }

    private Mode mode = DEFAULT_MODE;
    private DriveStyle style = DEFAULT_STYLE;

    /**
     * Construct an instance of a Logitech Controller that uses the default drive style and default mode.
     * 
     * @param port The number of the USB port on the driver station that the joystick is plugged into.
     */
    public LogitechController( int port ) {
        this(port, null, null);
    }

    /**
     * Construct an instance of a Logitech Controller that uses the default drive style and default mode.
     * 
     * @param port The number of the USB port on the driver station that the joystick is plugged into.
     * @param mode the mode of operation, either {@link Mode#D} or {@link Mode#X}; if <code>null</code>, the
     *        {@link LogitechController#DEFAULT_MODE default mode} is used
     * @param driveStyle the {@link DriveStyle} value, either {@link DriveStyle#TANK}, {@link DriveStyle#ARCADE_LEFT} or
     *        {@link DriveStyle#ARCADE_RIGHT}; if <code>null</code>, the {@link LogitechController#DEFAULT_STYLE default style} is
     *        used
     */
    public LogitechController( int port,
                               Mode mode,
                               DriveStyle driveStyle ) {
        super(port);
        this.setMode(mode);
        this.setStyle(driveStyle);
    }

    /**
     * Set the mode of the controller. This should match the controller's physical switch on the underside.
     * 
     * @param mode the mode of operation, either {@link Mode#D} or {@link Mode#X}; if <code>null</code>, the
     *        {@link LogitechController#DEFAULT_MODE default mode} is used
     */
    public void setMode( Mode mode ) {
        this.mode = mode != null ? mode : DEFAULT_MODE;
        // System.out.println("Setting Logitech Controller mode to " + this.mode);
    }

    /**
     * Get the mode of this controller. This should match the controller's physical switch on the underside.
     * 
     * @return the mode of operation, either {@link Mode#D} or {@link Mode#X}; never null
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Set whether the controller should use tank-drive or arcade-drive.
     * 
     * @param driveStyle the {@link DriveStyle} value, either {@link DriveStyle#TANK}, {@link DriveStyle#ARCADE_LEFT} or
     *        {@link DriveStyle#ARCADE_RIGHT}; if <code>null</code>, the {@link LogitechController#DEFAULT_STYLE default style} is
     *        used
     */
    public void setStyle( DriveStyle driveStyle ) {
        this.style = driveStyle != null ? driveStyle : DEFAULT_STYLE;
        // System.out.println("Setting Logitech Controller drive style to " + this.style);
    }

    /**
     * Get whether this controller is using tank-drive or arcade-drive.
     * 
     * @return the {@link DriveStyle} value, either {@link DriveStyle#TANK}, {@link DriveStyle#ARCADE_LEFT} or
     *         {@link DriveStyle#ARCADE_RIGHT}; never null
     */
    public DriveStyle getStyle() {
        return style;
    }

    /**
     * Call the appropriate {@link RobotDrive} drive method based upon the #
     * 
     * @param robotDrive
     */
    public void drive( RobotDrive robotDrive ) {
        if (Mode.X.equals(mode)) {
            // X-mode ...
            if (DriveStyle.TANK.equals(style)) {
                robotDrive.tankDrive(this, XModeAxes.LEFT_JOYSTICK_VERTICAL, this, XModeAxes.RIGHT_JOYSTICK_VERTICAL);
            } else if (DriveStyle.ARCADE_LEFT.equals(style)) {
                robotDrive.arcadeDrive(this, XModeAxes.LEFT_JOYSTICK_VERTICAL, this, XModeAxes.LEFT_JOYSTICK_HORIZONTAL);
            } else if (DriveStyle.ARCADE_RIGHT.equals(style)) {
                robotDrive.arcadeDrive(this, XModeAxes.RIGHT_JOYSTICK_VERTICAL, this, XModeAxes.RIGHT_JOYSTICK_HORIZONTAL);
            } else {
                System.out.println("Unknown Logitech Controller drive style: " + style);
            }
        } else if (Mode.D.equals(mode)) {
            // D-mode ...
            if (DriveStyle.TANK.equals(style)) {
                robotDrive.tankDrive(this, DModeAxes.LEFT_JOYSTICK_VERTICAL, this, DModeAxes.RIGHT_JOYSTICK_VERTICAL);
            } else if (DriveStyle.ARCADE_LEFT.equals(style)) {
                robotDrive.arcadeDrive(this, DModeAxes.LEFT_JOYSTICK_VERTICAL, this, DModeAxes.LEFT_JOYSTICK_HORIZONTAL);
            } else if (DriveStyle.ARCADE_RIGHT.equals(style)) {
                robotDrive.arcadeDrive(this, DModeAxes.RIGHT_JOYSTICK_VERTICAL, this, DModeAxes.RIGHT_JOYSTICK_HORIZONTAL);
            } else {
                System.out.println("Unknown Logitech Controller drive style: " + style);
            }
        } else {
            System.out.println("Unknown Logitech Controller mode: " + mode);
        }
    }

    public Button getXButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.X : DModeButtons.X;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getYButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.Y : DModeButtons.Y;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getAButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.A : DModeButtons.A;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getBButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.B : DModeButtons.B;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getBackButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.BACK : DModeButtons.BACK;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getStartButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.START : DModeButtons.START;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getLBButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.LB : DModeButtons.LB;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getRBButton() {
        int buttonNumber = Mode.X.equals(mode) ? XModeButtons.RB : DModeButtons.RB;
        return new JoystickButton(this, buttonNumber);
    }

    public Button getLTButton() {
        if (Mode.D.equals(mode)) {
            return new JoystickButton(this, DModeButtons.LT);
        }
        return null;
    }

    public Button getRTButton() {
        if (Mode.D.equals(mode)) {
            return new JoystickButton(this, DModeButtons.RT);
        }
        return null;
    }

    public String toString() {
        return "Logitech Controller (mode=" + mode + ", style=" + style + ")";
    }
}
