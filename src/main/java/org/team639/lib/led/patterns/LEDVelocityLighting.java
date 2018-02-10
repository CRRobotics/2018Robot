package org.team639.lib.led.patterns;

import org.team639.lib.led.LEDColor;
import org.team639.lib.led.LEDPattern;
import org.team639.robot.subsystems.DriveTrain;


public class LEDVelocityLighting extends LEDPattern {

    private LEDColor[] pattern;
    private DriveTrain driveTrain;
    int maxSpeed;
    public LEDVelocityLighting(int length, int maxSpeed, DriveTrain driveTrain) {
        pattern = new LEDColor[length];
        this.maxSpeed = maxSpeed;
        this.driveTrain = driveTrain;
    }

    @Override
    public LEDColor[] start() {
        return pattern;
    }

    @Override
    public void stop() {

    }

    @Override
    public LEDColor[] nextPortion() {
        double percent = driveTrain.getLeftEncVelocity() / maxSpeed;
        double percentRed;
        double percentGreen;
        if(percent > .50) {
            percentRed = percent;
            percentGreen = percent / 2;
        }
        else {
            percentRed = percent / 2;
            percentGreen = percent;
        }
        for(int i = 0; i < round(percent*pattern.length); i++) {
            pattern[i] = new LEDColor(round(255*percentRed), round(percentGreen*255), 0);
        }
        for(int i = round(percent*pattern.length); i < pattern.length; i++){
            pattern[i] = new LEDColor(0,0,0); 
        }
        return pattern;
    }

    private int round(double d) {
        if((int)(d + 0.5) > (int) d) return (int)d + 1;
        else return (int)d;
    }
}
