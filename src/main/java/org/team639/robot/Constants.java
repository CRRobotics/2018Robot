package org.team639.robot;

import org.team639.lib.TextSettings;

/**
 * Constants used by the robot.
 * Part of 2018Robot.
 */
public class Constants extends TextSettings{
    public static final boolean REAL = false;

    public static class DriveTrain {
        // TODO: Tune everything.
        public static final double HIGH_MIN_DRIVE_PERCENT = 0.09;

        public static final double HIGH_SPEED_RANGE = 33000;
        public static final double HIGH_DRIVE_P = 0.1;
        public static final double HIGH_DRIVE_I = 0;
        public static final double HIGH_DRIVE_D = 0;
        public static final double HIGH_DRIVE_F = 1023/HIGH_SPEED_RANGE;
        public static final double HIGH_ARCADE_RATE = 0.03;

        public static final double LOW_MIN_DRIVE_PERCENT = 0.09;

        public static final double LOW_SPEED_RANGE = 3400;
        public static final double LOW_DRIVE_P = 1.6;
        public static final double LOW_DRIVE_I = 0;
        public static final double LOW_DRIVE_D = 0;
        public static final double LOW_DRIVE_F = 0.25;
        public static final double LOW_ARCADE_RATE = 0.03;

        public static final double WHEEL_DIAMETER_INCHES = 4;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;

        public static final int ENC_TICKS_PER_ROTATION = 16384;
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

    //lift speed pid
    public static final double LIFT_P = 0;
    public static final double LIFT_I = 0;
    public static final double LIFT_D = 0;
    public static final double LIFT_F = 0;

    public static final double LIFT_MAX_SPEED = 1000; // TODO: Find real value.

    //lift position pid
    public static final double LIFT_POS_P = 0;
    public static final double LIFT_POS_I = 0;
    public static final double LIFT_POS_D = 0;
    public static final double LIFT_POS_MIN = 0;
    public static final double LIFT_POS_MAX =  0;
    public static final double LIFT_POS_RATE = 0;
    public static final double LIFT_POS_I_CAP = 0;
    public static final double LIFT_POS_TOLERANCE = 0;

    public static final int LIFT_MAX_HEIGHT = 395000; // TODO: FIND REAL VALUEs!!!!
    public static final int LIFT_SCALE_HEIGHT = 0;
    public static final int LIFT_SWITCH_HEIGHT = 0;
    public static final int LIFT_EXCHANGE_HEIGHT = 0;

//    public static final int LIFT_UPPER_LIMIT = 395000;
//    public static final int LIFT_LOWER_SLOW_DISTANCE = 50000;
    public static final int LIFT_SLOW_DISTANCE = 50000;


    public static final int LIFT_TOLERANCE = 200;

    public static final double DEFAULT_ACQ_SPEED = 0.5;

    static {
        //driving constants
        put("MIN_DRIVE_PERCENT", "" + 0.11);
        put("SPEED_RANGE", "" + 3400);
        put("DRIVE_P", "" + 1.6);
        put("DRIVE_I", "" + 0);
        put("DRIVE_D", "" + 0);
        put("DRIVE_F", "" + 0.25);
        put("ARCADE_RATE", "" + 0.03);

        //wheel constants
        put("WHEEL_DIAMETER_INCHES", "" + 6);
        put("ENC_TICKS_PER_ROTATION", "" + 4096);
        put("DRIVE_FORWARD_TOLERANCE", "" + 200);

        // field oriented drive turning constants
        put("FOT_P", "" + 0.03);
        put("FOT_I", "" + 0);
        put("FOT_D", "" + 0.15);
        put("FOT_MIN", "" + 0.11);
        put("FOT_MAX", "" + 0.25);
        put("FOT_RATE", "" + 0.015);
        put("FOT_I_CAP", "" + 0.2);
        put("FOT_TOLERANCE", "" + 2);

        //Turn To Angle constants
        put("TTA_P", "" + 0.007);
        put("TTA_I", "" + 0);
        put("TTA_D", "" + 0);
        put("TTA_MIN", "" + 0.09);
        put("TTA_MAX", "" + 1);
        put("TTA_RATE", "" + 0.01);
        put("TTA_I_CAP", "" + 0.2);
        put("TTA_TOLERANCE", "" + 2);

        //other
        put("JOYSTICK_DEADZONE", "" + 0.05);
    }
}