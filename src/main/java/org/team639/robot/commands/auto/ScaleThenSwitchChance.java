package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.lib.commands.ExecuteCommandAndWait;
import org.team639.lib.math.AngleMath;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.cube.RaiseAcquisition;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;

import static org.team639.robot.Constants.Acquisition.ACQ_RAISE_TIME;

/**
 *
 */
public class ScaleThenSwitchChance extends CommandGroup {
    public ScaleThenSwitchChance() {
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);
        AutoUtils.OwnedSide switchSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.SwitchNear);

        StartingPosition position = Robot.getStartingPosition();

        if ((scaleSide == AutoUtils.OwnedSide.Right && position == StartingPosition.Right) || (scaleSide == AutoUtils.OwnedSide.Left && position == StartingPosition.Left)) {
            addSequential(new SameSideScale());

            int side = position == StartingPosition.Right ? -1 : 1;
            double x =(90.12) - (19.25 * Math.cos(Math.atan2(64.65, 25.38)));
            double y =(299.65) - (19.25 * Math.sin(Math.atan2(64.65, 25.38)));

            double angle = Math.atan2(209 - y, -1 * side * 90.12 + side * x);
            double dist = AngleMath.pythagHypotenuse(209 - y, 90.12 - x) - 19.25;

            addSequential(new ExecuteCommandAndWait(new RaiseAcquisition(), ACQ_RAISE_TIME));
            addSequential(new MoveToSetPosition(LiftPosition.FullyLowered));
            addSequential(new AutoTurnToAngle(angle));
            addSequential(new DriveDistanceWhileAquiring(dist, angle));

            if ((position == StartingPosition.Right && switchSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && switchSide == AutoUtils.OwnedSide.Left)) {
                addSequential(new MoveToSetPosition(LiftPosition.SwitchHeight));
                addSequential(new AutoDriveForward(13, 270), 2);
                addSequential(new LaunchCube());
            }
        } else if ((position == StartingPosition.Right && switchSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && switchSide == AutoUtils.OwnedSide.Left)) {
            addSequential(new SwitchFromSameSide());
        } else {
            addSequential(new OneCubeScale());
        }
    }
}
