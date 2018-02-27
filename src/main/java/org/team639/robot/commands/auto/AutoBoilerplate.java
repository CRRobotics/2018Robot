package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.robot.commands.lift.ZeroLift;

public class AutoBoilerplate extends CommandGroup {
    public AutoBoilerplate(Command auto, double delay) {
        addSequential(new ZeroLift());
        addSequential(new WaitCommand(delay));
        addSequential(auto);
    }
}
