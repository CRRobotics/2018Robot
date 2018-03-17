package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team639.robot.Robot;

public class SideSwitchChance extends CommandGroup {
    public SideSwitchChance() {
        AutoUtils.OwnedSide switchSide = AutoUtils.getOwnedSide(AutoUtils.GameFeature.SwitchNear);
        StartingPosition position = Robot.getStartingPosition();

        if ((switchSide == AutoUtils.OwnedSide.Right && position == StartingPosition.Right) || (switchSide == AutoUtils.OwnedSide.Left && position == StartingPosition.Left)) {
            addSequential(new SwitchFromSameSide());
        } else {
            addSequential(new AutoCrossLine());
        }
    }
}
