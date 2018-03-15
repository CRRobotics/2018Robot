package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.commands.cube.CloseOnCubeAtFront;
import org.team639.robot.commands.drive.AutoDriveForward;

public class DriveDistanceWhileAquiring extends CommandGroup {
    public DriveDistanceWhileAquiring(double distance, double angle) {
        addParallel(new CloseOnCubeAtFront());
        addParallel(new AutoDriveForward(distance, angle));
    }
}
