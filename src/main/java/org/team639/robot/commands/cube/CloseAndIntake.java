package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import static org.team639.robot.Constants.DEFAULT_ACQ_SPEED;

/**
 * Closes and intakes, assuming there is a cube within reach.
 */
public class CloseAndIntake extends CommandGroup {
    public CloseAndIntake() {
        addSequential(new SetAcquisitionSpinning(-1 * DEFAULT_ACQ_SPEED));
        addSequential(new CloseAcquisition());
        addSequential(new WaitCommand(0.3));
        addSequential(new SetAcquisitionSpinning(0));
        addSequential(new SetShouldHaveCube(true));
//        addSequential(new RaiseAcquisition());
    }
}
