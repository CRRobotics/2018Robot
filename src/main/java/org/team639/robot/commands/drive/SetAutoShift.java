package org.team639.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.team639.robot.Robot;

public class SetAutoShift extends InstantCommand {
    private boolean shift;

    public SetAutoShift(boolean shift) {
        this.shift = shift;
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Robot.getDriveTrain().setAutoShift(shift);
    }
}
