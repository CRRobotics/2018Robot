package org.team639.robot.commands.drive.fancyauto;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.Auto.*;

/**
 * A Command that drives to a point limiting only acceleration, max speed, and min speed. DOES NOT STOP THE MOTORS WHEN FINISHED.
 * Be careful. Please.
 */
public class AutoDriveStart extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private boolean done = false;

    private double x;
    private double y;

    private double speed;

    private double angle;

    private PID turnPID;

    public AutoDriveStart(double x, double y) {
        super("AutoDriveForward");
        requires(driveTrain);

        this.x = x;
        this.y = y;

        double sx = Robot.getTrackedX();
        double sy = Robot.getTrackedY();

        this.angle = Math.toDegrees(Math.atan2(y - sy, x - sx));
    }

    protected void initialize() {
        done = false;

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);
        driveTrain.setCurrentGear(DriveTrain.DriveGear.Low);

        speed = FANCY_MIN;
//
        turnPID = new PID(FANCY_AC_P, FANCY_AC_I, FANCY_AC_D, FANCY_AC_MIN, FANCY_AC_MAX, FANCY_AC_RATE, FANCY_AC_TOLERANCE, FANCY_AC_I_CAP);
    }

    protected void execute() {

        double dist = Math.sqrt(Math.pow(Robot.getTrackedX() - x, 2) + Math.pow(Robot.getTrackedY() - y, 2));

        if (speed < FANCY_MAX) speed += FANCY_RATE;
        if (speed > FANCY_MAX) speed = FANCY_MAX;

        double sx = Robot.getTrackedX();
        double sy = Robot.getTrackedY();
        this.angle = Math.toDegrees(Math.atan2(y - sy, x - sx));

        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
        double output = turnPID.compute(error);
        driveTrain.setSpeedsPercent(speed - output, speed + output);
        done = (Math.abs(dist) < FANCY_TOLERANCE);
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {

    }
}
