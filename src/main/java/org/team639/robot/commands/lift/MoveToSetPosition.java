package org.team639.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.math.PID;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.Lift;

import static org.team639.robot.Constants.*;

/**
 * Moves the lift to one of several preset positions defined in {@link LiftPositions}
 * @see LiftPositions
 * @see Lift
 */
public class MoveToSetPosition extends Command {
    private Lift lift;
    private LiftPositions position;
    private int startPosition;
    private PID pid;
    private boolean done;
    public MoveToSetPosition(LiftPositions position) {
        this.position = position;
        lift = Robot.getLift();
        requires(lift);
    }


    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        done = false;
        startPosition = lift.getEncPos();
        pid = new PID(LIFT_POS_P, LIFT_POS_I, LIFT_POS_D, LIFT_POS_MIN, LIFT_POS_MAX, LIFT_POS_RATE, LIFT_POS_TOLERANCE, LIFT_POS_I_CAP);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        // TODO: Lock and unlock the first stage.
        double val = pid.compute(position.getPercentHeight() * LIFT_MAX_HEIGHT - lift.getEncPos());
        lift.setSpeedPercent(val);
        done = val == 0 || lift.isAtLowerLimit() || lift.isAtSecondStageLimit();
    }

    /**
     * Called when the command ended peacefully. This is where you may want to wrap up loose ends,
     * like shutting off a motor that was being used in the command.
     */
    @Override
    protected void end() {
        lift.setSpeedPercent(0);
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
        return done;
    }

}
