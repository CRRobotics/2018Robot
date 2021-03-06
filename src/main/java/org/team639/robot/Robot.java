package org.team639.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.led.LEDColor;
import org.team639.lib.led.patterns.*;
import org.team639.robot.commands.auto.*;
import org.team639.robot.commands.drive.DriveMode;
import org.team639.robot.commands.drive.fancyauto.DriveTracker;
import org.team639.robot.commands.lift.ZeroLift;
import org.team639.robot.subsystems.CubeAcquisition;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.LEDStrip;
import org.team639.robot.subsystems.Lift;

import static org.team639.robot.Constants.DriveTrain.HIGH_DRIVE_D;
import static org.team639.robot.Constants.DriveTrain.HIGH_DRIVE_I;
import static org.team639.robot.Constants.DriveTrain.HIGH_DRIVE_P;

/**
 * The main robot class.
 */
public class Robot extends TimedRobot {

    private static double rMax = 0;
    private static double lMax = 0;
    private static double liftMax = 0;

    private Command auto; // The auto period (very important)

    // Subsystems
    private static DriveTrain driveTrain;
    private static CubeAcquisition cubeAcquisition;
    private static Lift lift;
    private static LEDStrip ledStrip;

    private static DriveTracker driveTracker;

    // Driver options
    private static SendableChooser<DriveMode> driveMode;
    private static SendableChooser<ControlMode> driveTalonControlMode;
    private static SendableChooser<StartingPosition> startingPosition;
    private static SendableChooser<Class<? extends Command>> autoSelector;

    // LEDs
    private static SinePattern defaultPattern;

    public static SinePattern getDefaultPattern() { return defaultPattern; }

    /**
     * Returns a reference to the robot's drivetrain.
     * @return a reference to the robot's drivetrain.
     */
    public static DriveTrain getDriveTrain() {
        return driveTrain;
    }

    /**
     * Returns a reference to the robot's cube acquisition system.
     * @return a reference to the robot's cube acquisition system.
     */
    public static CubeAcquisition getCubeAcquisition() {
        return cubeAcquisition;
    }

    /**
     * Returns the drive mode selected by the driver on shuffleboard.
     * @return The drive mode selected by the driver on shuffleboard.
     */
    public static DriveMode getDriveMode() {
        return driveMode.getSelected();
    }

    public static ControlMode getDriveTalonControlMode() {
        return driveTalonControlMode.getSelected();
    }

    /**
     * Returns the starting position of the robot, specified on shuffleboard by the drive team.
     * @return The starting position of the robot, specified on shuffleboard by the drive team.
     */
    public static StartingPosition getStartingPosition() {
        return startingPosition.getSelected();
    }

    /**
     * Returns a reference to the robot's lift.
     * @return a reference to the robot's lift.
     */
    public static Lift getLift() {
        return lift;
    }

    /**
     * Returns a reference to the robot's LED strip.
     * @return a reference to the robot's LED strip.
     */
    public static LEDStrip getLedStrip() {
        return ledStrip;
    }

    /**
     * Returns the approximate x location of the robot reported by the drive tracker.
     * @return The approximate x location of the robot reported by the drive tracker.
     */
    public static double getTrackedX() {
        return driveTracker.getX();
    }

    /**
     * Returns the approximate y location of the robot reported by the drive tracker.
     * @return The approximate y location of the robot reported by the drive tracker.
     */
    public static double getTrackedY() {
        return driveTracker.getY();
    }

    /**
     * Resets the drive tracker to the specified coordinates.
     * @param x The x value to reset to.
     * @param y The y value to reset to.
     */
    public static void resetTrackedPosition(double x, double y) {
        driveTracker.reset(x, y);
    }


