package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Constants;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.Auto.*;
import static org.team639.robot.Constants.DriveTrain.*;

/**
 * Drives forward until certain distance is sensed.
 */
public class DriveToDistanceAwayFront extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private boolean done = false;

    private double targetDistance;

    private double angle;

    private PID pid;
    private PID turnPID;

    private boolean prevAutoShiftState;

    public DriveToDistanceAwayFront(double distance) {
        super("DriveToDistanceAwayFront");
        requires(driveTrain);

        targetDistance = distance;
    }

    protected void initialize() {
        done = false;

        prevAutoShiftState = driveTrain.getAutoShift();
        driveTrain.setAutoShift(false);
        driveTrain.setCurrentGear(DriveTrain.DriveGear.High);

        angle = driveTrain.getRobotYaw();

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

        double p = SmartDashboard.getNumber("drive p", DTDA_P);
        double i = SmartDashboard.getNumber("drive i", DTDA_I);
        double d = SmartDashboard.getNumber("drive d", DTDA_D);
        double rate = SmartDashboard.getNumber("rate", DTDA_RATE);
        double tolerance = SmartDashboard.getNumber("tolerance", DTDA_TOLERANCE);
        double min = SmartDashboard.getNumber("min", DTDA_MIN);
        double max = SmartDashboard.getNumber("max", DTDA_MAX);
        pid = new PID(p, i, d, min, max, rate, tolerance, DTDA_I_CAP);
//
        turnPID = new PID(AC_P, AC_I, AC_D, AC_MIN, AC_MAX, AC_RATE, AC_TOLERANCE, AC_I_CAP);
    }

    protected void execute() {
//        SmartDashboard.putBoolean("running", true);

        double diff = driveTrain.getFrontDistance() - targetDistance;
//        SmartDashboard.putNumber("diff", diff);
        double val = pid.compute(diff);
//
        double angleError = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
        double output = turnPID.compute(angleError);
        driveTrain.setSpeedsPercent(val - output, val + output);
        done = (val == 0);
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
//        SmartDashboard.putBoolean("running", false);
        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setAutoShift(prevAutoShiftState);
    }
}