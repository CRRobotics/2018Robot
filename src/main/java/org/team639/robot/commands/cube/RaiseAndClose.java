package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import static org.team639.robot.Constants.Acquisition.ACQ_RAISE_TIME;

public class RaiseAndClose extends CommandGroup {
    public RaiseAndClose() {
        addSequential(new RaiseAcquisition());
        addSequential(new WaitCommand(ACQ_RAISE_TIME));
        addSequential(new CloseAcquisition());
    }
}
