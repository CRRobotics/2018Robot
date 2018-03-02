package org.team639.robot.commands.climbing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.OI;
import org.team639.robot.commands.cube.*;

import static org.team639.robot.Constants.Acquisition.ACQ_LOWER_TIME;

/**
 * Performs the actions necessary to deploy the climbing bars.
 */
public class ClimberDeploySequence extends CommandGroup {
    public ClimberDeploySequence() {
        addSequential(new LowerAcquisition());
        addSequential(new OpenAcquisition());
        addSequential(new WaitCommand(ACQ_LOWER_TIME)); // TODO: Verify this time
        addSequential(new ReleaseArms());
    }
}
