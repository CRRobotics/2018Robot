package org.team639.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;

/**
 * The cube acquisition subsystem.
 * Responsible for manipulating cubes.
 */
public class CubeAcquisition extends Subsystem {

    private TalonSRX left;
    private TalonSRX right;

    private DigitalInput cubeDetector;

    private PistonMode mode;

    /**
     * The three modes the piston that opens the acquisition can be in.
     */
    public enum PistonMode {
        Open,
        Closed,
        Floating
    }

    public CubeAcquisition() {
        left = RobotMap.getLeftAcquisition();
        right = RobotMap.getRightAcquisition();

        cubeDetector = RobotMap.getCubeDetector();

        mode = PistonMode.Closed;
        setPistonMode(mode);
    }

    /**
     * Change the current mode of the piston that opens and closes the acquisition.
     * @param mode The new mode for the piston.
     */
    public void setPistonMode(PistonMode mode) {
        this.mode = mode;
        // TODO: actually change the piston
    }

    /**
     * Returns the current mode of the piston.
     * @return The current mode of the piston.
     */
    public PistonMode getPistonMode() {
        return mode;
    }

    /**
     * Raises or lowers the acquisition.
     * @param raised Whether or not the acquisition should be raised.
     */
    public void setRaised(boolean raised) {
        // TODO: actually move piston
    }

    /**
     * Returns whether or not the acquisition is raised.
     * @return Whether or not the aqcuisition is raised.
     */
    public boolean isRaised() {
        // TODO: actually check if raised
        return false;
    }

    /**
     * Changes the speeds of the acquisition wheels. Negative values to intake and positive to eject.
     * @param lSpeed The percentage of max speed to set the left side.
     * @param rSpeed The percentage of max speed to set the right side.
     */
    public void setSpeedsPercent(double lSpeed, double rSpeed) {
        // TODO: actually spin the wheels
    }

    /**
     * Returns whether or not a cube is in the acquisition.
     * @return Whether or not a cube is in the acquisition.
     */
    public boolean isCubeDetected() {
        return cubeDetector.get();
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {}
}
