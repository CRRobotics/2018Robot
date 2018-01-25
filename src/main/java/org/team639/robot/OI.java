package org.team639.robot;

import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 * Created by Jack Greenberg <theProgrammerJack@gmail.com> on 1/25/2018.
 * Part of 2018Robot.
 */
public class OI {

    public static final JoystickManager manager = new LogitechF310(0); // new DoubleLogitechAttack3(); // new LogitechF310(0);

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {
    }

    private OI() {
    }
}

