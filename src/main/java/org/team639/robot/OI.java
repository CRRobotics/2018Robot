package org.team639.robot;

import openrio.powerup.MatchData;
import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.commands.auto.OneCubeSwitch;
import org.team639.robot.commands.cube.*;
import org.team639.robot.commands.drive.*;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 * Part of 2018Robot.
 */
public class OI {

    public static final JoystickManager drive = new LogitechF310(0); // new DoubleLogitechAttack3(); // new LogitechF310(0);
    public static final JoystickManager controller = new LogitechF310(1);

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {
        controller.mapButton(LogitechF310.Buttons.RB, new EjectCube(), JoystickManager.MappingType.WhileHeld);
        controller.mapButton(LogitechF310.Buttons.LB, new IntakeCube(), JoystickManager.MappingType.WhileHeld);
        controller.mapButton(LogitechF310.Buttons.A, new OpenAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.B, new CloseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.X, new FloatAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.Y, new RaiseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.POVUp, new RaiseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.POVDown, new LowerAcquisition(), JoystickManager.MappingType.WhenPressed);

        drive.mapButton(LogitechF310.Buttons.LB, new ShiftHigh(), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.RB, new ShiftLow(), JoystickManager.MappingType.WhenPressed);

//        drive.mapButton(LogitechF310.Buttons.A, new AutoDriveForward(120), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(LogitechF310.Buttons.B, new ZeroYaw(), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(LogitechF310.Buttons.X, new OneCubeSwitch(), JoystickManager.MappingType.WhenPressed);
//
//        drive.mapButton(LogitechF310.Buttons.POVLeft, new AutoTurnToAngle(180), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(LogitechF310.Buttons.POVUp, new AutoTurnToAngle(90), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(LogitechF310.Buttons.POVRight, new AutoTurnToAngle(0), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(LogitechF310.Buttons.POVDown, new AutoTurnToAngle(270), JoystickManager.MappingType.WhenPressed);
    }

    private OI() {
    }
}

