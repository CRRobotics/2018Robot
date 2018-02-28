package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.*;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.drive.fancyauto.AutoDriveFinish;
import org.team639.robot.commands.drive.fancyauto.AutoDriveStart;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;

/**
 * An auto period that places one cube on the switch.
 */
public class OneCubeSwitch extends CommandGroup {
    public OneCubeSwitch() {
        AutoUtils.OwnedSide switchSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.SwitchNear);

        // Don't do anything if game data is not received or we are in the wrong place.
        if (switchSide == AutoUtils.OwnedSide.Unknown /*|| Robot.getStartingPosition() != StartingPosition.Center*/) return;

        int side = switchSide == AutoUtils.OwnedSide.Right ? 1 : -1;

        addSequential(new AutoDriveStart(side * 50.5, 64.25));

        addParallel(new AutoDriveFinish(side * 70.5, 101.5));
        addSequential(new MoveToSetPosition(LiftPosition.SwitchHeight));

        addSequential(new LaunchCube());

//        addSequential(new CloseAcquisition());
//        addParallel(new MoveToSetPosition(LiftPosition.SwitchHeight));
//        addSequential(new AutoDriveForward(10));
//        MatchData.OwnedSide side = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
//        double angle = 90 + Math.toDegrees(Math.atan(4.5 * 12 / 87.5)) * (side == MatchData.OwnedSide.RIGHT ? -1 : 1);
//        addSequential(new AutoTurnToAngle(angle));
//        addSequential(new AutoDriveForward(Math.sqrt(Math.pow(58, 2) + Math.pow(82, 2))));
//        addSequential(new AutoTurnToAngle(90));
//        addSequential(new AutoDriveForward(10));
//        addSequential(new LowerAcquisition());
//        addSequential(new EjectCube());
        // TODO: TEST
    }
}
