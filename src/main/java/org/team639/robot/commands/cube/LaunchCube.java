package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.*;
import org.team639.lib.commands.ExecuteCommandAndWait;
import org.team639.robot.Robot;

import static org.team639.robot.Constants.Acquisition.DEFAULT_ACQ_SPEED;

/**
 * Launches a cube
 */
public class LaunchCube extends CommandGroup {

    public LaunchCube() {
        addSequential(new ConditionalCommand(new ExecuteCommandAndWait(new LowerAcquisition(), 1)) {
            /**
             * The Condition to test to determine which Command to run.
             *
             * @return true if m_onTrue should be run or false if m_onFalse should be run.
             */
            @Override
            protected boolean condition() {
                return Robot.getCubeAcquisition().isRaised();
            }
        });
        addSequential(new SetAcquisitionSpinning(DEFAULT_ACQ_SPEED));
        addSequential(new WaitCommand(0.5));
        addSequential(new OpenAcquisition());
        addSequential(new RaiseAcquisition());
    }
}
