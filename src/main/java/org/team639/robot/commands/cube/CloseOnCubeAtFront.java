package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.team639.robot.Constants.DEFAULT_ACQ_SPEED;


/**
 * Starts intaking a cube once the front sensor is triggered.
 */
public class CloseOnCubeAtFront extends CommandGroup {
    public CloseOnCubeAtFront() {
        addSequential(new LowerAcquisition());
        addSequential(new OpenAcquisition());
        addSequential(new SetAcquisitionSpinning(-1 * DEFAULT_ACQ_SPEED));
        addSequential(new WaitForCubeAtFront());
        addSequential(new CloseAcquisition());
        addSequential(new WaitForCubeAtBack());
        addSequential(new CloseAndIntake());
    }
}
