package org.team639.robot;

import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.commands.drive.AutoDriveForward;
import org.team639.robot.commands.drive.AutoTurnToAngle;
import org.team639.robot.commands.drive.ShiftHigh;
import org.team639.robot.commands.drive.ShiftLow;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 * Part of 2018Robot.
 */
public class OI {

    public static final JoystickManager manager = new LogitechF310(0); // new DoubleLogitechAttack3(); // new LogitechF310(0);

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {
//        manager.mapButton(LogitechF310.Buttons.RB, new EjectCube(), JoystickManager.MappingType.WhileHeld);
//        manager.mapButton(LogitechF310.Buttons.LB, new IntakeCube(), JoystickManager.MappingType.WhileHeld);
//        manager.mapButton(LogitechF310.Buttons.A, new OpenAcquisition(), JoystickManager.MappingType.WhenPressed);
//        manager.mapButton(LogitechF310.Buttons.B, new CloseAcquisition(), JoystickManager.MappingType.WhenPressed);
//        manager.mapButton(LogitechF310.Buttons.X, new FloatAcquisition(), JoystickManager.MappingType.WhenPressed);
//        manager.mapButton(LogitechF310.Buttons.Y, new CloseOnCubeAtBack(), JoystickManager.MappingType.WhenPressed);

        manager.mapButton(LogitechF310.Buttons.LB, new ShiftHigh(), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.RB, new ShiftLow(), JoystickManager.MappingType.WhenPressed);

        manager.mapButton(LogitechF310.Buttons.A, new AutoDriveForward(10 * 12), JoystickManager.MappingType.WhenPressed);

        manager.mapButton(LogitechF310.Buttons.Y, new AutoTurnToAngle(270), JoystickManager.MappingType.WhenPressed);

        manager.mapButton(LogitechF310.Buttons.POVLeft, new AutoTurnToAngle(180), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVUp, new AutoTurnToAngle(90), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVRight, new AutoTurnToAngle(0), JoystickManager.MappingType.WhenPressed);
        manager.mapButton(LogitechF310.Buttons.POVDown, new AutoTurnToAngle(270), JoystickManager.MappingType.WhenPressed);
    }

    private OI() {
    }
}

