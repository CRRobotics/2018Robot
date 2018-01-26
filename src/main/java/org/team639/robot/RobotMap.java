package org.team639.robot;

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
    private static VictorSPX leftFollower1;
    private static VictorSPX leftFollower2;

    // Right drive
    private static TalonSRX rightDrive;
    private static VictorSPX rightFollower1;
    private static VictorSPX rightFollower2;

    // NAVX
    private static AHRS ahrs;

    // Acquisition
    private static TalonSRX leftAcquisition;
    private static TalonSRX rightAcquisition;
    private static DigitalInput cubeDetector;

    // Lift
    private static TalonSRX liftMain;
    private static TalonSRX liftFollower;

    private RobotMap() {}

    /**
     * Initializes all of the motors, sensors, etc.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        if (!initialized) {
            // Left drive
            leftDrive = new TalonSRX(0);
            leftFollower1 = new VictorSPX(1);
            leftFollower2 = new VictorSPX(2);

            // Right drive
            rightDrive = new TalonSRX(3);
            rightFollower1 = new VictorSPX(4);
            rightFollower2 = new VictorSPX(5);

            // NAVX
            ahrs = new AHRS(SPI.Port.kMXP);

            // Acquisition
            leftAcquisition = new TalonSRX(8);
            rightAcquisition = new TalonSRX(9);
            cubeDetector = new DigitalInput(0);

            // Lift
            liftMain = new TalonSRX(6);
            liftFollower = new TalonSRX(7);

            initialized = true;
        }
    }

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
    public static VictorSPX getLeftFollower1() {
        return leftFollower1;
    }

    /**
     * Returns the second left side Victor.
     * @return the second left side Victor.
     */
    public static VictorSPX getLeftFollower2() {
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
    public static VictorSPX getRightFollower1() {
        return rightFollower1;
    }

    /**
     * Returns the second right side Victor.
     * @return The second right side Victor.
     */
    public static VictorSPX getRightFollower2() {
        return rightFollower2;
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
    public static DigitalInput getCubeDetector() {
        return cubeDetector;
    }
}
