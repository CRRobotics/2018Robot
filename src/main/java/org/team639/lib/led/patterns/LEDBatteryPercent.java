package org.team639.lib.led.patterns;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import org.team639.lib.led.LEDColor;
import org.team639.lib.led.LEDPattern;

public class LEDBatteryPercent extends LEDPattern{

    private LEDColor[] pattern;
    private double percent;
    public LEDBatteryPercent(int length) {
        pattern = new LEDColor[length];
        percent = RobotController.getBatteryVoltage() / 13;
    }

    @Override
    public LEDColor[] start() {
        percent = (RobotController.getBatteryVoltage() - 5) / 8;
        for(int i = 0; i < round(percent*pattern.length); i++) {
            pattern[i] = new LEDColor(round(255*(1-percent)), round(255*percent), 0);
        }
        for(int i = round(percent*pattern.length); i < pattern.length; i++) {
                pattern[i] = new LEDColor(0,0,0);
        }
        return pattern;
    }

    @Override
    public void stop() {

    }

    @Override
    public LEDColor[] nextPortion() {
        pattern = start();
        return pattern;
    }

    private int round(double d) {
        if((int)(d + 0.5) > (int) d) return (int)d + 1;
        else return (int)d;
    }
}