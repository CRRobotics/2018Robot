package org.team639.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.RobotMap;

/**
 * The raising subsystem.
 * Responsible for picking up other robots.
 */
public class RaisingSystem extends Subsystem {
    private Solenoid leftPiston;
    private Solenoid rightPiston;
//
//    public RaisingSystem() {
//        leftPiston = RobotMap.getRaisingLeft();
//        rightPiston = RobotMap.getRaisingRight();
//    }

    /**
     * Returns whether or not the left piston is extended.
     * @return Whether or not the left piston is extended.
     */
    public boolean isLeftPistonExtended() {
        return leftPiston.get();
    }

    /**
     * Returns whether or not the right piston is extended.
     * @return Whether or not the right piston is extended.
     */
    public boolean isRightExtended() {
        return rightPiston.get();
    }

    /**
     * Sets the position of the left piston.
     * @param extended Whether the piston should be extended or not.
     */
    public void setLeftPistonExtended(boolean extended) {
        leftPiston.set(extended);
    }

    /**
     * Sets the position of the right piston.
     * @param extended Whether the piston should be extended or not.
     */
    public void setRightPistonExtended(boolean extended) {
        rightPiston.set(extended);
    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {
    }
}
