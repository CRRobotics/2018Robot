package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.robot.commands.cube.CloseAcquisition;
import org.team639.robot.commands.cube.OpenAcquisition;
import org.team639.robot.commands.cube.WaitForCubeAtBack;

import static org.team639.robot.Constants.DEFAULT_ACQ_SPEED;

public class CloseOnCubeAtBack extends CommandGroup {
    public CloseOnCubeAtBack() {
        addSequential(new LowerAcquisition());
        addSequential(new OpenAcquisition());
        addSequential(new SetAcquisitionSpinning(-1 * DEFAULT_ACQ_SPEED));
        addSequential(new WaitForCubeAtBack());
        addSequential(new CloseAndIntake());
    }
}
