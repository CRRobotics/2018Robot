package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.robot.commands.cube.CloseAcquisition;
import org.team639.robot.commands.drive.ZeroYaw;
import org.team639.robot.commands.lift.ZeroLift;

public class AutoBoilerplate extends CommandGroup {
    public AutoBoilerplate(Command auto, double delay) {
        addSequential(new ZeroYaw());
        addSequential(new CloseAcquisition());
        addSequential(new PrintCommand("Starting boilerplate -- " + auto.getClass().getName()));
//        addParallel(new ZeroLift(), 1); FIXME: Put back
        addSequential(new PrintCommand("zeroed lift"));
        if(delay > 0) addSequential(new WaitCommand(delay));
        addSequential(auto);
        addSequential(new PrintCommand("End of boilerplate"));
    }
}
