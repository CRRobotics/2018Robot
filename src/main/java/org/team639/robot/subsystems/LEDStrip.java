package org.team639.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.robot.commands.leds.LEDRefresh;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LEDStrip extends Subsystem {

    public enum LEDMode {
        Red,
        Green,
        Blue,
        Off
    }

    private final byte[] spiStart = {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
    private final byte[] spiEnd = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};

    private FileOutputStream spif;
    private int length;
    private Color[] sequence;

    private int currentPos;
    private boolean staticPattern;

    private int cycleTimeMS;

    public LEDStrip(int length) {
        super("LEDStrip");
        this.length = length;
        sequence = new Color[length];
        try {
            spif = new FileOutputStream("/dev/spidev0.0");
        } catch (FileNotFoundException e) {
            System.out.println("LEDs failed to initialize!");
            e.printStackTrace();
        }
        changeMode(LEDMode.Red);
    }

    public void changeMode(LEDMode mode) {
        currentPos = 0;
        switch (mode) {
            case Red:
                staticPattern = true;
                for (int i = 0; i < sequence.length; i++) {
                    sequence[i] = new Color(200, 0, 0);
                }
                break;
            case Green:
                staticPattern = true;
                for (int i = 0; i < sequence.length; i++) {
                    sequence[i] = new Color(0, 200, 0);
                }
                break;
            case Blue:
                staticPattern = true;
                for (int i = 0; i < sequence.length; i++) {
                    sequence[i] = new Color(0, 0, 200);
                }
                break;
            case Off:
                staticPattern = true;
                for (int i = 0; i < sequence.length; i++) {
                    sequence[i] = new Color(0, 0, 0);
                }
                break;
        }

        try {
            spif.write(spiStart);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Color c : sequence) {
            try {
                byte[] b = c.toByteArray();
                /*System.out.println(spif);
                spif.write(new byte[]{1, 2, 3, 4, 5, 6});
                for(byte bb : b) {
                    System.out.print(bb);
                }*/
                //System.out.println(b);
                spif.write(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            spif.write(spiEnd);

            spif.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (staticPattern) {
            cycleTimeMS = 180000;
        }
    }

    public void update() {
        if (!staticPattern) {
            try {
                spif.write(spiStart);
                spif.write(sequence[currentPos].toByteArray());
                spif.write(spiEnd);
                spif.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentPos++;
            currentPos %= length;
        }
    }

    /**
     * Returns the time between updates of the currently selected sequence.
     * @return The time between updates of the currently selected sequence.
     */
    public int getCycleTimeMS() {
        return cycleTimeMS;
    }

    public int getLength() {
        return length;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public boolean isStaticPattern() {
        return staticPattern;
    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LEDRefresh());
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
            byte[] arr = {(byte)brightness, (byte)blue, (byte)green, (byte)red};
            return arr;
        }
    }
}
