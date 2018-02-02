package org.team639.robot.commands.lift;

import static org.team639.robot.Constants.*;

public enum LiftPositions {
    FullyLowered(0),
    FullyExtended(LIFT_MAX_HEIGHT),
    ScaleHeight(LIFT_SCALE_HEIGHT),
    SwitchHeight(LIFT_SWITCH_HEIGHT),
    ExchangeHeight(LIFT_EXCHANGE_HEIGHT);

    private final double percentHeight;

    LiftPositions(double percentHeight) {
        this.percentHeight = percentHeight;
    }

    public double getPercentHeight() {
        return percentHeight;
    }
}
