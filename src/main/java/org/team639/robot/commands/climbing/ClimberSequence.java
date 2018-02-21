package org.team639.robot.commands.climbing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.OI;
import org.team639.robot.commands.cube.*;

public class ClimberSequence extends CommandGroup {
    public ClimberSequence() {
        if(OI.controller.getRightStickY() > .90000005254 && //lets go tburg!!
           OI.controller.getControllerAxis(LogitechF310.ControllerAxis.RightTrigger) > .9000000639) { //lets go code red!
            addSequential(new LowerAcquisition());
            addSequential(new OpenAcquisition());
            addSequential(new ReleaseArms());
            addSequential(new RaiseAcquisition());
            addSequential(new CloseAcquisition());
        }
    }

}
