package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
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

        int side = switchSide == AutoUtils.OwnedSide.Right ? -1 : 1;

//        addSequential(new MoveToSetPosition(LiftPosition.SwitchHeight));

        addSequential(new AutoDriveForward(10, 90));
//        double angle = 90 + Math.toDegrees(Math.atan(4.5 * 12 / 87.5)) * (switchSide == AutoUtils.OwnedSide.Right ? -1 : 1);
        double angle = switchSide == AutoUtils.OwnedSide.Right ? Math.toDegrees(Math.atan2(81.5, (55 - 4))) : Math.toDegrees(Math.atan2(81.5, -1 * (55 + 4)));
        addSequential(new PrintCommand("" + angle));
        addSequential(new AutoTurnToAngle(angle));
//        addSequential(new AutoDriveForward(Math.sqrt(Math.pow(55 + (side * 4), 2) + Math.pow(81.5, 2))), angle);
        addSequential(new MoveLiftWhileDriving(Math.sqrt(Math.pow(55 + (side * 4), 2) + Math.pow(81.5, 2)), angle, LiftPosition.SwitchHeight));
        addSequential(new AutoTurnToAngle(90));
        addSequential(new AutoDriveForward(10, 90), 1.5);
        addSequential(new LaunchCube());
        // TODO: TEST
    }
}
