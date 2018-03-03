package org.team639.robot.commands.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.controls.LogitechF310;
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
        else lift.setCurrentControlMode(ControlMode.Velocity); // TODO: Change back to Velocity
        lift.setSpeedPercent(0);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
//        double p = SmartDashboard.getNumber("drive p", LIFT_P);
//        double i = SmartDashboard.getNumber("drive i", LIFT_I);
//        double d = SmartDashboard.getNumber("drive d", LIFT_D);
//        lift.setPID(p, i, d, LIFT_F);

        double yVal = OI.controller.getLeftStickY();
        SmartDashboard.putNumber("left stick y", yVal);
        double sign = yVal < 0 ? -1 : 1;
        if (Math.abs(yVal) < CONTROLLER_JOYSTICK_DEADZONE) yVal = 0;
        else yVal = (yVal - sign * CONTROLLER_JOYSTICK_DEADZONE) / (1 - CONTROLLER_JOYSTICK_DEADZONE);
        double multiplier = 1 - (0.8 * OI.controller.getControllerAxis(LogitechF310.ControllerAxis.LeftTrigger));
        double lift_pos = lift.getEncPos();
        double speed = yVal;
        if (speed < 0) {
            //speed *= 0.8;
            if(lift_pos < LIFT_BOTTOM_SLOW_DISTANCE) speed *= lift_pos / LIFT_BOTTOM_SLOW_DISTANCE * .9 + .1;
        }

        /*if (speed > 0 && LIFT_MAX_HEIGHT - lift_pos < LIFT_TOP_SLOW_DISTANCE) {
            speed *= (LIFT_MAX_HEIGHT - lift_pos) / LIFT_TOP_SLOW_DISTANCE * .9 + .1;
        }*/
        speed *= multiplier;
//        if(lift.getEncPos() < 2500)
//        if ((lift.isAtSecondStageLimit() && speed > 0) || (lift.encoderPresent() && (lift.getEncPos() > LIFT_MAX_HEIGHT - LIFT_TOLERANCE) && speed > 0)) speed = 0;
//        if (lift.isAtLowerLimit() && speed < 0) speed = 0;

        if (speed == 0 /*&& lift.getEncVelocity() < LIFT_THRESHOLD*/) lift.setBrake(true);
        else lift.setBrake(false);
        SmartDashboard.putNumber("lift speed percent", speed);
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