    /**
     * Robot-wide initialization code should go here.
     * <p>
     * <p>Users should override this method for default Robot-wide initialization which will be called
     * when the robot is first powered on. It will be called exactly one time.
     * <p>
     * <p>Warning: the Driver Station "Robot Code" light and FMS "Robot Ready" indicators will be off
     * until RobotInit() exits. Code in RobotInit() that waits for enable will cause the robot to
     * never indicate that the code is ready, causing the robot to be bypassed in a match.
     */
    @Override
    public void robotInit() {
        RobotMap.init(); // Initialize all sensors, motors, etc.
//
//        VideoSource c = CameraServer.getInstance().startAutomaticCapture();
//        c.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 30);

        SmartDashboard.putNumber("drive p", HIGH_DRIVE_P);
        SmartDashboard.putNumber("drive i", HIGH_DRIVE_I);
        SmartDashboard.putNumber("drive d", HIGH_DRIVE_D);

        // Subsystem initializations
        driveTrain = new DriveTrain();
        cubeAcquisition = new CubeAcquisition();
        lift = new Lift();
        ledStrip = new LEDStrip(84);
        defaultPattern = new SinePattern(ledStrip.getLength());

        driveTracker = new DriveTracker(0, 0);

        // Driver options init
        driveMode = new SendableChooser<>(  );
        driveMode.addDefault("2 Joystick Arcade Right", DriveMode.Arcade2JoystickRightDrive);
        driveMode.addObject("1 Joystick Arcade", DriveMode.Arcade1Joystick);
        driveMode.addObject("Tank", DriveMode.Tank);
        driveMode.addObject("Field Oriented 1 joystick", DriveMode.Field1Joystick);
        driveMode.addObject("Field Oriented 2 joysticks", DriveMode.Field2Joystick);
        driveMode.addObject("2 Joystick Arcade Left", DriveMode.Arcade2JoystickLeftDrive);
        SmartDashboard.putData("Drive Mode", driveMode);

        driveTalonControlMode = new SendableChooser<>();
        driveTalonControlMode.addDefault("Closed loop", ControlMode.Velocity);
        driveTalonControlMode.addObject("Open loop", ControlMode.PercentOutput);
        SmartDashboard.putData("Control mode", driveTalonControlMode);

        startingPosition = new SendableChooser<>();
        startingPosition.addDefault("Center", StartingPosition.Center);
        startingPosition.addObject("Left", StartingPosition.Left);
        startingPosition.addObject("Right", StartingPosition.Right);
        SmartDashboard.putData("Starting position", startingPosition);

        SmartDashboard.putNumber("Auto delay", 0);
        autoSelector = new SendableChooser<>();
        autoSelector.addDefault("Drive over line", AutoCrossLine.class); // Passing class types to be instantiated later.
        autoSelector.addObject("One Cube Switch", OneCubeSwitch.class);
        autoSelector.addObject("One Cube Scale", OneCubeScale.class);
        autoSelector.addObject("50/50 Switch", AutoSwitchSideGuess.class);
        autoSelector.addObject("50/50 switch 50/50 scale", SwitchChanceThenScaleChance.class);
        autoSelector.addObject("50/50 scale", ScaleChance.class);
        autoSelector.addObject("Scale/switch chance", ScaleThenSwitchChance.class);
        autoSelector.addObject("ScaleFromSide", ScaleFromSide.class);
        autoSelector.addObject("side switch chance", SideSwitchChance.class);
        SmartDashboard.putData("Auto selector", autoSelector);

        SmartDashboard.putData("Lower lift", new ZeroLift());

        OI.mapButtons(); // Map all of the buttons on the controller(s)
    }

    /**
     * Initialization code for disabled mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters disabled mode.
     */
    @Override
    public void disabledInit() {
        RobotMap.getClimbPiston().set(false);
//        ledStrip.changeMode(new LEDSolid(new LEDColor(200, 0, 0), ledStrip.getLength()));
        LEDColor[] arr = {
                new LEDColor(200, 0, 0),
                new LEDColor(200, 0, 0),
//                new LEDColor(0, 200, 0),
//                new LEDColor(0, 200, 0),
                new LEDColor(0, 0, 200),
                new LEDColor(0, 0, 200)
        };
        ledStrip.changeMode(new LEDScrollingSequence(arr, ledStrip.getLength(), 150));
//        ledStrip.changeMode(new SinePattern(ledStrip.getLength()));
    }

