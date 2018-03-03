package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.drive.fancyauto.AutoDriveFinish;
import org.team639.robot.commands.drive.fancyauto.AutoDriveSegment;
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

        int side = position == StartingPosition.Right ? -1 : 1;

        addSequential(new AutoDriveForward(10, 90));

        double angle = Math.toDegrees(Math.atan2(81.5, -1 * side * (125.905 + side * 4)));

        addSequential(new AutoTurnToAngle(angle));
        addSequential(new AutoDriveForward(Math.sqrt(Math.pow(81.5, 2) + Math.pow(-1 * side * (125.905 + side * 4), 2)), angle));

        addSequential(new AutoTurnToAngle(90));
        addSequential(new AutoDriveForward(90)); // At y = 230

        if ((position == StartingPosition.Right && scaleSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && scaleSide == AutoUtils.OwnedSide.Left)) {

        }



//
//        if ((position == StartingPosition.Right && scaleSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && scaleSide == AutoUtils.OwnedSide.Left)) {
//            addSequential(new AutoDriveStart(side * 115.5, 196));
//            addParallel(new MoveToSetPosition(LiftPosition.ScaleHeight));
//            addSequential(new AutoDriveFinish(side * 90.12, 288));
//        } else {
//            // TODO: go to lift on opposite side
//            addSequential(new AutoDriveStart(side * 115.5, 196));
//            addSequential(new AutoDriveSegment(side * 85.5, 226));
//            addSequential(new AutoDriveSegment(side * -30, 226));
//
//            addParallel(new MoveToSetPosition(LiftPosition.ScaleHeight));
//            addSequential(new AutoDriveFinish(side * -60, 299));
//
//            addSequential(new LaunchCube());
//        }
//
//        // TODO: Actually place a cube here.
    }
}
