package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * A command that waits for both the back sensor and arms closed sensor of the acquisition to return true.
 */
public class WaitForBackAndClosedSensors extends Command {
    private CubeAcquisition cubeAcquisition;

    public WaitForBackAndClosedSensors() {
        cubeAcquisition = Robot.getCubeAcquisition();
        requires(cubeAcquisition);
    }

    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     * <p>
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return cubeAcquisition.isCubeDetectedAtBack() && cubeAcquisition.isClosed();
    }


}
