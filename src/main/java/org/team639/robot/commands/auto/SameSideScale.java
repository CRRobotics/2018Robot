package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.lib.math.AngleMath;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.lift.LiftPosition;

public class SameSideScale extends CommandGroup {
    public SameSideScale() {
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);

        addSequential(new AutoDriveForward(235 - 19.25)); // Center at x = +/- 115.5, y = 235
        double side = scaleSide == AutoUtils.OwnedSide.Right ? -1 : 1;
        double angle = Math.toDegrees(Math.atan2(64.65, side * 25.38));
        addSequential(new AutoTurnToAngle(angle));
        addSequential(new MoveLiftWhileDriving(AngleMath.pythagHypotenuse(64.65, 25.38) - 19.25, angle, LiftPosition.ScaleHeight));
        addSequential(new LaunchCube());
    }
}
