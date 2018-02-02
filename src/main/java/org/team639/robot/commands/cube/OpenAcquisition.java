package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * A command that tells the acquisition to open and then immediately finishes.
 * @see CubeAcquisition
 */
public class OpenAcquisition extends Command {
    private CubeAcquisition cubeAcquisition;

    /**
     * Creates a new CubeAcquisition and requires needed subsystem.
     */
    public OpenAcquisition() {
        super("OpenAcquisition");
        cubeAcquisition = Robot.getCubeAcquisition();
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        cubeAcquisition.setPistonMode(CubeAcquisition.PistonMode.Open);
        System.out.println("opening");
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
