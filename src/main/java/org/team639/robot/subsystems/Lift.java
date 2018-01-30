package org.team639.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;

/**
 * The lift subsystem.
 * Responsible for moving the acquisition system up and down.
 */
public class Lift extends Subsystem {

    private TalonSRX mainTalon;
    private TalonSRX followerTalon;

    private DigitalInput lowerLimit;
    private DigitalInput firstStageLimit;
    private DigitalInput secondStageLimit;

    private Solenoid firstStageLock;

    private ControlMode currentControlMode;

    public Lift() {
        mainTalon = RobotMap.getLiftMain();
        followerTalon = RobotMap.getLiftFollower();

        followerTalon.follow(mainTalon);

        mainTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        mainTalon.setSensorPhase(true);

        mainTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);

        mainTalon.setNeutralMode(NeutralMode.Brake);

        mainTalon.configAllowableClosedloopError(0, 50, 10);

        lowerLimit = RobotMap.getLiftLowerLimit();
        firstStageLimit = RobotMap.getLiftFirstStageLimit();
        secondStageLimit = RobotMap.getLiftSecondStageLimit();

        firstStageLock = RobotMap.getLiftLock();
    }

    /**
     * Sets the speed of the lift with a percent of total speed from -1 to 1. Negative is down and positive is up.
     * @param speed The speed to move the lift.
     */
    public void setSpeedPercent(double speed) {
        if (speed > 1) speed = 1;
        else if (speed < -1) speed = -1;
        mainTalon.set(currentControlMode, speed);
    }

    /**
     * Returns the current value read by the encoder.
     * @return The current value read by the encoder.
     */
    public double getEncPos() {
        return mainTalon.getSelectedSensorPosition(0);
    }

    /**
     * Sets the current position of the relative encoder to zero.
     */
    public void zeroEncoder() {
        mainTalon.getSensorCollection().setQuadraturePosition(0, 10);
    }

    /**
     * Returns whether or not the lift is at the lower limit.
     * @return Whether or not the lift is at the lower limit.
     */
    public boolean isAtLowerLimit() {
        return lowerLimit.get();
    }

    /**
     * Returns whether or not the lift is at the upper limit of the first stage.
     * @return Whether or not the lift is at the upper limit of the first stage.
     */
    public boolean isAtFirstStageLimit() {
        return firstStageLimit.get();
    }

    /**
     * Returns whether or not the lift is at the upper limit of the second stage.
     * @return Whether or not the lift is at the upper limit of the second stage.
     */
    public boolean isAtSecondStageLimit() {
        return secondStageLimit.get();
    }

    /**
     * Locks or unlocks the first stage of the lift.
     * @param locked Whether or not the first stage should be locked.
     */
    public void setFirstStageLocked(boolean locked) {
        firstStageLock.set(locked);
    }

    /**
     * Returns whether of not the first stage is locked.
     * @return Whether of not the first stage is locked.
     */
    public boolean isFirstStageLocked() {
        return firstStageLock.get();
    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }
}
