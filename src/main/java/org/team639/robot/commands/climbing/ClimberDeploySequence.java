package org.team639.robot.commands.climbing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.lib.commands.ExecuteCommandAndWait;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.*;

import static org.team639.robot.Constants.Acquisition.ACQ_LOWER_TIME;
import static org.team639.robot.Constants.Acquisition.ACQ_RAISE_TIME;

/**
 * Performs the actions necessary to deploy the climbing bars.
 */
public class ClimberDeploySequence extends CommandGroup {
    public ClimberDeploySequence() {
//        addSequential(new LowerAcquisition());
//        addSequential(new OpenAcquisition());
//        addSequential(new WaitCommand(ACQ_LOWER_TIME)); // TODO: Verify this time
//        addSequential(new RaiseAcquisition());
//        addSequential(new WaitCommand(ACQ_RAISE_TIME));
        addSequential(new ConditionalCommand(new ExecuteCommandAndWait(new RaiseAcquisition(), ACQ_RAISE_TIME)) {
            @Override
            protected boolean condition() {
                return !Robot.getCubeAcquisition().isRaised();
            }
        });
        addSequential(new CloseAcquisition());
        addSequential(new ReleaseArms());
        addSequential(new WaitCommand(0.5));
        addSequential(new OpenAcquisition());
        addSequential(new LowerAcquisition());
    }
}
