package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team639.lib.led.patterns.GreenFlashPattern;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.CubeAcquisition;

import static org.team639.robot.Constants.Acquisition.MONITOR_CUBE_SPEED;
import static org.team639.robot.Constants.Acquisition.MONITOR_CUBE_TIMEOUT;

/**
 * A command that tries to hold on to a cube if it is knocked loose.
 * @see CubeAcquisition
 * @see SetShouldHaveCube
 */
public class MonitorCube extends Command {
    private CubeAcquisition cubeAcquisition;

    private enum State {
        Spinning,
        Watching
    }

    private State state = State.Watching;

    private long startedSpinningTime;

    public MonitorCube() {
        super("MonitorCube");
        cubeAcquisition = Robot.getCubeAcquisition();
        requires(cubeAcquisition);
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        if (cubeAcquisition.isCubeDetectedAtBack()) {
            cubeAcquisition.setShouldHaveCube(true);
            new TimedCommand(1){
                @Override
                public void initialize() {
                    Robot.getLedStrip().changeMode(new GreenFlashPattern(Robot.getLedStrip().getLength()));
                }
                @Override
                public void interrupted() {
                    end();
                }
                @Override
                public void end() {
                    Robot.getLedStrip().changeMode(Robot.getDefaultPattern());
                }

            }.start();
        }

        startedSpinningTime = 0;
        state = State.Watching;
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        switch (state) {
            case Watching:
                if (cubeAcquisition.shouldHaveCube()) {
                    cubeAcquisition.setSpeedsPercent(-0.2, -0.2);
                } else {
                    cubeAcquisition.setSpeedsPercent(0, 0);
                }
                if (cubeAcquisition.shouldHaveCube() && !cubeAcquisition.isCubeDetectedAtBack()) {
                    cubeAcquisition.setSpeedsPercent(-1 * MONITOR_CUBE_SPEED, -1 * MONITOR_CUBE_SPEED);
                    state = State.Spinning;
                    startedSpinningTime = System.currentTimeMillis();
                }
                break;
            case Spinning:
                if (cubeAcquisition.isCubeDetectedAtBack()) {
                    cubeAcquisition.setSpeedsPercent(0, 0);
                    state = State.Watching;
                } else if (!cubeAcquisition.isCubeDetectedAtFront()) {
                    cubeAcquisition.setShouldHaveCube(false);
                    cubeAcquisition.setSpeedsPercent(0, 0);
                    state = State.Watching;
                }
                break;
        }
    }

    /**
     * Called when the command ended peacefully. This is where you may want to wrap up loose ends,
     * like shutting off a motor that was being used in the command.
     */
    @Override
    protected void end() {
        super.end();
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
        super.interrupted();
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
        return false;
    }
}
