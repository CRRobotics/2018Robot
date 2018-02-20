package org.team639.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.led.LEDColor;
import org.team639.lib.led.patterns.*;
import org.team639.robot.commands.auto.StartingPosition;
import org.team639.robot.commands.drive.DriveMode;
import org.team639.robot.subsystems.CubeAcquisition;
import org.team639.robot.subsystems.DriveTrain;
import org.team639.robot.subsystems.LEDStrip;
import org.team639.robot.subsystems.Lift;

import static org.team639.robot.Constants.*;
import static org.team639.robot.Constants.Auto.*;
import static org.team639.robot.Constants.DriveTrain.*;

/**
 * The main robot class.
 */
public class Robot extends TimedRobot {

    private static double rMax = 0;
    private static double lMax = 0;
    public static double liftMax = 0;

    // Subsystems
    private static DriveTrain driveTrain;
    private static CubeAcquisition cubeAcquisition;
    private static Lift lift;
    private static LEDStrip ledStrip;
    // Driver options
    private static SendableChooser<DriveMode> driveMode;
    private static SendableChooser<ControlMode> driveTalonControlMode;
    private static SendableChooser<StartingPosition> startingPosition;

    public static DriveTrain getDriveTrain() {
        return driveTrain;
    }

    public static CubeAcquisition getCubeAcquisition() {
        return cubeAcquisition;
    }

    public static DriveMode getDriveMode() {
        return driveMode.getSelected();
    }

    public static ControlMode getDriveTalonControlMode() {
        return driveTalonControlMode.getSelected();
    }

    public static StartingPosition getStartingPosition() {
        return startingPosition.getSelected();
    }

    public static Lift getLift() {
        return lift;
    }

    public static LEDStrip getLedStrip() {
        return ledStrip;
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
        // Subsystem initializations
        driveTrain = new DriveTrain();
        cubeAcquisition = new CubeAcquisition();
        lift = new Lift();
        ledStrip = new LEDStrip(42);

        SmartDashboard.putNumber("drive p", HIGH_DRIVE_P);
        SmartDashboard.putNumber("lift max pid", LIFT_MAX);
        SmartDashboard.putNumber("lift min pid", LIFT_MIN);
        SmartDashboard.putNumber("l max", lMax);
        SmartDashboard.putNumber("r max", rMax);

        SmartDashboard.putNumber("rrate", HIGH_ARCADE_RATE);

        // Driver options init
        driveMode = new SendableChooser<>();
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
       // SmartDashboard.putNumber("drive p", LIFT_P);
        SmartDashboard.putNumber("drive i", LIFT_I);
        SmartDashboard.putNumber("drive d", HIGH_DRIVE_D);
        SmartDashboard.putNumber("rate", ADF_RATE);
        SmartDashboard.putNumber("tolerance", LIFT_TOLERANCE);
//        SmartDashboard.putNumber("min", LIFT_CRUISE);
//        SmartDashboard.putNumber("max", ADF_MAX);



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
//        ledStrip.changeMode(new LEDSolid(new LEDColor(200, 0, 0), ledStrip.getLength()));
        /*LEDColor[] arr = {
                new LEDColor(200, 0, 0),
                new LEDColor(200, 0, 0),
//                new LEDColor(0, 200, 0),
//                new LEDColor(0, 200, 0),
                new LEDColor(0, 0, 200),
                new LEDColor(0, 0, 200)
        };
        ledStrip.changeMode(new LEDScrollingSequence(arr, ledStrip.getLength(), 150));*/
        ledStrip.changeMode(new SinePattern(ledStrip.getLength()));
    }

    /**
     * Initialization code for autonomous mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters autonomous mode.
     */
    @Override
    public void autonomousInit() {
        super.autonomousInit();
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

        ledStrip.changeMode(new LEDBlink(new LEDColor(200, 0, 0), ledStrip.getLength(), 500));
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

        SmartDashboard.putNumber("lift pos", lift.getEncPos());
        SmartDashboard.putNumber("pdp energy", RobotMap.getPdp().getTotalEnergy());

        SmartDashboard.putNumber("lift speed", Math.abs(lift.getEncVelocity()));
        SmartDashboard.putNumber("lift velocity", lift.getEncVelocity());

        SmartDashboard.putNumber("lift enc", lift.getEncPos());

        SmartDashboard.putBoolean("drivetrain encoders", driveTrain.encodersPresent());
        SmartDashboard.putNumber("Left speed", driveTrain.getLeftEncVelocity());
        SmartDashboard.putNumber("Right speed", driveTrain.getRightEncVelocity());

        SmartDashboard.putNumber("navx yaw", driveTrain.getRobotYaw());

        SmartDashboard.putBoolean("outer", cubeAcquisition.isCubeDetectedAtFront());
        SmartDashboard.putBoolean("inner", cubeAcquisition.isCubeDetectedAtBack());
        SmartDashboard.putBoolean("arms", cubeAcquisition.isClosed());

        SmartDashboard.putNumber("left enc", driveTrain.getLeftEncPos());
        SmartDashboard.putNumber("right enc", driveTrain.getRightEncPos());

        SmartDashboard.putNumber("Left PercentVBus", RobotMap.getLeftDrive().getMotorOutputPercent());
        SmartDashboard.putNumber("Right PercentVBus", RobotMap.getRightDrive().getMotorOutputPercent());
        SmartDashboard.putString("Current gear", Robot.getDriveTrain().getCurrentGear().name());

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
        super.autonomousPeriodic();
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
