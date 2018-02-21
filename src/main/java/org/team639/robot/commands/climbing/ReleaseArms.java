package org.team639.robot.commands.climbing;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.OI;
import org.team639.robot.RobotMap;

public class ReleaseArms extends Command {
    public ReleaseArms(){

    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
            RobotMap.getClimbPiston().set(true);
    }

    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     * <p>
     * <p>Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. We recommend using
     * for this.
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return true;
    }
}
