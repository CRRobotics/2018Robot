package org.team639.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.robot.Robot;
import org.team639.robot.subsystems.DriveTrain;

import static org.team639.lib.math.AngleMath.*;
import static org.team639.robot.Constants.DriveTrain.*;


/**
 * Turns the robot to a specified angle.
 * Constants for this routine are in the static Constants.Auto class and are prefixed with TTA_
 */
public class AutoTurnToAngleR extends Command {

    private DriveTrain driveTrain;

    private double angle;
    private boolean done;
    private double gSpeed;
    private double lSpeed;
    private double rSpeed;
    private double maxTol;
    private double minTol;
    private int sign;


    public AutoTurnToAngleR(double pAngle, double speed) {
        super("AutoTurnToAngleR");
        driveTrain = Robot.getDriveTrain();
        requires(driveTrain);
        angle = pAngle % 360;

        gSpeed = Math.abs(speed);
        lSpeed = gSpeed;
        rSpeed = gSpeed;
        maxTol = 15;
        minTol = 3;
        sign = shortestDirection(driveTrain.getRobotYaw(), angle);


    }

    protected void initialize() {
        done = gSpeed < MIN_DRIVE_PERCENT;
        if(!done)
            done = Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) <= minTol;


        driveTrain.setSpeedsPercent(0, 0);
        driveTrain.setCurrentControlMode(ControlMode.Velocity);

    }

    protected void execute() {
        sign = shortestDirection(driveTrain.getRobotYaw(), angle);
        if(Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) > maxTol) {
            lSpeed *= sign;
            rSpeed *= sign;
            lSpeed *= -1;
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }
//            account for MIN_DRIVE_PERCENT
//            else if(Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) > minTol) {
//                double multiplier = (Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) - minTol) / maxTol;
//                lSpeed -= MIN_DRIVE_PERCENT;
//                rSpeed -= MIN_DRIVE_PERCENT;
//                lSpeed *= multiplier;
//                rSpeed *= multiplier;
//                lSpeed += MIN_DRIVE_PERCENT;
//                rSpeed += MIN_DRIVE_PERCENT;
//                lSpeed *= sign;
//                rSpeed *= sign;
//                lSpeed *= -1;
//                driveTrain.setSpeedsPercent(lSpeed, rSpeed);
//            }
        else if(Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) > minTol) {
            double multiplier = (Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) - minTol) / maxTol;
            lSpeed *= multiplier * sign;
            rSpeed *= multiplier * sign;
            lSpeed *= -1;
            driveTrain.setSpeedsPercent(lSpeed, rSpeed);
        }
//            else
//                driveTrain.setSpeedsPercent(0, 0);

        done = gSpeed < MIN_DRIVE_PERCENT;
        if(!done)
            done = Math.abs(shortestAngle(driveTrain.getRobotYaw(), angle)) <= minTol;

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
