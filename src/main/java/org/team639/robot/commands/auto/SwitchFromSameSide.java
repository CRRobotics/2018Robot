package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.lift.LiftPosition;

public class SwitchFromSameSide extends CommandGroup {
    public SwitchFromSameSide() {
        AutoUtils.OwnedSide switchSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.SwitchNear);

        addSequential(new MoveLiftWhileDriving(140, 90, LiftPosition.SwitchHeight));
        double angle = switchSide == AutoUtils.OwnedSide.Right ? 180 : 0;
        addSequential(new AutoTurnToAngle(angle));
        addSequential(new AutoDriveForward(39.06 - 19.25), 2); // TODO: Check timeout
        addSequential(new LaunchCube());
    }
}
