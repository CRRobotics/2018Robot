package org.team639.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import org.team639.lib.controls.JoystickManager;
import org.team639.lib.controls.XBoxController;
import org.team639.robot.commands.climbing.ClimberDeploySequence;
import org.team639.robot.commands.cube.*;
import org.team639.robot.commands.drive.*;
import org.team639.robot.commands.lift.LiftPosition;
import org.team639.robot.commands.lift.MoveToSetPosition;
import org.team639.robot.commands.lift.ReturnLiftControl;
import org.team639.robot.commands.lift.ZeroLift;

import static org.team639.lib.controls.XBoxController.ControllerAxis.LeftTrigger;

/**
 * Operator Interface
 * Manages the interaction between the drive team and the robot
 * Part of 2018Robot.
 */
public class OI {

    public static final JoystickManager drive = new XBoxController(0); // new DoubleLogitechAttack3(); // new LogitechF310(0);
    public static final JoystickManager controller = new XBoxController(1);

    /**
     * Maps all of the buttons.
     * THIS MUST BE RUN AT THE END OF robotInit in Robot.java!!!
     */
    public static void mapButtons() {

        // Special connditions
        Button climbTrigger = new Button() {
            @Override
            public boolean get() {
                return controller.getControllerAxis(XBoxController.ControllerAxis.RightTrigger) > 0.9 && controller.getRightStickY() > 0.9;
            }
        };

        Button driveAcqRaise = new Button() {
            @Override
            public boolean get() {
                return drive.getControllerAxis(LeftTrigger) >= 0.25;
            }
        };

        controller.mapButton(XBoxController.Buttons.LB, new EjectCube(), JoystickManager.MappingType.WhileHeld);
        controller.mapButton(XBoxController.Buttons.RB, new IntakeCube(), JoystickManager.MappingType.WhileHeld);
        controller.mapButton(XBoxController.Buttons.A, new OpenAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.B, new CloseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.X, new CloseOnCubeAtFront(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.X, new CloseAndIntake(), JoystickManager.MappingType.WhenReleased);
        controller.mapButton(XBoxController.Buttons.Y, new ZeroLift(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.LeftJoyPress, new ReturnLiftControl(), JoystickManager.MappingType.WhenReleased);
        controller.mapButton(XBoxController.Buttons.RightJoyPress, new FloatAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.POVUp, new RaiseAcquisition(), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.POVDown, new LowerAcquisition(), JoystickManager.MappingType.WhenPressed);
        mapCondition(climbTrigger, new ClimberDeploySequence(), JoystickManager.MappingType.WhenPressed);
//        mapCondition(climbTrigger, new RaiseAndClose(), JoystickManager.MappingType.WhenReleased);

        controller.mapButton(XBoxController.Buttons.POVRight, new MoveToSetPosition(LiftPosition.ExchangeHeight), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.POVRight, new ReturnLiftControl(), JoystickManager.MappingType.WhenReleased);

        controller.mapButton(XBoxController.Buttons.POVLeft, new MoveToSetPosition(LiftPosition.SwitchHeight), JoystickManager.MappingType.WhenPressed);
        controller.mapButton(XBoxController.Buttons.POVLeft, new ReturnLiftControl(), JoystickManager.MappingType.WhenReleased);

        drive.mapButton(XBoxController.Buttons.RB, new SetAutoShift(true), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(XBoxController.Buttons.LB, new ShiftLow(), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(XBoxController.Buttons.Y, new FancyTest(), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(XBoxController.Buttons.A, new FancyTest2(), JoystickManager.MappingType.WhenPressed);
//        drive.mapButton(XBoxController.Buttons.B, new ZeroYaw(), JoystickManager.MappingType.WhenPressed);

        drive.mapButton(XBoxController.Buttons.POVLeft, new AutoTurnToAngle(180), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(XBoxController.Buttons.POVUp, new AutoTurnToAngle(90), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(XBoxController.Buttons.POVRight, new AutoTurnToAngle(0), JoystickManager.MappingType.WhenPressed);
        drive.mapButton(XBoxController.Buttons.POVDown, new AutoTurnToAngle(270), JoystickManager.MappingType.WhenPressed);

//        mapCondition(driveAcqRaise, new ToggleAcquisitionRaised(), JoystickManager.MappingType.WhenPressed);
    }


    /**
     * Maps the specified command to the specified button
     * @param condition A custom condition to map to
     * @param cmd The command to map
     * @param type The type of mapping
     */
    public static void mapCondition(Button condition, Command cmd, JoystickManager.MappingType type) {
        switch (type) {
            case WhenPressed:
                condition.whenPressed(cmd);
                break;
            case WhenReleased:
                condition.whenReleased(cmd);
                break;
            case WhileHeld:
                condition.whileHeld(cmd);
                break;
            case CancelWhenPressed:
                condition.cancelWhenPressed(cmd);
                break;
            case ToggleWhenPressed:
                condition.toggleWhenPressed(cmd);
                break;
        }
    }

    private OI() {
    }
}

