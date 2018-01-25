package org.team639.robot;

/**
 * Created by Jack Greenberg <theProgrammerJack@gmail.com> on 1/25/2018.
 * Part of 2018Robot.
 */
public class Constants {
    public static class DriveTrain {
        public static final double MIN_DRIVE_PERCENT = 0.09;

        public static final double SPEED_RANGE = 3400;
        public static final double DRIVE_P = 1.6;
        public static final double DRIVE_I = 0;
        public static final double DRIVE_D = 0;
        public static final double DRIVE_F = 0.25;
        public static final double ARCADE_RATE = 0.03;

        public static final double WHEEL_DIAMETER_INCHES = 6;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;

        public static final int ENC_TICKS_PER_ROTATION = 4096;
        public static final double TICKS_PER_INCH = ENC_TICKS_PER_ROTATION / WHEEL_CIRCUMFERENCE_INCHES;

        public static final double DRIVE_FORWARD_TOLERANCE = 200;


        // field oriented drive turning constants
        public static final double FOT_P = 0.03;
        public static final double FOT_I = 0;
        public static final double FOT_D = 0.15;
        public static final double FOT_MIN = 0.11;
        public static final double FOT_MAX = 0.25;
        public static final double FOT_RATE = 0.015;
        public static final double FOT_I_CAP = 0.2;
        public static final double FOT_TOLERANCE = 2;
    }

    public static class Auto {
        //Turn To Angle constants
        public static final double TTA_P = 0.007;
        public static final double TTA_I = 0;
        public static final double TTA_D = 0;
        public static final double TTA_MIN = 0.09;
        public static final double TTA_MAX = 1;
        public static final double TTA_RATE = 0.01;
        public static final double TTA_I_CAP = 0.2;
        public static final double TTA_TOLERANCE = 2;
    }

    public static final double JOYSTICK_DEADZONE = 0.05;
}
