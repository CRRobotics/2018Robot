package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;

/**
 * Tells the cube acquisition system whether it should currently have a cube.
 * @see org.team639.robot.subsystems.CubeAcquisition
 * @see MonitorCube
 */
public class SetShouldHaveCube extends Command {
    private boolean shouldHaveCube;

    public SetShouldHaveCube(boolean shouldHaveCube) {
        this.shouldHaveCube = shouldHaveCube;
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Robot.getCubeAcquisition().setShouldHaveCube(shouldHaveCube);
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
        return true;
    }
}
