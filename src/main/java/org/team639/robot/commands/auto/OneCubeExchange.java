package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.Robot;
import org.team639.robot.commands.cube.LaunchCube;
import org.team639.robot.commands.cube.LowerAcquisition;
import org.team639.robot.commands.drive.fancyauto.AutoDriveFinish;
import org.team639.robot.commands.drive.fancyauto.AutoDriveSegment;
import org.team639.robot.commands.drive.fancyauto.AutoDriveStart;

public class OneCubeExchange extends CommandGroup {
    public OneCubeExchange() {
        StartingPosition position = Robot.getStartingPosition();

        if (position == StartingPosition.Right) {
            addSequential(new AutoDriveStart(position.x, position.y + 15));
            addSequential(new AutoDriveSegment(position.x - 30,position.y + 45));
            addSequential(new AutoDriveSegment(-6, position.y + 45));
            addSequential(new AutoDriveFinish(-36, position.y + 15));
        } else if (position == StartingPosition.Left) {
            addSequential(new AutoDriveStart(-1 * position.x, position.y + 15));
            addParallel(new LowerAcquisition());
            addSequential(new AutoDriveSegment(-1 * position.x + 30,position.y + 45));
            addSequential(new AutoDriveSegment(-66, position.y + 45));
            addSequential(new AutoDriveFinish(-36, position.y + 15));
            addSequential(new LaunchCube());
        }
    }
}
