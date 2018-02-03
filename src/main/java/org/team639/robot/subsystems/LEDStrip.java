package org.team639.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class LEDStrip extends Subsystem {

    public enum LEDMode {
        Red,
        Green,
        Blue,
        Off
    }

    private FileOutputStream spif;
    private int length;

    public LEDStrip(int length) {
        this.length = length;
        try {
            spif = new FileOutputStream("/dev/spidev0.0");
        } catch (FileNotFoundException e) {
            System.out.println("LEDs failed to initialize!");
            e.printStackTrace();
        }
    }

    public void changeMode(LEDMode mode) {
        switch (mode) {
            case Red:
                break;
            case Green:
                break;
            case Blue:
                break;
            case Off:
                break;
        }
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public static class Color {
        private int brightness;
        private int red;
        private int green;
        private int blue;

        public Color(int red, int green, int blue) {
            this(0xff, red, green, blue);
        }

        public Color(int brightness, int red, int green, int blue) {
            this.brightness = brightness;
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public int getBrightness() {
            return brightness;
        }

        public void setBrightness(int brightness) {
            this.brightness = brightness;
        }

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int green) {
            this.green = green;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }

        public byte[] toByteArray() {
            byte[] arr = {(byte)brightness, (byte)red, (byte)green, (byte)blue};
            return arr;
        }
    }
}
