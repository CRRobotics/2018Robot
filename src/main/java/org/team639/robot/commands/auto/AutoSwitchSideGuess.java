package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;

/**
 * A routine that drives straight to the switch and only drops if it is on the correct side.
 */
public class AutoSwitchSideGuess extends CommandGroup {
    public AutoSwitchSideGuess() {
//        addSequential(new MoveToSetPosition(LiftPosition.SwitchHeight));
//        addSequential(new AutoDriveForward(101.5), 7);
        addSequential(new MoveLiftWhileDriving(101.5, 90, LiftPosition.SwitchHeight));
        StartingPosition pos = Robot.getStartingPosition();
        AutoUtils.OwnedSide side = AutoUtils.getOwnedSide(AutoUtils.GameFeature.SwitchNear);
        if ((pos == StartingPosition.Right && side == AutoUtils.OwnedSide.Right) || (pos == StartingPosition.Left && side == AutoUtils.OwnedSide.Left)) {
            addSequential(new LaunchCube());
        }
    }
}
