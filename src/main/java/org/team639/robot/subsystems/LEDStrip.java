package org.team639.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team639.lib.led.LEDColor;
import org.team639.lib.led.LEDPattern;
import org.team639.lib.led.patterns.LEDBatteryPercent;
import org.team639.lib.led.patterns.LEDBlink;
import org.team639.lib.led.patterns.LEDOff;
import org.team639.robot.commands.leds.LEDRefresh;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LEDStrip extends Subsystem {

    private final byte[] spiStart = {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
    private final byte[] spiEnd = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};

    private FileOutputStream spif;
    private int length;

    private LEDPattern pattern;

    public LEDStrip(int length) {
        super("LEDStrip");
        this.length = length;
        try {
            spif = new FileOutputStream("/dev/spidev0.0");
        } catch (FileNotFoundException e) {
            System.out.println("LEDs failed to initialize!");
            e.printStackTrace();
        }
        pattern = new LEDOff(length);

        changeMode(new LEDBatteryPercent(length));
    }

    public void changeMode(LEDPattern pattern) {
        this.pattern.stop();
        this.pattern = pattern;

        LEDColor[] sequence = pattern.start();

        if (sequence.length > 0) {
            try {
                spif.write(spiStart);
                for (LEDColor c : sequence) {
                    byte[] b = c.toByteArray();
                    spif.write(b);
                }
                spif.write(spiEnd);
                spif.flush();

                } catch(IOException e){
                    e.printStackTrace();
                }
        }
    }

    public void update() {
        LEDColor[] sequence = pattern.nextPortion();
        if (sequence.length > 0) {
            try {
                spif.write(spiStart);
                for (LEDColor c : sequence) {
                    spif.write(c.toByteArray());
                }
                spif.write(spiEnd);
                spif.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getLength() {
        return length;
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

}
