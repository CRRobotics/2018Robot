package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.drive.fancyauto.AutoDriveComplete;

public class FancyTest2 extends CommandGroup {
    public FancyTest2() {
        addSequential(new AutoTurnToAngle(270));
        addSequential(new AutoDriveForward(6 * 12));
        addSequential(new AutoTurnToAngle(90));
//        addSequential(new AutoDriveStart(8 * 12, 14.5 * 12));
        addSequential(new AutoDriveComplete(8 * 12, 18 * 12));
    }
}
