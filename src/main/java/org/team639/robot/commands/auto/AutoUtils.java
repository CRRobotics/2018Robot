package org.team639.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.HashMap;
import java.util.Map;

/**
 * Tools for auto.
 */
public class AutoUtils {
    private static String gameData = null;
    private static Map<GameFeature, OwnedSide> info;

    public enum GameFeature {
        SwitchNear,
        Scale,
        SwitchFar
    }

    public enum OwnedSide {
        Left,
        Right,
        Unknown
    }

    /**
     * Returns a map containing game features and the sides that you own.
     * @return A map containing game features and the sides that you own.
     */
    public static Map<GameFeature, OwnedSide> getMatchData() {
        if (gameData == null) {
            String msg = DriverStation.getInstance().getGameSpecificMessage();
            int retries = 0;
            // Wait and retry in case the message isn't available yet,
            while ((msg == null || msg.length() < 3) && retries < 100) {
                msg = DriverStation.getInstance().getGameSpecificMessage();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // I don't think we care about this.
                }
                retries++;
            }
            info = new HashMap<>();
            if (msg == null || msg.length() < 1) {
                info.put(GameFeature.SwitchNear, OwnedSide.Unknown);
                info.put(GameFeature.Scale, OwnedSide.Unknown);
                info.put(GameFeature.SwitchFar, OwnedSide.Unknown);
            } else {
                switch (msg.charAt(0)) { // Closest switch.
                    case 'l':
                    case 'L':
                        info.put(GameFeature.SwitchNear, OwnedSide.Left);
                        break;
                    case 'r':
                    case 'R':
                        info.put(GameFeature.SwitchNear, OwnedSide.Right);
                        break;
                    default:
                        info.put(GameFeature.SwitchNear, OwnedSide.Unknown);
                        break;
                }
                if (msg.length() >= 2) {
                    switch (msg.charAt(1)) { // Scale
                        case 'l':
                        case 'L':
                            info.put(GameFeature.Scale, OwnedSide.Left);
                            break;
                        case 'r':
                        case 'R':
                            info.put(GameFeature.Scale, OwnedSide.Right);
                            break;
                        default:
                            info.put(GameFeature.Scale, OwnedSide.Unknown);
                            break;
                    }
                } else {
                    info.put(GameFeature.Scale, OwnedSide.Unknown);
                }

                if (msg.length() >= 3) {
                    switch (msg.charAt(2)) { // Far switch
                        case 'l':
                        case 'L':
                            info.put(GameFeature.SwitchFar, OwnedSide.Left);
                            break;
                        case 'r':
                        case 'R':
                            info.put(GameFeature.SwitchFar, OwnedSide.Right);
                            break;
                        default:
                            info.put(GameFeature.SwitchFar, OwnedSide.Unknown);
                            break;
                    }
                } else {
                    info.put(GameFeature.SwitchFar, OwnedSide.Unknown);
                }
            }
        }
        return info;
    }

    /**
     * Returns the side that you own of the specified game feature.
     * @param feature The game feature to retrieve info about.
     * @return The side of the feature that you own.
     */
    public static OwnedSide getOwnedSide(GameFeature feature) {
        if (info == null) getMatchData();
        return info.get(feature);
    }
}
