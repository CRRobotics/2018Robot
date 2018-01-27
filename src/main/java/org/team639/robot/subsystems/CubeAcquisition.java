package org.team639.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;

/**
 * The cube acquisition subsystem.
 * Responsible for manipulating cubes.
 */
public class CubeAcquisition extends Subsystem {
    private TalonSRX left;
    private TalonSRX right;

    private DigitalInput innerCubeDetector;
    private DigitalInput armsClosed;

    private Solenoid cubeRaise;
    private Solenoid acqOpen1;
    private Solenoid acqOpen2;

    private PistonMode mode;

    /**
     * The three modes the piston that opens the acquisition can be in.
     */
    public enum PistonMode {
        Open,
        Closed,
        Floating
    }

    /**
     * Creates a new CubeAcquisition.
     */
    public CubeAcquisition() {
        left = RobotMap.getLeftAcquisition();
        right = RobotMap.getRightAcquisition();

        right.setInverted(true);

        innerCubeDetector = RobotMap.getInnerCubeDetector();
        armsClosed = RobotMap.getArmsClosed();

        cubeRaise = RobotMap.getCubeRaise();
        acqOpen1 = RobotMap.getAcqOpen1();
        acqOpen2 = RobotMap.getAcqOpen2();

        mode = PistonMode.Closed;
        setPistonMode(mode);
    }

    /**
     * Change the current mode of the piston that opens and closes the acquisition.
     * @param mode The new mode for the piston.
     */
    public void setPistonMode(PistonMode mode) {
        this.mode = mode;
        // TODO: Check that the piston changes correctly
        switch (this.mode) {
            case Open:
                acqOpen1.set(true);
                acqOpen2.set(false);
                break;
            case Closed:
                acqOpen1.set(false);
                acqOpen2.set(true);
                break;
            case Floating:
                acqOpen1.set(false);
                acqOpen2.set(false);
                break;
        }
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
        cubeRaise.set(!raised); // TODO: Check that this is correct.
    }

    /**
     * Returns whether or not the acquisition is raised.
     * @return Whether or not the aqcuisition is raised.
     */
    public boolean isRaised() {
        return !cubeRaise.get(); // TODO: Check that this is correct.
    }

    /**
     * Changes the speeds of the acquisition wheels. Negative values to intake and positive to eject.
     * @param lSpeed The percentage of max speed to set the left side.
     * @param rSpeed The percentage of max speed to set the right side.
     */
    public void setSpeedsPercent(double lSpeed, double rSpeed) {
        left.set(ControlMode.Current, lSpeed);
        right.set(ControlMode.Current, rSpeed);
    }

    /**
     * Returns whether or not a cube is at the back of the acquisition.
     * @return Whether or not a cube is at the back of the acquisition.
     */
    public boolean isCubeDetectedAtBack() {
        return innerCubeDetector.get();
    }

    /**
     * Returns whether or not the arms are closed far enough for the cube to be positioned correctly.
     * @return Whether or not the arms are closed far enough for the cube to be positioned correctly.
     */
    public boolean isClosed() {
        return armsClosed.get();
    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {}
}
