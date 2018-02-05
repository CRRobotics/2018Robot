package org.team639.robot.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team639.lib.math.PID;
import org.team639.robot.Robot;
import org.team639.robot.Constants;
import org.team639.robot.subsystems.DriveTrain;

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

        targetLeft = driveTrain.getLeftEncPos() + targetTicks;
        targetRight = driveTrain.getRightEncPos() + targetTicks;

        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);
        driveTrain.setCurrentGear(DriveTrain.DriveGear.High);
        driveTrain.setRampRate(1); // TODO: Maybe change this.

        double p = SmartDashboard.getNumber("drive p", Constants.DriveTrain.HIGH_DRIVE_P);
        double i = SmartDashboard.getNumber("drive i", Constants.DriveTrain.HIGH_DRIVE_I);
        double d = SmartDashboard.getNumber("drive d", Constants.DriveTrain.HIGH_DRIVE_D);
        double rate = SmartDashboard.getNumber("rate", 0.1);
        double tolerance = SmartDashboard.getNumber("tolerance", 200);
        double min = SmartDashboard.getNumber("min", 0.2);
        double max = SmartDashboard.getNumber("max", 0.5);
        pid = new PID(p, i, d, min, max, rate, tolerance, 0.2);
//
        turnPID = new PID(FOT_P, FOT_I, FOT_D, FOT_MIN, FOT_MAX, FOT_RATE, FOT_TOLERANCE, FOT_I_CAP);
    }

    protected void execute() {
        lTickDiff = Math.abs(targetLeft - driveTrain.getLeftEncPos());
        rTickDiff = Math.abs(targetRight - driveTrain.getRightEncPos());

        double val = pid.compute(lTickDiff);
        System.out.println(lTickDiff + ", " + val);
        // TODO: Re-enable angle correction and tune it.
//        double error = AngleMath.shortestAngle(driveTrain.getRobotYaw(), angle);
//        double output = turnPID.compute(error);
////        System.out.println((val - output) + ", " + (val + output));
        driveTrain.setSpeedsPercent(val/* - output */, val /*+ output*/);
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
