package org.team639.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import org.team639.lib.sensor.distance.DistanceSensor;
import org.team639.lib.sensor.distance.MaxSonarEZ4Analog;

/**
 * Contains references to all of the motors, sensors, pneumatics, etc. Controls access by the rest of the code from a central location.
 */
public class RobotMap {
    private static boolean initialized = false;

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

    private static MaxSonarEZ4Analog frontSonar;

    // Acquisition
    private static TalonSRX leftAcquisition;
    private static TalonSRX rightAcquisition;
    private static DigitalInput innerCubeDetector;
    private static DigitalInput armsClosed;
    private static Solenoid cubeRaise;
    private static Solenoid acqOpen1;
    private static Solenoid acqOpen2;

    // Lift
    private static TalonSRX liftMain;
    private static TalonSRX liftFollower;
    private static DigitalInput liftLowerLimit;
    private static DigitalInput liftFirstStageLimit;
    private static DigitalInput liftSecondStageLimit;
    private static Solenoid liftLock;

    // Raising subsystem
    private static Solenoid raisingLeft;
    private static Solenoid raisingRight;

    private RobotMap() {}

    /**
     * Initializes all of the motors, sensors, etc.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        if (!initialized) {

            // Left drive
            leftDrive = new TalonSRX(3);
            leftFollower1 = new VictorSPX(4);
            leftFollower2 = new VictorSPX(5);

//            leftFollower1 = new TalonSRX(4);
//            leftFollower2 = new TalonSRX(5);


            // Right drive
            rightDrive = new TalonSRX(0);
            rightFollower1 = new VictorSPX(1);
            rightFollower2 = new VictorSPX(2);
//            rightFollower1 = new TalonSRX(1);
//            rightFollower2 = new TalonSRX(2);

//            driveShifter = new Solenoid(0);

            // NAVX
            ahrs = new AHRS(SPI.Port.kMXP);

            frontSonar = new MaxSonarEZ4Analog(0);

            // Acquisition
            leftAcquisition = new TalonSRX(8);
            rightAcquisition = new TalonSRX(9);
            innerCubeDetector = new DigitalInput(0);
            armsClosed = new DigitalInput(1);
            cubeRaise = new Solenoid(4);
            acqOpen1 = new Solenoid(7);
            acqOpen2 = new Solenoid(0);

            /*
            // Lift
            liftMain = new TalonSRX(6);
            liftFollower = new TalonSRX(7);
            */
//            liftLock = new Solenoid(4);
//            liftLowerLimit = new DigitalInput(2);
//            liftFirstStageLimit = new DigitalInput(3);
//            liftSecondStageLimit = new DigitalInput(4);

            // Raising Subsystem
//            raisingLeft = new Solenoid(5);
//            raisingRight = new Solenoid(6);

            initialized = true;
        }
    }

    /**
     * @return The front sonar
     */
    public static MaxSonarEZ4Analog getFrontSonar() { return frontSonar; }

    /**
     * Returns the left side Talon.
     * @return The left side Talon.
     */
    public static TalonSRX getLeftDrive() {
        return leftDrive;
    }

    /**
     * Returns the first left side Victor.
     * @return the first left side Victor.
     */
    public static IMotorController getLeftFollower1() {
        return leftFollower1;
    }

    /**
     * Returns the second left side Victor.
     * @return the second left side Victor.
     */
    public static IMotorController getLeftFollower2() {
        return leftFollower2;
    }

    /**
     * Returns the right side Talon.
     * @return The right side Talon.
     */
    public static TalonSRX getRightDrive() {
        return rightDrive;
    }

    /**
     * Returns the first right side Victor.
     * @return The first right side Victor.
     */
    public static IMotorController getRightFollower1() {
        return rightFollower1;
    }

    /**
     * Returns the second right side Victor.
     * @return The second right side Victor.
     */
    public static IMotorController getRightFollower2() {
        return rightFollower2;
    }

    /**
     * Returns the solenoid that controls gear shifting on the drivetrain.
     * @return The solenoid that controls gear shifting on the drivetrain.
     */
    public static Solenoid getDriveShifter() {
        return driveShifter;
    }

    /**
     * Returns the navX gyro.
     * @return The navX gyro.
     */
    public static AHRS getAhrs() {
        return ahrs;
    }

    /**
     * Returns the Talon controlling the left side of the acquisition.
     * @return The Talon controlling the left side of the acquisition.
     */
    public static TalonSRX getLeftAcquisition() {
        return leftAcquisition;
    }

    /**
     * Returns the Talon controlling the right side of the acquisition.
     * @return The Talon controlling the right side of the acquisition.
     */
    public static TalonSRX getRightAcquisition() {
        return rightAcquisition;
    }

    /**
     * Returns the IR cube detector on the acquisition.
     * @return The IR cube detector on the acquisition.
     */
    public static DigitalInput getInnerCubeDetector() {
        return innerCubeDetector;
    }

    /**
     * Returns the DigitalInput detecting whether the arms are open or closed.
     * @return The DigitalInput detecting whether the arms are open or closed.
     */
    public static DigitalInput getArmsClosed() {
        return armsClosed;
    }

    /**
     * Returns the main Talon that controls the lift.
     * @return The main Talon that controls the lift.
     */
    public static TalonSRX getLiftMain() {
        return liftMain;
    }

    /**
     * Returns the follower Talon of the lift.
     * @return The follower Talon of the lift.
     */
    public static TalonSRX getLiftFollower() {
        return liftFollower;
    }

    /**
     * Returns the limit switch at the lower limit of the lift.
     * @return The limit switch at the lower limit of the lift.
     */
    public static DigitalInput getLiftLowerLimit() {
        return liftLowerLimit;
    }

    /**
     * Returns the limit switch at the upper limit of the first stage of the lift.
     * @return The limit switch at the upper limit of the first stage of the lift.
     */
    public static DigitalInput getLiftFirstStageLimit() {
        return liftFirstStageLimit;
    }

    /**
     * Returns the limit switch at the upper limit of the second stage of the lift.
     * @return The limit switch at the upper limit of the second stage of the lift.
     */
    public static DigitalInput getLiftSecondStageLimit() {
        return liftSecondStageLimit;
    }

    /**
     * Returns the solenoid which locks the first stage in place.
     * @return The solenoid which locks the first stage in place.
     */
    public static Solenoid getLiftLock() {
        return liftLock;
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

    /**
     * Returns the left piston of the raising subsystem.
     * @return The left piston of the raising subsystem.
     */
    public static Solenoid getRaisingLeft() {
        return raisingLeft;
    }

    /**
     * Returns the right piston of the raising subsystem.
     * @return The right piston of the raising subsystem.
     */
    public static Solenoid getRaisingRight() {
        return raisingRight;
    }
}
