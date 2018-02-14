package org.team639.robot.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.AngleMath;
import org.team639.lib.math.PID;
import org.team639.robot.Robot;
import org.team639.robot.Constants;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.Auto.*;
import static org.team639.robot.Constants.DriveTrain.*;

/**
 * Command to autonomously drive forward a specified distance
 */
public class AutoDriveForward extends Command {
    private DriveTrain driveTrain = Robot.getDriveTrain();

    private boolean done = false;

    private double targetDistance;
    private int targetTicks;
    private double rSpeed;
    private double lSpeed;
    private double startSlow;
    private double lTickDiff;
    private double rTickDiff;
    private double targetLeft;
    private double targetRight;
    private double minSpeed;

    private double angle;

    private PID pid;
    private PID turnPID;

    public AutoDriveForward(double distance) {
        super("AutoDriveForward");
        requires(driveTrain);

        targetDistance = distance;
        targetTicks = (int)(targetDistance * Constants.DriveTrain.TICKS_PER_INCH);
    }

    protected void initialize() {
        done = false;

        angle = driveTrain.getRobotYaw();

        SmartDashboard.putNumber("target", targetTicks);


        targetLeft = driveTrain.getLeftEncPos() + targetTicks;
        targetRight = driveTrain.getRightEncPos() + targetTicks;

        SmartDashboard.putNumber("startError", targetLeft - driveTrain.getLeftEncPos());

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);
        // Dominic "DJ" Towns was here.
        driveTrain.setCurrentGear(DriveTrain.DriveGear.High);
//        driveTrain.setRampRate(1); // TODO: Maybe change this.

        double p = SmartDashboard.getNumber("drive p", AC_P);
        double i = SmartDashboard.getNumber("drive i", AC_I);
        double d = SmartDashboard.getNumber("drive d", AC_D);
        double rate = SmartDashboard.getNumber("rate", AC_RATE);
        double tolerance = SmartDashboard.getNumber("tolerance", AC_TOLERANCE);
        double min = SmartDashboard.getNumber("min", AC_MIN);
        double max = SmartDashboard.getNumber("max", AC_MAX);
        pid = new PID(ADF_P, ADF_I, ADF_D, ADF_MIN, ADF_MAX, ADF_RATE, ADF_TOLERANCE, ADF_I_CAP);
//
        turnPID = new PID(p, i, d, min, max, rate, tolerance, AC_I_CAP);
    }

    protected void execute() {
        lTickDiff = targetLeft - driveTrain.getLeftEncPos();
        rTickDiff = targetRight - driveTrain.getRightEncPos();

        double val = pid.compute(lTickDiff);
//        SmartDashboard.putNumber("error", lTickDiff);
        // TODO: Re-enable angle correction and tune it.
        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
        double output = turnPID.compute(error);
//        System.out.println((val - output) + ", " + (val + output));
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
        driveTrain.setSpeedsPercent(0, 0);
    }
}
