package org.team639.robot.commands.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.robot.OI;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.Lift;

import static org.team639.robot.Constants.*;

/**
 * Allows for movement of the lift using joysticks.
 * @see Lift
 */
public class MoveLiftWithJoystick extends Command {
    private Lift lift;

    public MoveLiftWithJoystick() {
        super("MoveLiftWithJoystick");
        lift = Robot.getLift();
        requires(lift);
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        if (!lift.encoderPresent()) lift.setCurrentControlMode(ControlMode.PercentOutput);
        else lift.setCurrentControlMode(ControlMode.PercentOutput); // TODO: Change back to Velocity
        lift.setSpeedPercent(0);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        double yVal = OI.controller.getLeftStickY();
        SmartDashboard.putNumber("left stick y", yVal);
        if (Math.abs(yVal) < JOYSTICK_DEADZONE) yVal = 0;
        double multiplier = 1;
        if (LIFT_MAX_HEIGHT - lift.getEncPos() <= LIFT_SLOW_DISTANCE) multiplier = (LIFT_MAX_HEIGHT - lift.getEncPos()) / LIFT_SLOW_DISTANCE;
        else if (lift.getEncPos() <= LIFT_SLOW_DISTANCE) multiplier = lift.getEncPos() / LIFT_SLOW_DISTANCE;
        double speed = yVal * multiplier;

//        if ((lift.isAtSecondStageLimit() && speed > 0) || (lift.encoderPresent() && (lift.getEncPos() > LIFT_MAX_HEIGHT - LIFT_TOLERANCE) && speed > 0)) speed = 0;
//        if (lift.isAtLowerLimit() && speed < 0) speed = 0;

        if (speed == 0) lift.setBrake(true);
        else lift.setBrake(false);

        lift.setSpeedPercent(speed);
    }


    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}
