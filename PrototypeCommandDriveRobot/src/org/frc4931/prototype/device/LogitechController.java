/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.device;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.tables.ITable;

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
public class LogitechController extends Joystick implements NamedSendable {

    protected static final class DMode {
        public static final class Axes {
            public static final int LEFT_JOYSTICK_HORIZONTAL = 1;
            public static final int LEFT_JOYSTICK_VERTICAL = 2;
            public static final int RIGHT_JOYSTICK_HORIZONTAL = 3;
            public static final int RIGHT_JOYSTICK_VERTICAL = 4;
            public static final int D_PAD_HORIZONTAL = 5;
            public static final int D_PAD_VERTICAL = 6;
        }

        public static final class Buttons {
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
    }

    protected static final class XMode {
        public static final class Axes {
            public static final int LEFT_JOYSTICK_HORIZONTAL = 1;
            public static final int LEFT_JOYSTICK_VERTICAL = 2;
            public static final int LEFT_TRIGGER = 3;
            public static final int RIGHT_JOYSTICK_HORIZONTAL = 4;
            public static final int RIGHT_JOYSTICK_VERTICAL = 5;
            public static final int RIGHT_TRIGGER = 6;
        }

        public static final class Buttons {
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
            return obj == this || (obj instanceof Mode && ((Mode)obj).value.equals(this.value));
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
            return obj == this || (obj instanceof DriveStyle && ((DriveStyle)obj).value.equals(this.value));
        }

        public String toString() {
            return value;
        }
    }

    private final String name;
    private ITable table;

    /** The mode is set upon construction and never changed */
    private final Mode mode;

    /** The buttons are all determined by the controller mode, and thus are created upon construction and never changed */
    private final Button xButton;
    private final Button yButton;
    private final Button aButton;
    private final Button bButton;
    private final Button lbButton;
    private final Button rbButton;
    private final Button ltButton;
    private final Button rtButton;
    private final Button backButton;
    private final Button startButton;

    /**
     * The error corrections for the joystick axes.
     */
    private final double[] axisErrors = new double[7];

    /** The style of drive, which can be changed at any time. */
    private DriveStyle style = DEFAULT_STYLE;

    /**
     * The specific implementation for the particular drive style and controller mode. This is set when the style is set, and by
     * having a specific implementation class for each combination we're able to set this once when the {@link #setStyle style is
     * set} and simply delegate to it in the frequently-called {@link #drive(RobotDrive)} method without having to check the drive
     * style and the controller mode. This makes the {@link #drive(RobotDrive)} method faster since it has to do less work than if
     * it first had to check the drive style and controller mode to determine the appropriate drive operation to call.
     */
    private DriveLogic driveLogic;

    /**
     * Construct an instance of a Logitech Controller that uses the specified controller mode and the default drive style.
     * 
     * @param name the name of this controller
     * @param port The number of the USB port on the driver station that the joystick is plugged into.
     * @param mode the mode of operation, either {@link Mode#D} or {@link Mode#X}; if <code>null</code>, the
     *        {@link LogitechController#DEFAULT_MODE default mode} is used
     */
    public LogitechController( String name,
                               int port,
                               Mode mode ) {
        this(name, port, mode, null);
    }

