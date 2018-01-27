package org.team639.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.commands.drive.DriveMode;
import org.team639.robot.subsystems.CubeAcquisition;
import org.team639.robot.subsystems.DriveTrain;

/**
 * The main robot class.
 */
public class Robot extends TimedRobot {

    // Subsystems
    private static DriveTrain driveTrain;
    private static CubeAcquisition cubeAcquisition;

    // Driver options
    private static SendableChooser<DriveMode> driveMode;

    public static DriveTrain getDriveTrain() {
        return driveTrain;
    }

    public static CubeAcquisition getCubeAcquisition() {
        return cubeAcquisition;
    }

    public static DriveMode getDriveMode() {
        return driveMode.getSelected();
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

        // Driver options init
        driveMode = new SendableChooser<>();
        driveMode.addDefault("1 Joystick Arcade", DriveMode.Aracde1Joystick);
        driveMode.addObject("Tank", DriveMode.Tank);
        driveMode.addObject("Field Oriented 1 joystick", DriveMode.Field1Joystick);
        driveMode.addObject("Field Oriented 2 joysticks", DriveMode.Field2Joystick);
        driveMode.addObject("2 Joystick Arcade", DriveMode.Arcade2Joystick);
        SmartDashboard.putData("Drive Mode", driveMode);

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
        super.disabledInit();
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
        super.teleopInit();
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
        super.robotPeriodic();
    }

    /**
     * Periodic code for disabled mode should go here.
     */
    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
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
        super.teleopPeriodic();
    }

    /**
     * Periodic code for test mode should go here.
     */
    @Override
    public void testPeriodic() {
        super.testPeriodic();
    }
}
