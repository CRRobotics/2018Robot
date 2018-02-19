package org.team639.robot.commands.lift;

import static org.team639.robot.Constants.*;

/**
 * The possible preset positions that the lift can travel to.
 */
public enum LiftPosition {
    FullyLowered(0),
    FullyExtended(LIFT_MAX_HEIGHT),
    ScaleHeight(LIFT_SCALE_HEIGHT),
    SwitchHeight(LIFT_SWITCH_HEIGHT),
    ExchangeHeight(LIFT_EXCHANGE_HEIGHT),
    ClimbHeight(LIFT_CLIMB_HEIGHT);

    private final int encTicks;

    LiftPosition(int encTicks) {
        this.encTicks = encTicks;
    }

    /**
     * Returns the number of encoder ticks of the position.
     * @return The number of encoder ticks of the position.
     */
    public int getEncTicks() {
        return encTicks;
    }
}
