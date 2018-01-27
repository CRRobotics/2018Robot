package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * Tells the acquisition to close and then immediately ends.
 * @see CubeAcquisition
 */
public class CloseAcquisition extends Command {
    private CubeAcquisition cubeAcquisition;

    /**
     * Constructs a new CloseAcquisition and requires needed subsystem.
     */
    public CloseAcquisition() {
        super("CloseAcquisition");
        cubeAcquisition = new CubeAcquisition();
        requires(cubeAcquisition);
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
