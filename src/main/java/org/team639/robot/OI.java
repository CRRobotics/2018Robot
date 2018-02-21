package org.team639.robot;

import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.LogitechF310;
import org.team639.robot.commands.auto.OneCubeSwitch;
import org.team639.robot.commands.climbing.ClimberSequence;
import org.team639.robot.commands.cube.*;
import org.team639.robot.commands.drive.*;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;
import org.team639.robot.commands.lift.ZeroLift;

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
        controller.mapButton(LogitechF310.Buttons.LB, new EjectCube(), JoystickManager.MappingType.WhileHeld);
        controller.mapButton(LogitechF310.Buttons.RB, new IntakeCube(), JoystickManager.MappingType.WhileHeld);
        controller.mapButton(LogitechF310.Buttons.A, new OpenAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.B, new CloseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.X, new CloseOnCubeAtFront(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.X, new CloseAndIntake(), JoystickManager.MappingType.WhenReleased);
        controller.mapButton(LogitechF310.Buttons.Y, new ZeroLift(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.POVUp, new RaiseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.POVDown, new LowerAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.RightJoyPress, new ClimberSequence(), JoystickManager.MappingType.WhenPressed);

        MoveToSetPosition exchange_position = new MoveToSetPosition(LiftPosition.ExchangeHeight);
        controller.mapButton(LogitechF310.Buttons.POVRight, exchange_position, JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.POVRight, new Command() {
            @Override
            protected void execute() {
                exchange_position.cancel();
            }
            @Override
            protected boolean isFinished() {
                return true;
            }
        }, JoystickManager.MappingType.WhenReleased);

        MoveToSetPosition switch_position = new MoveToSetPosition(LiftPosition.SwitchHeight);
        controller.mapButton(LogitechF310.Buttons.POVLeft, switch_position, JoystickManager.MappingType.WhenPressed);
        controller.mapButton(LogitechF310.Buttons.POVLeft, new Command() {
            @Override
            protected void execute() {
                switch_position.cancel();
            }
            @Override
            protected boolean isFinished() {
                return true;
            }
        }, JoystickManager.MappingType.WhenReleased);

        drive.mapButton(LogitechF310.Buttons.RB, new ShiftHigh(), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.LB, new ShiftLow(), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.Y, new OneCubeSwitch(), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.A, new AutoDriveForward(12), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.B, new ZeroYaw(), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(LogitechF310.Buttons.X, new AutoDriveForward(10 * 12, 90), JoystickManager.MappingType.WhenPressed);
//
        drive.mapButton(LogitechF310.Buttons.POVLeft, new AutoTurnToAngle(180), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.POVUp, new AutoTurnToAngle(90), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.POVRight, new AutoTurnToAngle(0), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(LogitechF310.Buttons.POVDown, new AutoTurnToAngle(270), JoystickManager.MappingType.WhenPressed);
    }

    private OI() {
    }
}

