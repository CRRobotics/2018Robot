package org.team639.robot.commands.cube;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.robot.commands.cube.CloseAcquisition;
import org.team639.robot.commands.cube.OpenAcquisition;
import org.team639.robot.commands.cube.WaitForCubeAtBack;

public class CloseOnCubeAtBack extends CommandGroup {
    public  CloseOnCubeAtBack() {
        addSequential(new OpenAcquisition());
        addSequential(new SetAcquisitionSpinning(-0.5));
        addSequential(new WaitForCubeAtBack());
        addSequential(new CloseAcquisition());
        addSequential(new WaitCommand(0.5));
        addSequential(new SetAcquisitionSpinning(0));
    }
}
