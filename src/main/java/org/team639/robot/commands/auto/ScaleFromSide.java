package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;

public class ScaleFromSide extends CommandGroup {
    public ScaleFromSide() {
//        addSequential(new AutoDriveForward(324, 90));
//        addSequential(new AutoTurnToAngle(180));
//        addSequential(new LaunchCube());
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);

        if (scaleSide == AutoUtils.OwnedSide.Right) {
            addSequential(new AutoDriveForward(140, 90));
        } else {
            addSequential(new AutoDriveForward(235 - 19.25, 90));
//            addSequential(new AutoTurnToAngle());
        }


    }
}
