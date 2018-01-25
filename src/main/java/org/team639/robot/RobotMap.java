package org.team639.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import org.team639.lib.sensor.distance.DistanceSensor;
import org.team639.lib.sensor.distance.MaxSonarEZ4Analog;

/**
 * Contains references to all of the motors, sensors, pneumatics, etc. Controls access by the rest of the code from a central location
 */
public class RobotMap {
    private static boolean initialized = false;

    private static TalonSRX leftDrive;
    private static TalonSRX rightDrive;

    private static AHRS ahrs;


    private static TalonSRX leftAcquisition;
    private static TalonSRX rightAcquisition;

    private static DigitalInput cubeDetector;

    private RobotMap() {}

    /**
     * Initializes all of the motors, sensors, etc.
     * THIS MUST BE RUN AT THE BEGINNING OF robotInit in Robot.java!!!
     */
    public static void init() {
        if (!initialized) {
            leftDrive = new TalonSRX(1);
            rightDrive = new TalonSRX(2);
            ahrs = new AHRS(SPI.Port.kMXP);

            leftAcquisition = new TalonSRX(3);
            rightAcquisition = new TalonSRX(4);
            cubeDetector = new DigitalInput(0);

            initialized = true;
        }
    }

    public static TalonSRX getLeftDrive() {
        return leftDrive;
    }

    public static TalonSRX getRightDrive() {
        return rightDrive;
    }

    public static AHRS getAhrs() {
        return ahrs;
    }

    public static TalonSRX getLeftAcquisition() {
        return leftAcquisition;
    }

    public static TalonSRX getRightAcquisition() {
        return rightAcquisition;
    }

    public static DigitalInput getCubeDetector() {
        return cubeDetector;
    }
}
