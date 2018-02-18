package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

/**
 * A command that correctly positions a cube within the acquisition.
 * Assumes that a cube is already somewhere within the range of the acquisition.
 * @see CubeAcquisition
 */
public class PositionCube extends Command {
    private CubeAcquisition cubeAcquisition;

    /**
     * Possible states of the command
     */
    private enum State {
        Intake,
        Rotate,
        Finished
    }

    private State state;

    private long startRotateTime;

    public PositionCube() {
        super("PositionCube");
        cubeAcquisition = Robot.getCubeAcquisition();
        requires(cubeAcquisition);
        startRotateTime = -1;
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        state = State.Intake;
        cubeAcquisition.setPistonMode(CubeAcquisition.PistonMode.Floating);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        switch (state) {
            case Intake:
                cubeAcquisition.setSpeedsPercent(-0.3, -0.3);
                if (cubeAcquisition.isCubeDetectedAtBack() && cubeAcquisition.isClosed()) {
                    cubeAcquisition.setSpeedsPercent(0, 0);
                    state = State.Finished;
                } // else if (cubeAcquisition.isCubeDetectedAtBack() && !cubeAcquisition.isClosed()) {
//                    cubeAcquisition.setSpeedsPercent(0, 0);
//                    state = State.Rotate;
//                }
                break;
            case Rotate:
                if (startRotateTime == -1) startRotateTime = System.currentTimeMillis();
                cubeAcquisition.setSpeedsPercent(0.2, -0.2);
                if (cubeAcquisition.isClosed() || System.currentTimeMillis() - startRotateTime >= 100) {
                    cubeAcquisition.setSpeedsPercent(0, 0);
                    startRotateTime = -1;

                    state = State.Intake;
                }
                break;
        }
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
        return false;
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
        cubeAcquisition.setShouldHaveCube(true);
    }
}
