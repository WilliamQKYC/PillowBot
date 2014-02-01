/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author m3hr
 */
public class Catapult {
    
    private Talon winchMotor;
    private Piston shifter;
    private Encoder winchEncoder;
    private static final double INTAKE_VALUE = 0.5;
    
    private Timer timer = new Timer();
    
    public Catapult(int winchMotorPort, int solenoidPortOne, int solenoidPortTwo, int encoderSlotOne, int encoderSlotTwo){
        this.winchMotor = new Talon(1,winchMotorPort);
        this.shifter = new Piston(solenoidPortOne,solenoidPortTwo);
        this.winchEncoder = new Encoder(new DigitalInput(1,encoderSlotOne),new DigitalInput(1,encoderSlotTwo));
        winchEncoder.start();
    }
    
    public void retract(){
        shifter.retract();
        timer.schedule(new TimerTask() {
            public void run(){
                if(winchEncoder.getDistance() > 0.0){
                    winchMotor.set(INTAKE_VALUE);
                }
            }
        
        },2000);
    }
            
    public void shoot(){
        shifter.extend();
        timer.schedule(new TimerTask() {
            public void run(){
                
            }
        
        },2000);
        shifter.retract();
    }    
    
}
