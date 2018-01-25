package org.team639.lib.math;

/**
 * Contains various useful methods for handling angles.
 */
public class AngleMath {

    /**
     * Returns the shortest way to get from one angle to another in degrees.
     * @param start The starting angle
     * @param end The ending angle
     * @return The shortest angle between start and end
     */
    public static double shortestAngle(double start, double end) {
        start %= 360;
        end %= 360;

        double first = end - start;
        double second = (360 - Math.abs(first));
        if (first > 0) second *= -1;
        return Math.abs(first) < Math.abs(second) ? first : second;
    }

    /**
     * Determines the direction of the shortest distance between two angles in degrees. -1 is clockwise, 1 is counterclockwise, and 0 is a draw
     * @param start The starting angle in degrees
     * @param end The ending angle in degrees
     * @return The direction of the shortest distance between start and end
     */
    public static int shortestDirection(double start, double end) {
        double angle = shortestAngle(start, end);
        if (angle < 0) {
            return -1;
        } else if (angle > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
