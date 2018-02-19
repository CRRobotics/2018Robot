package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
import org.team639.robot.Robot;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;

public class OneCubeScale extends CommandGroup {
    public OneCubeScale() {

        MatchData.OwnedSide side = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
        StartingPosition position = Robot.getStartingPosition();


        if ((side == MatchData.OwnedSide.RIGHT && position == StartingPosition.Right) || (side == MatchData.OwnedSide.LEFT && position == StartingPosition.Left)) {
            addSequential(new AutoDriveForward(10));
            double relAngle = Math.toDegrees(Math.atan(41.88 / 247.15)) * (position == StartingPosition.Left ? -1 : 1);
            addSequential(new AutoTurnToAngle(90 + relAngle));
            addSequential(new AutoDriveForward(Math.sqrt(Math.pow(41.88, 2) + Math.pow(247.15, 2))));
            addSequential(new AutoDriveForward(10));
        } else {
            addSequential(new AutoDriveForward(219));
            int direction = position == StartingPosition.Left ? -1 : 1;
            addSequential(new AutoTurnToAngle(90 + 90 * direction));
            addSequential(new AutoDriveForward(80.65));
        }

        // TODO: Actually place a cube here.
    }
}
