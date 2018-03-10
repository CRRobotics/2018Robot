package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;

public class MoveLiftWhileDriving extends CommandGroup {
    public MoveLiftWhileDriving(double distanceInches, double angle, LiftPosition position) {
//        addParallel(new MoveToSetPosition(position)); FIXME: Put back
        addParallel(new AutoDriveForward(distanceInches, angle));
    }
}