    /**
     * Initialization code for autonomous mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters autonomous mode.
     */
    @Override
    public void autonomousInit() {
        StartingPosition position = startingPosition.getSelected();
        driveTracker.reset(position.x, position.y);
        try { // This try/catch is for the call to Class<? extends Command>.newInstance that constructs the auto (hopefully).
            auto = new AutoBoilerplate(autoSelector.getSelected().newInstance(), SmartDashboard.getNumber("Auto delay", 0));
        } catch (InstantiationException e) {
            e.printStackTrace();
            auto = new AutoCrossLine();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            auto = new AutoCrossLine();
        }
        auto.start();
        driveTrain.setAutoShift(false);
    }

    /**
     * Initialization code for teleop mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters teleop mode.
     */
    @Override
    public void teleopInit() {
        driveTrain.setNeutralMode(NeutralMode.Brake);
        driveTrain.setAutoShift(true);

        if (auto != null) auto.cancel(); // Stop the auto

//        ledStrip.changeMode(new LEDSolid(new LEDColor(200, 0, 0), ledStrip.getLength()));
        ledStrip.changeMode(defaultPattern);
//        ledStrip.changeMode(new LEDBlink(new LEDColor(200, 0, 0), ledStrip.getLength(), 500));
//        ledStrip.changeMode(new LEDBatteryPercent(ledStrip.getLength()));
//        ledStrip.changeMode(new LEDVelocityLighting(ledStrip.getLength(), (int)HIGH_SPEED_RANGE, () -> driveTrain.getLeftEncVelocity()));
    }

    /**
     * Initialization code for test mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters test mode.
     */
    @Override
    public void testInit() {
        super.testInit();
    }

    /**
     * Periodic code for all robot modes should go here.
     */
    @Override
    public void robotPeriodic() {
        driveTracker.collect();

        SmartDashboard.putNumber("x pos", driveTracker.getX());
        SmartDashboard.putNumber("y pos", driveTracker.getY());

        SmartDashboard.putString("Selected auto mode", autoSelector.getSelected().getSimpleName());

//        SmartDashboard.putNumber("lift pos", lift.getEncPos());
//        SmartDashboard.putNumber("pdp energy", RobotMap.getPdp().getTotalEnergy());

//        SmartDashboard.putNumber("lift speed", Math.abs(lift.getEncVelocity()));
//        SmartDashboard.putNumber("lift velocity", lift.getEncVelocity());

//        SmartDashboard.putNumber("lift enc", lift.getEncPos());

        SmartDashboard.putBoolean("drivetrain encoders", driveTrain.encodersPresent());
        SmartDashboard.putNumber("Left speed", driveTrain.getLeftEncVelocity());
        SmartDashboard.putNumber("Right speed", driveTrain.getRightEncVelocity());

        SmartDashboard.putNumber("navx yaw", driveTrain.getRobotYaw());

        SmartDashboard.putBoolean("outer", cubeAcquisition.isCubeDetectedAtFront());
        SmartDashboard.putBoolean("inner", cubeAcquisition.isCubeDetectedAtBack());
        SmartDashboard.putBoolean("arms", cubeAcquisition.isClosed());

//        SmartDashboard.putNumber("left enc", driveTrain.getLeftEncPos());
//        SmartDashboard.putNumber("right enc", driveTrain.getRightEncPos());

        double r = driveTrain.getRightEncVelocity();
        double l = driveTrain.getLeftEncVelocity();
        if (r > rMax) {
            rMax = r;
            SmartDashboard.putNumber("r max", rMax);
        }

        if (l > lMax) {
            lMax = l;
            SmartDashboard.putNumber("l max", lMax);
        }
        double lf = lift.getEncVelocity();
        if (lf > liftMax) {
            liftMax = lf;
            SmartDashboard.putNumber("lift max", liftMax);
        }
    }

    /**
     * Periodic code for disabled mode should go here.
     */
    @Override
    public void disabledPeriodic() {
        ledStrip.update();
    }

    /**
     * Periodic code for autonomous mode should go here.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Periodic code for teleop mode should go here.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Periodic code for test mode should go here.
     */
    @Override
    public void testPeriodic() {
        super.testPeriodic();
    }
}
