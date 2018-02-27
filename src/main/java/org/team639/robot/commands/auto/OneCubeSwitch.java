package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import openrio.powerup.MatchData;
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
        addSequential(new AutoDriveStart(70.5, 84.25));
        addSequential(new AutoDriveFinish(70.5, 101.5));
        addParallel(new MoveToSetPosition(LiftPosition.SwitchHeight));
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
