package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.lib.math.AngleMath;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.lift.LiftPosition;

/**
 * An auto routine that only goes to the scale if it is on the correct side. Otherwise drives forward over line.
 */
public class ScaleChance extends CommandGroup {
    public ScaleChance() {
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);
        StartingPosition position = Robot.getStartingPosition();

        if ((scaleSide == AutoUtils.OwnedSide.Right && position == StartingPosition.Right) || (scaleSide == AutoUtils.OwnedSide.Left && position == StartingPosition.Left)) {
            addSequential(new AutoDriveForward(235, 90));
            double side = scaleSide == AutoUtils.OwnedSide.Right ? -1 : 1;
            double angle = Math.atan2(64.65, side * 25.38);
            addSequential(new AutoTurnToAngle(angle));
            addSequential(new MoveLiftWhileDriving(AngleMath.pythagHypotenuse(64.65, 25.38), angle, LiftPosition.ScaleHeight));
            addSequential(new LaunchCube());
        } else {
            addSequential(new AutoDriveForward(196));
        }
    }
}
