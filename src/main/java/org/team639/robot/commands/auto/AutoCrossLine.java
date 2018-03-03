package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.commands.drive.AutoDriveForward;

/**
 * An auto routine that simply crosses the line.
 */
public class AutoCrossLine extends CommandGroup {
    public AutoCrossLine() {
        addSequential(new AutoDriveForward(196));
        // TODO: Consider adding code to handle the case where the robot is in the center starting position.
    }
}