    /**
     * Construct an instance of a Logitech Controller that uses the specified drive style and controller mode. Calling this
     * constructor is equivalent to calling <code>controller = new LogitechController(port,mode)</code> and then immediately
     * calling <code>controller.setStyle(style)</code> (or
     * <code>controller = new LogitechController(port,mode).setStyle(style)</code>).
     * 
     * @param name the name of this controller
     * @param port The number of the USB port on the driver station that the joystick is plugged into.
     * @param mode the mode of operation, either {@link Mode#D} or {@link Mode#X}; if <code>null</code>, the
     *        {@link LogitechController#DEFAULT_MODE default mode} is used
     * @param driveStyle the {@link DriveStyle} value, either {@link DriveStyle#TANK}, {@link DriveStyle#ARCADE_LEFT} or
     *        {@link DriveStyle#ARCADE_RIGHT}; if <code>null</code>, the {@link LogitechController#DEFAULT_STYLE default style} is
     *        used
     */
    public LogitechController( String name,
                               int port,
                               Mode mode,
                               DriveStyle driveStyle ) {
        super(port);
        this.name = name;
        this.mode = mode != null ? mode : DEFAULT_MODE;
        // Create the buttons based upon the controller mode ...
        if (Mode.X.equals(mode)) {
            this.xButton = new JoystickButton(this, XMode.Buttons.X);
            this.yButton = new JoystickButton(this, XMode.Buttons.Y);
            this.aButton = new JoystickButton(this, XMode.Buttons.A);
            this.bButton = new JoystickButton(this, XMode.Buttons.B);
            this.lbButton = new JoystickButton(this, XMode.Buttons.LB);
            this.rbButton = new JoystickButton(this, XMode.Buttons.RB);
            this.ltButton = null;
            this.rtButton = null;
            this.backButton = new JoystickButton(this, XMode.Buttons.BACK);
            this.startButton = new JoystickButton(this, XMode.Buttons.START);
        } else {
            this.xButton = new JoystickButton(this, DMode.Buttons.X);
            this.yButton = new JoystickButton(this, DMode.Buttons.Y);
            this.aButton = new JoystickButton(this, DMode.Buttons.A);
            this.bButton = new JoystickButton(this, DMode.Buttons.B);
            this.lbButton = new JoystickButton(this, DMode.Buttons.LB);
            this.rbButton = new JoystickButton(this, DMode.Buttons.RB);
            this.ltButton = new JoystickButton(this, DMode.Buttons.LT);
            this.rtButton = new JoystickButton(this, DMode.Buttons.RT);
            this.backButton = new JoystickButton(this, DMode.Buttons.BACK);
            this.startButton = new JoystickButton(this, DMode.Buttons.START);
        }
        // Set the style, which will instantiate the driveLogic instance ...
        this.setStyle(driveStyle);
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
     * Get whether this controller is using tank-drive or arcade-drive.
     * 
     * @return the {@link DriveStyle} value, either {@link DriveStyle#TANK}, {@link DriveStyle#ARCADE_LEFT} or
     *         {@link DriveStyle#ARCADE_RIGHT}; never null
     */
    public DriveStyle getStyle() {
        return style;
    }

    /**
     * Set whether the controller should use tank-drive or arcade-drive.
     * 
     * @param driveStyle the {@link DriveStyle} value, either {@link DriveStyle#TANK}, {@link DriveStyle#ARCADE_LEFT} or
     *        {@link DriveStyle#ARCADE_RIGHT}; if <code>null</code>, the {@link LogitechController#DEFAULT_STYLE default style} is
     *        used
     * @return this controller instance to allow for chaining additional methods; never null
     */
    public LogitechController setStyle( DriveStyle driveStyle ) {
        if (this.style != null && this.driveLogic != null && this.style.equals(driveStyle)) {
            // We're not changing the drive style, so we're done ...
            return this;
        }
        this.style = driveStyle != null ? driveStyle : DEFAULT_STYLE;
        // Now create the particular kind of DriveLogic implementation for the combination of drive style and controller mode ...
        if (Mode.X.equals(mode)) {
            // X-mode ...
            if (DriveStyle.TANK.equals(style)) {
                this.driveLogic = new XModeTankDriveLogic();
            } else if (DriveStyle.ARCADE_LEFT.equals(style)) {
                this.driveLogic = new XModeArcadeLeftDriveLogic();
            } else if (DriveStyle.ARCADE_RIGHT.equals(style)) {
                this.driveLogic = new XModeArcadeRightDriveLogic();
            }
        } else if (Mode.D.equals(mode)) {
            // D-mode ...
            if (DriveStyle.TANK.equals(style)) {
                this.driveLogic = new DModeTankDriveLogic();
            } else if (DriveStyle.ARCADE_LEFT.equals(style)) {
                this.driveLogic = new DModeArcadeLeftDriveLogic();
            } else if (DriveStyle.ARCADE_RIGHT.equals(style)) {
                this.driveLogic = new DModeArcadeRightDriveLogic();
            }
        }
        return this;
    }

    /**
     * Call the appropriate {@link RobotDrive} drive method based upon the #
     * 
     * @param robotDrive
     */
    public void drive( RobotDrive robotDrive ) {
        // Delegate to the particular kind of DriveLogic instance for the style and mode ...
        driveLogic.drive(robotDrive);
    }

    /**
     * Get the controller's button that is labeled "X".
     * 
     * @return the button; never null
     */
    public Button getXButton() {
        return this.xButton;
    }

    /**
     * Get the controller's button that is labeled "Y".
     * 
     * @return the button; never null
     */
    public Button getYButton() {
        return this.yButton;
    }

    /**
     * Get the controller's button that is labeled "A".
     * 
     * @return the button; never null
     */
    public Button getAButton() {
        return this.aButton;
    }

    /**
     * Get the controller's button that is labeled "B".
     * 
     * @return the button; never null
     */
    public Button getBButton() {
        return this.bButton;
    }

    /**
     * Get the controller's button that is labeled "Back".
     * 
     * @return the button; never null
     */
    public Button getBackButton() {
        return this.backButton;
    }

    /**
     * Get the controller's button that is labeled "Start".
     * 
     * @return the button; never null
     */
    public Button getStartButton() {
        return this.startButton;
    }

    /**
     * Get the controller's button that is labeled "LB".
     * 
     * @return the button; never null
     */
    public Button getLBButton() {
        return this.lbButton;
    }

    /**
     * Get the controller's button that is labeled "RB".
     * 
     * @return the button; never null
     */
    public Button getRBButton() {
        return this.rbButton;
    }

    /**
     * Get the controller's button that is labeled "LT".
     * 
     * @return the button; never null if {@link #getMode() mode} is {@link Mode#D}, or always null if {@link #getMode() mode} is
     *         {@link Mode#X}
     */
    public Button getLTButton() {
        return this.ltButton;
    }

    /**
     * Get the controller's button that is labeled "RT".
     * 
     * @return the button; never null if {@link #getMode() mode} is {@link Mode#D}, or always null if {@link #getMode() mode} is
     *         {@link Mode#X}
     */
    public Button getRTButton() {
        return this.rtButton;
    }

    public double getRawAxis( int axis ) {
        return super.getRawAxis(axis) + axisErrors[axis - 1];
    }

    /**
     * Read all of the axis values to determine the error values, and then record the corrections for each of these axes. This
     * method should be called only when the controller axes are physically in their neutral position.
     */
    public void zeroAxisReadings() {
        for (int i = 1; i <= 6; ++i) {
            axisErrors[i - 1] = -super.getRawAxis(i);
        }
    }

    public String getName() {
        return name;
    }

    public String getSmartDashboardType() {
        return getName();
    }

    public ITable getTable() {
        return this.table;
    }

    public void initTable( ITable table ) {
        this.table = table;
        if (this.table != null) {
            this.table.putString("Mode", this.mode.toString());
            this.table.putString("Style", this.style.toString());
            driveLogic.update(this.table);
        }
    }

    public String toString() {
        return "Logitech Controller \"" + name + "\" (mode=" + mode + ", style=" + style + ")";
    }

    /**
     * An abstraction for the particular kind of drive logic for a specific combination of drive style and controller mode.
     */
    protected abstract class DriveLogic {
        public abstract void drive( RobotDrive robotDrive );

        public abstract void update( ITable table );

        /**
         * Return the {@link LogitechController} instance that owns this logic instance. This is a convenience method for
         * subclasses to call, since the controller is needed in the {@link #drive(RobotDrive)} implementations.
         * 
         * @return the controller instance; never null
         */
        protected final LogitechController ctrl() {
            return LogitechController.this;
        }
    }

    protected final class DModeTankDriveLogic extends DriveLogic {
        public void drive( RobotDrive robotDrive ) {
            double move = ctrl().getRawAxis(DMode.Axes.LEFT_JOYSTICK_VERTICAL);
            double turn = ctrl().getRawAxis(DMode.Axes.RIGHT_JOYSTICK_VERTICAL);
            robotDrive.tankDrive(move, turn, true);
        }

        public void update( ITable table ) {
            table.putNumber("Tank-Left", getRawAxis(DMode.Axes.LEFT_JOYSTICK_VERTICAL));
            table.putNumber("Tank-Right", getRawAxis(DMode.Axes.RIGHT_JOYSTICK_VERTICAL));
            table.putNumber("Arcade-Move", 0.0f);
            table.putNumber("Arcade-Turn", 0.0f);
        }
    }

    protected final class DModeArcadeLeftDriveLogic extends DriveLogic {
        public void drive( RobotDrive robotDrive ) {
            double move = ctrl().getRawAxis(DMode.Axes.LEFT_JOYSTICK_VERTICAL);
            double turn = ctrl().getRawAxis(DMode.Axes.LEFT_JOYSTICK_HORIZONTAL);
            robotDrive.arcadeDrive(move, turn, true);
        }

        public void update( ITable table ) {
            table.putNumber("Tank-Left", 0.0f);
            table.putNumber("Tank-Right", 0.0f);
            table.putNumber("Arcade-Move", getRawAxis(DMode.Axes.LEFT_JOYSTICK_VERTICAL));
            table.putNumber("Arcade-Turn", getRawAxis(DMode.Axes.LEFT_JOYSTICK_HORIZONTAL));
        }
    }

    protected final class DModeArcadeRightDriveLogic extends DriveLogic {
        public void drive( RobotDrive robotDrive ) {
            double move = ctrl().getRawAxis(DMode.Axes.RIGHT_JOYSTICK_VERTICAL);
            double turn = ctrl().getRawAxis(DMode.Axes.RIGHT_JOYSTICK_HORIZONTAL);
            robotDrive.arcadeDrive(move, turn, true);
        }

        public void update( ITable table ) {
            table.putNumber("Tank-Left", 0.0f);
            table.putNumber("Tank-Right", 0.0f);
            table.putNumber("Arcade-Move", getRawAxis(DMode.Axes.LEFT_JOYSTICK_VERTICAL));
            table.putNumber("Arcade-Turn", getRawAxis(DMode.Axes.LEFT_JOYSTICK_HORIZONTAL));
        }
    }

    protected final class XModeTankDriveLogic extends DriveLogic {
        public void drive( RobotDrive robotDrive ) {
            double move = ctrl().getRawAxis(XMode.Axes.LEFT_JOYSTICK_VERTICAL);
            double turn = ctrl().getRawAxis(XMode.Axes.RIGHT_JOYSTICK_VERTICAL);
            robotDrive.tankDrive(move, turn, true);
        }

        public void update( ITable table ) {
            table.putNumber("Tank-Left", getRawAxis(XMode.Axes.LEFT_JOYSTICK_VERTICAL));
            table.putNumber("Tank-Right", getRawAxis(XMode.Axes.RIGHT_JOYSTICK_VERTICAL));
            table.putNumber("Arcade-Move", 0.0f);
            table.putNumber("Arcade-Turn", 0.0f);
        }
    }

    protected final class XModeArcadeLeftDriveLogic extends DriveLogic {
        public void drive( RobotDrive robotDrive ) {
            double move = ctrl().getRawAxis(XMode.Axes.LEFT_JOYSTICK_VERTICAL);
            double turn = ctrl().getRawAxis(XMode.Axes.LEFT_JOYSTICK_HORIZONTAL);
            robotDrive.arcadeDrive(move, turn, true);
        }

        public void update( ITable table ) {
            table.putNumber("Tank-Left", 0.0f);
            table.putNumber("Tank-Right", 0.0f);
            table.putNumber("Arcade-Move", getRawAxis(XMode.Axes.LEFT_JOYSTICK_VERTICAL));
            table.putNumber("Arcade-Turn", getRawAxis(XMode.Axes.LEFT_JOYSTICK_HORIZONTAL));
        }
    }

    protected final class XModeArcadeRightDriveLogic extends DriveLogic {
        public void drive( RobotDrive robotDrive ) {
            double move = ctrl().getRawAxis(XMode.Axes.RIGHT_JOYSTICK_VERTICAL);
            double turn = ctrl().getRawAxis(XMode.Axes.RIGHT_JOYSTICK_HORIZONTAL);
            robotDrive.arcadeDrive(move, turn, true);
        }

        public void update( ITable table ) {
            table.putNumber("Tank-Left", 0.0f);
            table.putNumber("Tank-Right", 0.0f);
            table.putNumber("Arcade-Move", getRawAxis(XMode.Axes.RIGHT_JOYSTICK_VERTICAL));
            table.putNumber("Arcade-Turn", getRawAxis(XMode.Axes.RIGHT_JOYSTICK_HORIZONTAL));
        }
    }

}
