package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.team639.robot.Constants.Acquisition.DEFAULT_ACQ_SPEED;

/**
 * Starts intaking a cube once the back sensor is triggered.
 */
public class CloseOnCubeAtBack extends CommandGroup {
    public CloseOnCubeAtBack() {
        addSequential(new LowerAcquisition());
        addSequential(new OpenAcquisition());
        addSequential(new SetAcquisitionSpinning(-1 * DEFAULT_ACQ_SPEED));
        addSequential(new WaitForCubeAtBack());
        addSequential(new CloseAndIntake());
    }
}
