package org.team639.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;
import org.team639.robot.commands.cube.MonitorCube;

/**
 * The cube acquisition subsystem.
 * Responsible for manipulating cubes.
 * The best subsystem.
 */
public class CubeAcquisition extends Subsystem {
    private SpeedController left;
    private SpeedController right;

    private DigitalInput innerCubeDetector;
    private DigitalInput outerCubeDetector;
    private DigitalInput armsClosed;

    private boolean innerDetectorEnabled = true;
    private boolean outerDetectorEnabled = true;
    private boolean armsClosedEnabled = true;

    private boolean shouldHaveCube = false;

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

        left.setInverted(true);

        innerCubeDetector = RobotMap.getInnerCubeDetector();
        outerCubeDetector = RobotMap.getOuterCubeDetector();
        armsClosed = RobotMap.getArmsClosed();

        cubeRaise = RobotMap.getCubeRaise();
        acqOpen1 = RobotMap.getAcqOpen1();
        acqOpen2 = RobotMap.getAcqOpen2();

        mode = PistonMode.Floating;
        setPistonMode(mode);

        setRaised(true);
    }

    /**
     * Change the current mode of the piston that opens and closes the acquisition.
     * @param mode The new mode for the piston.
     */
    public void setPistonMode(PistonMode mode) {
        this.mode = mode;
        switch (this.mode) {
            case Open:
                acqOpen1.set(false);
                acqOpen2.set(true);
                break;
            case Closed:
                acqOpen1.set(true);
                acqOpen2.set(false);
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
        cubeRaise.set(!raised);
    }

    /**
     * Returns whether or not the acquisition is raised.
     * @return Whether or not the aqcuisition is raised.
     */
    public boolean isRaised() {
        return !cubeRaise.get();
    }

    /**
     * Changes the speeds of the acquisition wheels. Negative values to intake and positive to eject.
     * @param lSpeed The percentage of max speed to set the left side.
     * @param rSpeed The percentage of max speed to set the right side.
     */
    public void setSpeedsPercent(double lSpeed, double rSpeed) {
        left.set(lSpeed);
        right.set(rSpeed);
    }

    /**
     * Returns whether or not a cube is at the back of the acquisition.
     * @return Whether or not a cube is at the back of the acquisition.
     */
    public boolean isCubeDetectedAtBack() {
        return innerDetectorEnabled && innerCubeDetector.get();
    }

    public boolean isCubeDetectedAtFront() {
        return  outerDetectorEnabled && outerCubeDetector.get();
    }

    /**
     * Returns whether or not the arms are closed far enough for the cube to be positioned correctly.
     * @return Whether or not the arms are closed far enough for the cube to be positioned correctly.
     */
    public boolean isClosed() {
        return armsClosedEnabled && !armsClosed.get();
    }

    public boolean isInnerDetectorEnabled() {
        return innerDetectorEnabled;
    }

    public void setInnerDetectorEnabled(boolean innerDetectorEnabled) {
        this.innerDetectorEnabled = innerDetectorEnabled;
    }

    public boolean isOuterDetectorEnabled() {
        return outerDetectorEnabled;
    }

    public void setOuterDetectorEnabled(boolean outerDetectorEnabled) {
        this.outerDetectorEnabled = outerDetectorEnabled;
    }

    public boolean isArmsClosedEnabled() {
        return armsClosedEnabled;
    }

    public void setArmsClosedEnabled(boolean armsClosedEnabled) {
        this.armsClosedEnabled = armsClosedEnabled;
    }

    public boolean shouldHaveCube() {
        return shouldHaveCube;
    }

    public void setShouldHaveCube(boolean shouldHaveCube) {
        this.shouldHaveCube = shouldHaveCube;
    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {
          setDefaultCommand(new MonitorCube());
    }
}
