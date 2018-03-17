package org.team639.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import org.team639.lib.LoggingSolenoid;

import static org.team639.robot.Constants.REAL;

/**
 * Contains references to all of the motors, sensors, pneumatics, etc. Controls access by the rest of the code from a central location.
 */
public class RobotMap {
    private static boolean initialized = false;

//    private static PowerDistributionPanel pdp;

    // Left drive
    private static TalonSRX leftDrive;
    private static IMotorController leftFollower1;
    private static IMotorController leftFollower2;

    // Right drive
    private static TalonSRX rightDrive;
    private static IMotorController rightFollower1;
    private static IMotorController rightFollower2;

    // Drive shifter
    private static Solenoid driveShifter;

    // NAVX
    private static AHRS ahrs;

    // Acquisition
    private static SpeedController leftAcquisition;
    private static SpeedController rightAcquisition;
    private static DigitalInput innerCubeDetector;
    private static AnalogInput outerCubeDetector;
    private static DigitalInput armsClosed;
    private static Solenoid cubeRaise;
    private static Solenoid acqOpen1;
    private static Solenoid acqOpen2;

    // Lift
    private static TalonSRX liftMain;
    private static TalonSRX liftFollower;
    private static Solenoid liftBrake;
    private static Solenoid climbPiston;

    private RobotMap() {
    }

    /**
     * Initializes all of the motors, sensors, etc.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        if (!initialized) {

//            pdp = new PowerDistributionPanel(0);

            // Left drive
            leftDrive = new TalonSRX(3);
            rightDrive = new TalonSRX(0);

            if (REAL) {
                leftFollower1 = new VictorSPX(4);
                leftFollower2 = new VictorSPX(5);

                rightFollower1 = new VictorSPX(1);
                rightFollower2 = new VictorSPX(2);
            } else {
                leftFollower1 = new TalonSRX(4);
                leftFollower2 = new TalonSRX(5);

                rightFollower1 = new TalonSRX(1);
                rightFollower2 = new TalonSRX(2);
            }

            // Right drive

            driveShifter = new Solenoid(5);

            // NAVX
            ahrs = new AHRS(SPI.Port.kMXP);

            // Acquisition
            if (REAL) {
                leftAcquisition = new WPI_TalonSRX(8);
                rightAcquisition = new WPI_TalonSRX(9);
            } else {
                leftAcquisition = new Spark(0);
                rightAcquisition = new Spark(1);
            }
            innerCubeDetector = new DigitalInput(0);
            outerCubeDetector = new AnalogInput(0);
            armsClosed = new DigitalInput(2);
            cubeRaise = new LoggingSolenoid(1);
            acqOpen1 = new LoggingSolenoid(3);
            acqOpen2 = new LoggingSolenoid(4);

            // Lift
            liftMain = new TalonSRX(6);
            liftFollower = new TalonSRX(7);

            climbPiston = new LoggingSolenoid(2);
            liftBrake = new LoggingSolenoid(6);

            initialized = true;
        }
    }

    /**
     * Returns the left side Talon.
     *
     * @return The left side Talon.
     */
    public static TalonSRX getLeftDrive() {
        return leftDrive;
    }

    /**
     * Returns the first left side Victor.
     *
     * @return the first left side Victor.
     */
    public static IMotorController getLeftFollower1() {
        return leftFollower1;
    }

    /**
     * Returns the second left side Victor.
     *
     * @return the second left side Victor.
     */
    public static IMotorController getLeftFollower2() {
        return leftFollower2;
    }

    /**
     * Returns the right side Talon.
     *
     * @return The right side Talon.
     */
    public static TalonSRX getRightDrive() {
        return rightDrive;
    }

    /**
     * Returns the first right side Victor.
     *
     * @return The first right side Victor.
     */
    public static IMotorController getRightFollower1() {
        return rightFollower1;
    }

    /**
     * Returns the second right side Victor.
     *
     * @return The second right side Victor.
     */
    public static IMotorController getRightFollower2() {
        return rightFollower2;
    }

    /**
     * Returns the solenoid that controls gear shifting on the drivetrain.
     *
     * @return The solenoid that controls gear shifting on the drivetrain.
     */
    public static Solenoid getDriveShifter() {
        return driveShifter;
    }

    /**
     * Returns the navX gyro.
     *
     * @return The navX gyro.
     */
    public static AHRS getAhrs() {
        return ahrs;
    }

    /**
     * Returns the Talon controlling the left side of the acquisition.
     *
     * @return The Talon controlling the left side of the acquisition.
     */
    public static SpeedController getLeftAcquisition() {
        return leftAcquisition;
    }

    /**
     * Returns the Talon controlling the right side of the acquisition.
     *
     * @return The Talon controlling the right side of the acquisition.
     */
    public static SpeedController getRightAcquisition() {
        return rightAcquisition;
    }

    /**
     * Returns the IR cube detector on the acquisition.
     *
     * @return The IR cube detector on the acquisition.
     */
    public static DigitalInput getInnerCubeDetector() {
        return innerCubeDetector;
    }

    /**
     * Returns the outer IR cube detector on the acquisition.
     *
     * @return The outer IR cube detector on the acquisition.
     */
    public static AnalogInput getOuterCubeDetector() {
        return outerCubeDetector;
    }

    /**
     * Returns the DigitalInput detecting whether the arms are open or closed.
     *
     * @return The DigitalInput detecting whether the arms are open or closed.
     */
    public static DigitalInput getArmsClosed() {
        return armsClosed;
    }

    /**
     * Returns the main Talon that controls the lift.
     *
     * @return The main Talon that controls the lift.
     */
    public static TalonSRX getLiftMain() {
        return liftMain;
    }

    /**
     * Returns the follower Talon of the lift.
     *
     * @return The follower Talon of the lift.
     */
    public static TalonSRX getLiftFollower() {
        return liftFollower;
    }

    /**
     * Returns the solenoid which locks the first stage in place.
     *
     * @return The solenoid which locks the first stage in place.
     */
    public static Solenoid getLiftBrake() {
        return liftBrake;
    }

    public static Solenoid getCubeRaise() {
        return cubeRaise;
    }

    public static Solenoid getAcqOpen1() {
        return acqOpen1;
    }

    public static Solenoid getAcqOpen2() {
        return acqOpen2;
    }

    public static Solenoid getClimbPiston() {
        return climbPiston;
    }
}