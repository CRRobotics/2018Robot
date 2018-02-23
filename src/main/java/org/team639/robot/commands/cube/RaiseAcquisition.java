package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * A command that tells the acquisition to raise and then immediately finishes.
 * @see CubeAcquisition
 */
public class RaiseAcquisition extends Command {
    private CubeAcquisition cubeAcquisition;

    /**
     * Constructs a new RaiseAcquisition and requires needed subsystems.
     */
    public RaiseAcquisition() {
        super("RaiseAcquisition");
        cubeAcquisition = Robot.getCubeAcquisition();
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        // Using shouldHaveCube here has the small potential to cause a problem but will not once the new sensor is installed and working.
        if (!cubeAcquisition.shouldHaveCube() && !cubeAcquisition.isCubeDetectedAtBack()) cubeAcquisition.setPistonMode(CubeAcquisition.PistonMode.Open);
        cubeAcquisition.setRaised(true);
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
        return true;
    }
}
