package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
import org.team639.robot.Robot;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.drive.fancyauto.AutoDriveFinish;
import org.team639.robot.commands.drive.fancyauto.AutoDriveStart;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;

public class OneCubeScale extends CommandGroup {
    public OneCubeScale() {
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);
        StartingPosition position = Robot.getStartingPosition();

        if (position == StartingPosition.Center) { // Center is a miss-click. Drive straight over line.
            addSequential(new AutoCrossLine());
            return;
        }

        int side = position == StartingPosition.Right ? 1 : -1;

        if ((position == StartingPosition.Right && scaleSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && scaleSide == AutoUtils.OwnedSide.Left)) {
            addSequential(new AutoDriveStart(side * 115.5, 196));
            addParallel(new MoveToSetPosition(LiftPosition.ScaleHeight));
            addSequential(new AutoDriveFinish(side * 90.12, 288));
        } else {
            // TODO: go to lift on opposite side
        }

        // TODO: Actually place a cube here.
    }
}
