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

//import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author m3hr
 */
public class Catapult {
    
    private Talon winchMotor;
    private Piston shifter;
    private DigitalInput limitSwitchOne;
    private DigitalInput limitSwitchTwo;
    //private Encoder winchEncoder;
    private static final double INTAKE_VALUE = 0.5;
    public static final long WINCH_MAX_RUNTIME = 10000;
    private Timer timer = new Timer();
    
    public Catapult(int winchMotorPort, int solenoidPortOne, int solenoidPortTwo, int encoderSlotOne, int encoderSlotTwo, int limitSwitchPortOne, int limitSwitchPortTwo){
        this.winchMotor = new Talon(1,winchMotorPort);
        this.shifter = new Piston(solenoidPortOne,solenoidPortTwo);
        this.limitSwitchOne = new DigitalInput(1,limitSwitchPortOne);
        this.limitSwitchTwo = new DigitalInput(1,limitSwitchPortTwo);

        //this.winchEncoder = new Encoder(new DigitalInput(1,encoderSlotOne),new DigitalInput(1,encoderSlotTwo));
        //winchEncoder.start();
    }
    
    public void retract(){
        shifter.extend();

        timer.schedule(new TimerTask() {
            public void run(){
               winchSpool();       
            }
        
        },2000);
    }
            
    public void shoot(){
        shifter.retract();
        timer.schedule(new TimerTask() {
            public void run(){
                
            }
        
        },2000);
        shifter.retract();
    }    
    
    public boolean isRetracted() {
        return limitSwitchOne.get() || limitSwitchTwo.get();
    }
    
    public void clutchOpen() {
        shifter.retract();
    }
    
    public void clutchClose() {
        shifter.extend();
    }
    
    public void winchSpool() {
//        long motorStartTime = System.currentTimeMillis();
//        winchMotor.set(INTAKE_VALUE);
//        while (true) {                    
//            if (isRetracted())
//                break;
//            if ((System.currentTimeMillis()- motorStartTime)> WINCH_MAX_RUNTIME)
//                break;
//            edu.wpi.first.wpilibj.Timer.delay(0.01);
//        }
//        winchMotor.set(0.0);
//      
        if(!isRetracted())
            winchMotor.set(INTAKE_VALUE);
        else
            winchMotor.set(0);
    }
    
    public void winchStop() {
        winchMotor.set(0);
    }
}
