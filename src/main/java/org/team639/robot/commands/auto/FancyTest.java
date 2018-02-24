package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.Robot;
import org.team639.robot.commands.drive.ZeroYaw;
import org.team639.robot.commands.drive.fancyauto.AutoDriveFinish;
import org.team639.robot.commands.drive.fancyauto.AutoDriveSegment;
import org.team639.robot.commands.drive.fancyauto.AutoDriveStart;

public class FancyTest extends CommandGroup {
    public FancyTest() {
        addSequential(new ZeroYaw());
        addSequential(new Command() {
            /**
             * The initialize method is called the first time this Command is run after being started.
             */
            @Override
            protected void initialize() {
                Robot.resetTrackedPosition(0, 0);
            }

            @Override
            protected boolean isFinished() {
                return true;
            }
        });
        addSequential(new AutoDriveStart(0, 9.5 * 12));
//        addSequential(new AutoDriveSegment(2.5 * 12, 12 * 12));
        addSequential(new AutoDriveSegment(5.5 * 12, 12 * 12));
        addSequential(new AutoDriveSegment(8 * 12, 14.5 * 12));
        addSequential(new AutoDriveFinish(8 * 12, 18 * 12));
    }
}
