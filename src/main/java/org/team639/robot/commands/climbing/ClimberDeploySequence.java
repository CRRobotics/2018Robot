package org.team639.robot.commands.climbing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.OI;
import org.team639.robot.commands.cube.*;

public class ClimberDeploySequence extends CommandGroup {
    public ClimberDeploySequence() {
        addSequential(new LowerAcquisition());
        addSequential(new OpenAcquisition());
        addSequential(new WaitCommand(0.5)); // TODO: Verify this time
        addSequential(new ReleaseArms());
        addSequential(new RaiseAcquisition());
        addSequential(new CloseAcquisition());
    }
}
