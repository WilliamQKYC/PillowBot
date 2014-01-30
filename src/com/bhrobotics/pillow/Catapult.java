/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author m3hr
 */
public class Catapult {
    
    private Talon winchMotor;
    private Piston shifter;
    private Encoder winchEncoder;
    
    public Catapult(int winchMotorPort, int solenoidPortOne, int solenoidPortTwo, int encoderSlotOne, int encoderSlotTwo){
        this.winchMotor = new Talon(1,winchMotorPort);
        this.shifter = new Piston(solenoidPortOne,solenoidPortTwo);
        this.winchEncoder = new Encoder(new DigitalInput(1,encoderSlotOne),new DigitalInput(1,encoderSlotTwo));
    }
    
    public void retract(){
        shifter.retract();
        //finish later
    }
    
}
