package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;

public class OneCubeSwitch extends CommandGroup {
    public OneCubeSwitch() {
        addSequential(new AutoDriveForward(10));
        MatchData.OwnedSide side = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
        double angle = 90 + Math.toDegrees(Math.atan(4.5 * 12 / 87.5)) * (side == MatchData.OwnedSide.RIGHT ? -1 : 1);
        addSequential(new AutoTurnToAngle(angle));
        addSequential(new AutoDriveForward(Math.sqrt(Math.pow(4.5 * 12, 2) + Math.pow(87.5, 2))));
        addSequential(new AutoTurnToAngle(90));
        addSequential(new AutoDriveForward(10));
    }
}
