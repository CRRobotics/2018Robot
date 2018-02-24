package org.team639.robot.commands.drive.fancyauto;

import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.robot.Constants.DriveTrain.TICKS_PER_INCH;

/**
 * A class that approximates the position of the robot by monitoring the encoders and gyro.
 */
public class DriveTracker {
    private DriveTrain driveTrain;

    private double x;
    private double y;

    private double lastRightDist;
    private double lastLeftDist;

    private double lastAngle;

    public DriveTracker(double startX, double startY) {
        driveTrain = Robot.getDriveTrain();
        reset(startX, startY);
    }

    public void collect() {
        double l = driveTrain.getLeftEncPos() / TICKS_PER_INCH;
        double r = driveTrain.getRightEncPos() / TICKS_PER_INCH;
        double a = driveTrain.getRobotYaw();

        double angle = (a + lastAngle) / 2;
        lastAngle = a;

        double avg = ((l - lastLeftDist) + (r - lastRightDist)) / 2;

        lastRightDist = r;
        lastLeftDist = l;

        x += Math.cos(Math.toRadians(angle)) * avg;
        y += Math.sin(Math.toRadians(angle)) * avg;
    }

    public void reset(double x, double y) {
        this.x = x;
        this.y = y;

        lastRightDist = driveTrain.getRightEncPos() / TICKS_PER_INCH;
        lastLeftDist = driveTrain.getLeftEncPos() / TICKS_PER_INCH;

        lastAngle = driveTrain.getRobotYaw();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
