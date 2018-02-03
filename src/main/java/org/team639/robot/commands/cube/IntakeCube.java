package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.CliffordTheBigRedBot;
import org.team639.robot.subsystems.CubeAcquisition;

import static org.team639.robot.Constants.DEFAULT_ACQ_SPEED;

/**
 * Runs the acquisition motors inwards. Runs until interrupted.
 */
public class IntakeCube extends Command {
    private CubeAcquisition cubeAcquisition;

    public IntakeCube() {
        super("IntakeCube");
        cubeAcquisition = CliffordTheBigRedBot.getCubeAcquisition();
        requires(cubeAcquisition);
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        cubeAcquisition.setSpeedsPercent(-1 * DEFAULT_ACQ_SPEED, -1 * DEFAULT_ACQ_SPEED);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        cubeAcquisition.setSpeedsPercent(-1 * DEFAULT_ACQ_SPEED, -1 * DEFAULT_ACQ_SPEED);
    }

    /**
     * Called when the command ends because somebody called {@link Command#cancel() cancel()} or
     * another command shared the same requirements as this one, and booted it out.
     * <p>
     * <p>This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * <p>
     * <p>Generally, it is useful to simply call the {@link Command#end() end()} method within this
     * method, as done here.
     */
    @Override
    protected void interrupted() {
        end();
    }

    /**
     * Called when the command ended peacefully. This is where you may want to wrap up loose ends,
     * like shutting off a motor that was being used in the command.
     */
    @Override
    protected void end() {
        cubeAcquisition.setSpeedsPercent(0, 0);
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
        return false;
    }
}
