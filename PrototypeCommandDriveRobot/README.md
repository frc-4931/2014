This is a very basic program for a robot with two motors, one for the left wheels and one for the right wheels.
The robot is controlled using an arcade-style single joystick: forward, reverse, turn left, turn right.
The joystick position also controls the variable-speed motors, though buttons on the joystick can increase
or decrease the maximum speed.

This program uses the [command based programming](http://wpilib.screenstepslive.com/s/3120/m/7952/l/105519-what-is-command-based-programming) technique of the [WPILib library](http://wpilib.screenstepslive.com/s/3120/m/7912). All of the Java code is within the `org.frc4931.prototype` package, and consists of just a few classes.

### `org.frc4931.prototype.Robot`

The [Robot.java](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/Robot.java) is the class represents the robot and it subclasses the `edu.wpi.first.wpilibj.IterativeRobot` in the WPILib. This means there are methods that will be called when the robot is put in each of the various modes:

* **Tele-operation mode** - the robot is directly controlled by the Drivers Station and the physical interfaces (e.g., joysticks, buttons, etc.)
* **Autonomous mode** - the robot runs a pre-programmed sequence of steps. At this time, this prototype simply drives forward at 50% power for 3 seconds, pauses for 5 seconds, and drives backward at 50% power for 3 seconds.
* **Test mode** - the robot runs a pre-programmed series of tests. When each test is run, a description of it will be written to the Driver Station.
* **Disabled mode** - the robot is disabled.

The _`mode`_`Init()` methods are run once when the robot is put into that mode, while the _`mode`_`Periodic()` methods are called repeatedly (e.g., every 0.02 seconds) while the robot remains in that mode.

The class also defines several other important things:

* Constants for the drive joystick and its input ports and buttons used in the program.
* Constants for the drive motors and its output ports and flags for directional reversal and speed constants.
* The static, singleton `OperatorInterface` instance.
* The static, singleton subsystem instances. Currently there is only the `DriveTrain` instance.
* The `Command` or `CommandGroup` instance that contains the sequence of commands that should be run in autonomous mode.
* The `Command` or `CommandGroup` instance that contains the sequence of commands that should be run in test mode.

### `org.frc4931.prototype.OperatorInterface`

The [OperatorInterface.java](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/OperatorInterface.java) class serves one purpose: register a particular `Command` instance with each input. Commands can be registered when buttons are pressed, when they are released, while they remain pressed, etc. At this time, this prototype robot registers commands for these buttons:

* Button 3 _increases_ the maximum speed of the robot by 5%, up to 100%. This is done by registering the `IncreaseMaxDriveSpeed` command with this button.
* Button 4 _decreases_ the maximum speed of the robot by 5%, down to 0%. This is done by registering the `DecreaseMaxDriveSpeed` command with this button. Note that decreasing the maximum speed is useful when needing to accurately position the robot, since decreasing the maximum speed effectively increases the sensitivity of the drive joystick.
* Button 5 toggles whether the robot's debug (verbose) messages are output to the Driver Station console. This is done by registering the `VerboseOutputToggle` command with this button.

The `Robot` class is responsible for creating the single (static) `OperatorInterface` instance.

### `org.frc4931.prototype.subsystem` package

This package contains a class for each kind of controllable "subsystem" on the robot. Each subsystem will expose methods that control the subsystem, but how this is done is encapsulate within the subsystem. In other words, the purpose of each subsystem class is to "abstract" or hide the complexity of certain components on the robot that are used together.

Each `Command` class that uses a specific subsystem must call in its contructor the `require(...)` method and pass in the subsystem. When the command classes are then used, the 

#### DriveTrain

The [DriveTrain.java](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/subsystem/DriveTrain.java) class represents the motors that are used to drive the robot. It subclasses (extends) the WPILib's `Subsystem` class, which contains most of the functionality. Currently it contains (encapsulates):

* A `Jaguar` object representing the Jaguar motor controller for the left drive motor
* A `Jaguar` object representing the Jaguar motor controller for the right drive motor
* A `RobotDrive` object that is the control logic that transforms basic and easy-to-understand "drive" method calls into explicit changes in the left and right motors.
* A `speedFactor` double value that ranges between 0.0 and 1.0, and that limits the amount of power sent to the motors.
* Methods for manipulating the drive train directly from `Command` classes.

### `org.frc4931.prototype.command` package

This package contains the robot-specific "commands". These make use of the [command style programming](http://wpilib.screenstepslive.com/s/3120/m/7952/l/105519-what-is-command-based-programming) made possible with the WPILib library.

A **_command_** is a Java class that extends the `Command` class and that represents an atomic operation that you might want to perform based upon operator inputs (e.g., press a button or move a joystick). Many `Command` classes are simple, while others are groups of other commands. Each command will operate against one or more **_subsystms_**.

Multiple commands can also be placed into a **_command group_**, which is a Java class that extends the `CommandGroup` class and that creates a series of other command objects that all will be used together. Note that even when a command is used within a command group, that command can also be used elsewhere.

Thus, commands provide a way of defining small reusable actions that can be used on their own and/or assembled into larger groups of reusable actions. As mentioned above, some `Command` objects will be bound to the operator interface (e.g., joystick, buttons, etc.); in this case, when the operator interface is used (e.g., a button is pressed), the associated `Command` object is run.

Interestingly, the robot's autonomous mode is really just running a single `Command` (or rather a large group of other commands); see the `autonomousCommand` static field in the [Robot.java](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/Robot.java) class. The `autonomousCommand` instance is run whenever the robot is put into autonomous mode.

The great thing about the command framework is that "running" a command really means "schedule the command for execution". You see, the robot has a "scheduler" that operates in a loop: every 0.02 seconds (or so) it checks to see which commands "need to be run" and which subsystems they need, and it figures out when and in what order to execute each of these scheduled commands during that 0.02 second period. Some commands will complete immediately, while other do not complete immediately and instead are simply rescheduled to be executed again. The net effect is that, to someone watching the robot, the robot can appear to be doing multiple things at one (in parallel), even though all of the `Command` classes are written simple units of work.

Thus, the command framework is actually very powerful and has several benefits:

1. Each `Command` class encapsulates a single simple but specific action, yet is a surprisingly small amount of code.
1. A `CommandGroup` class contains multiple `Command` or other `CommandGroup` instances that are run serially, in parallel, or in a combination of series and parallel. This makes it easy to build up functionality from smaller commands.
1. Each `Command` classese can be bound to operator input as well as used in autonomous and test modes. Thus, it is very easy to build up a series of commands or command groups, and try them individually in tele-op mode before putting them into other `CommandGroups`.
1. Authonomus mode is simply a single `Command` (or more likely a single `CommandGroup`).
1. Test mode is simply a single `Command` (or likely a single `CommandGroup`).
1. The "scheduling" functionality allows multiple commands to be run in parallel. For example, the robot might be driving in response to the joystick, an arm might be raising to 45 degree elevation, and a claw might be opening -- all at the same time.

There are currently a number of `Command` subclasses in this package:

* [ArcadeDriveWithJoystick](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/ArcadeDriveWithJoystick.java) - A command that is used to drive the chassis based upon real-time inputs coming from the single arcade-style joystick. This is created as the default command in the [DriveTrain](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/subsystem/DriveTrain.java) subsystem.
* [DecreaseMaxDriveSpeed](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/DecreaseMaxDriveSpeed.java) - A command that when run decreases by 5% the maximum speed of the drive motors.
* [IncreaseMaxDriveSpeed](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/IncreaseMaxDriveSpeed.java) - A command that when run increases by 5% the maximum speed of the drive motors.
* [DriveAtSpeedForTime](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/DriveAtSpeedForTime.java) - A command that when run drives the robot forward at a speed and for a number of seconds specified in the constructor.
* [VerboseOutputSet](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/VerboseOutputSet.java) - A command that when run uses a boolean passed into the constructor to set whether the robot code outputs "debug" messages to the Drive Station console.
* [VerboseOutputToggle](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/VerboseOutputToggle.java) - A command that when run toggles whether the robot code outputs "debug" messages to the Drive Station console.
* [WaitCommand](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/WaitCommand.java) - A command that when run simply waits for the number of seconds passed into the constructor.

There are currently several `CommandGroup` subclasses in this package:

* [DriveForwardAndBackward](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/ArcadeDriveWithJoystick.java) - A command that when run drives the robot forward at 50% speed for 3 seconds, pauses for 5 seconds, and drives the robot backward at 50% speed for 3 seconds. This command simpley reuses a [DriveAtSpeedForTime](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/DriveAtSpeedForTime.java) to drive forward, one [WaitCommand](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/WaitCommand.java), and another [DriveAtSpeedForTime](https://github.com/frc-4931/2014/blob/master/PrototypeCommandDriveRobot/src/org/frc4931/prototype/command/DriveAtSpeedForTime.java) to drive backward.
* [RunTests]() - A command that operates several of motors for short periods of time. Again, this is just a group of other `Command` instances.




