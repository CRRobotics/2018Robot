package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team639.lib.math.AngleMath;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;

import static org.team639.robot.Constants.Acquisition.ACQ_RAISE_TIME;

public class OneCubeScale extends CommandGroup {
    public OneCubeScale() {
        AutoUtils.OwnedSide scaleSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.Scale);
        StartingPosition position = Robot.getStartingPosition();



        if (position == StartingPosition.Center) { // Center is a mis-click. Drive straight over line.
            addSequential(new AutoCrossLine());
            return;
        }

        int side = position == StartingPosition.Right ? -1 : 1;

//        addSequential(new AutoDriveForward(235 - 19.25)); // Center at x = +/- 115.5, y = 235

        if ((position == StartingPosition.Right && scaleSide == AutoUtils.OwnedSide.Right) || (position == StartingPosition.Left && scaleSide == AutoUtils.OwnedSide.Left)) {
//            double angle = Math.toDegrees(Math.atan2(64.65, side * 25.38));
//            addSequential(new AutoTurnToAngle(angle));
//            addSequential(new AutoDriveForward(AngleMath.pythagHypotenuse(64.65, 25.38) - 19.25, angle));
            addSequential(new SameSideScale());
        } else {
            addSequential(new AutoDriveForward(240 - 19.25), 90);
            double angle = 90 - side * 90;
            addSequential(new AutoTurnToAngle(angle));
            addSequential(new AutoDriveForward(200 - 19.25 - 4, angle));
            addSequential(new AutoTurnToAngle(90));
            addSequential(new MoveLiftWhileDriving(71.65 - 20 - 7, 90, LiftPosition.ScaleHeight));
            addSequential(new LaunchCube());
        }
        addSequential(new WaitCommand(ACQ_RAISE_TIME));
        addSequential(new MoveToSetPosition(LiftPosition.FullyLowered));
    }
}
