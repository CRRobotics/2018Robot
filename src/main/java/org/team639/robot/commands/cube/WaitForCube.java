package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * A command that waits for a cube to be detected in the acquisition.
 */
public class WaitForCube extends Command {
    private CubeAcquisition cubeAcquisition;

    /**
     * Constructs a new WaitForCube and requires needed subsystem.
     */
    public WaitForCube() {
        super("WaitForCube");
        cubeAcquisition = Robot.getCubeAcquisition();
        requires(cubeAcquisition);
    }

    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return cubeAcquisition.isCubeDetected();
    }
}
