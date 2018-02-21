package org.team639.robot;

/**
 * Constants used by the robot.
 * Part of 2018Robot.
 */
public class Constants {
    public static final boolean REAL = false;

    public static class Acquisition {
        public static final double DEFAULT_ACQ_SPEED = 0.4;
        public static final double MONITOR_CUBE_TIMEOUT = 1500;
        public static final double MONITOR_CUBE_SPEED = 0.2;

    }

    public static class DriveTrain {
        // TODO: Tune everything.
        public static final double HIGH_MIN_DRIVE_PERCENT = 0.09;

        public static final double HIGH_SPEED_RANGE = 33000 * 0.95;
        public static final double HIGH_DRIVE_P = 0.1;
        public static final double HIGH_DRIVE_I = 0;
        public static final double HIGH_DRIVE_D = 0;
        public static final double HIGH_DRIVE_F = 1023/HIGH_SPEED_RANGE;
        public static final double HIGH_ARCADE_RATE = 0.03;

        //public static final double IDEAL_SHIFT_SPEED = 1.926*39.37/4.0/(2*Math.PI)*4096*0.1; // ticks per 100ms?
        public static final double IDEAL_SHIFT_SPEED = 5000;

        public static final double LOW_MIN_DRIVE_PERCENT = 0.09;

        public static final double LOW_SPEED_RANGE = 18000 * .95;
        public static final double LOW_DRIVE_P = 0.1;
        public static final double LOW_DRIVE_I = 0;
        public static final double LOW_DRIVE_D = 0;
        public static final double LOW_DRIVE_F = 1023/LOW_SPEED_RANGE;
        public static final double LOW_ARCADE_RATE = 0.03;

        public static final double WHEEL_DIAMETER_INCHES = 4;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;

        public static final double ENC_TICKS_PER_ROTATION = 4096 * 3 * 54 / 30; // Quad encoder has 1024 ticks (* 4 = 4096). 3 and 54/30 are gear ratios.
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

        // Angle correction constants
        public static final double AC_P = 0.0035;
        public static final double AC_I = 0;
        public static final double AC_D = 0;
        public static final double AC_MIN = 0;
        public static final double AC_MAX = 0.03;
        public static final double AC_RATE = 0.01;
        public static final double AC_I_CAP = 0.2;
        public static final double AC_TOLERANCE = 1;

    }

    public static class Auto {
        //Turn To Angle constants
        public static final double TTA_P = 0.007;
        public static final double TTA_I = 0;
        public static final double TTA_D = 0;
        public static final double TTA_MIN = 0.09;
        public static final double TTA_MAX = 0.3;
        public static final double TTA_RATE = 0.01;
        public static final double TTA_I_CAP = 0.2;
        public static final double TTA_TOLERANCE = 2;

        public static final double ADF_P = 0.0000028;
        public static final double ADF_I = 0;
        public static final double ADF_D = 0;
        public static final double ADF_MIN = 0.09; // 0.045;
        public static final double ADF_MAX = 0.5;
        public static final double ADF_RATE = 0.005;
        public static final double ADF_I_CAP = 0.2;
        public static final double ADF_TOLERANCE = 800;
    }

    public static final double JOYSTICK_DEADZONE = 0.05;
    public static final double CONTROLLER_JOYSTICK_DEADZONE = 0.2;


    public static final int LIFT_MAX_SPEED = 1700;

    //lift speed pid
    public static final double LIFT_P = 0;
    public static final double LIFT_I = 0;
    public static final double LIFT_D = 0;
    public static final double LIFT_F = 1023.0 / LIFT_MAX_SPEED;

    public static final double LIFT_RATE = .03;
    public static final double LIFT_MIN = .15;
    public static final double LIFT_MAX = .6;

    public static final double LIFT_POS_P = 0.00018;
    public static final double LIFT_POS_I = 0;
    public static final double LIFT_POS_D = 0;


    public static final int LIFT_MAX_HEIGHT = 40700; // TODO: THIS IS PROBABLY THE REAL VALUE BUT MAYBE NOT
    public static final int LIFT_SCALE_HEIGHT = LIFT_MAX_HEIGHT;
    public static final int LIFT_SWITCH_HEIGHT = 13474;
    public static final int LIFT_EXCHANGE_HEIGHT = 2500;//2807; 950 is mark's guess
    public static final int LIFT_CLIMB_HEIGHT = 0; //TODO FIND HEIGHT

//    public static final int LIFT_UPPER_LIMIT = 395000;
//    public static final int LIFT_LOWER_SLOW_DISTANCE = 50000;
    public static final int LIFT_TOP_SLOW_DISTANCE = 1000;
    public static final int LIFT_BOTTOM_SLOW_DISTANCE = 4000;


    public static final double LIFT_ZERO_SPEED = 0.15;

    public static final int LIFT_TOLERANCE = 200;

}