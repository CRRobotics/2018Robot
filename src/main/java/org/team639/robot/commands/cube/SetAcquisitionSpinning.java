package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * Sets the acquisition motors spinning at a given speed.
 * @see CubeAcquisition
 */
public class SetAcquisitionSpinning extends Command {
    private CubeAcquisition cubeAcquisition;
    private double speed;

    public SetAcquisitionSpinning(double speed) {
        this.speed = speed;
        cubeAcquisition = Robot.getCubeAcquisition();
        requires(cubeAcquisition);
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        cubeAcquisition.setSpeedsPercent(speed, speed);
    }

    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return true;
    }
}
