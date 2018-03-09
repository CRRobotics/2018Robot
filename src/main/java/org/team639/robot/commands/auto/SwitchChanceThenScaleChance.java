package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.lift.LiftPosition;

/**
 * A command that goes to the switch if it is on the correct side, then falls back to going to the scale if it is on the correct side, then falls back to driving forward across the auto line.
 */
public class SwitchChanceThenScaleChance extends CommandGroup {
    public SwitchChanceThenScaleChance() {
        AutoUtils.OwnedSide switchSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.SwitchNear);
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);
        StartingPosition position = Robot.getStartingPosition();

        if ((position == StartingPosition.Right && switchSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && switchSide == AutoUtils.OwnedSide.Left)) {
            addSequential(new MoveLiftWhileDriving(140, 90, LiftPosition.SwitchHeight));
            double angle = switchSide == AutoUtils.OwnedSide.Right ? 180 : 0;
            addSequential(new AutoTurnToAngle(angle));
            addSequential(new AutoDriveForward(39.06 - 19.25)); // TODO: Timeout here
            addSequential(new LaunchCube());
        } else {
            addSequential(new ScaleChance());
        }
    }
}
