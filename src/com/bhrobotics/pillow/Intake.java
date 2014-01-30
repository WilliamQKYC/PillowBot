/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author MorTorq
 */
public class Intake {
    
    private Talon intakeMotor;
    private Piston leftPiston;
    private Piston rightPiston;
    private Encoder intakeEncoder;
    private static final double INTAKE_VALUE = 1.0;
    private static final double FLUSH_VALUE = 1.0;
    
    public Intake(int motorPort,int solenoidPortOne,int solenoidPortTwo,int solenoidPortThree,int solenoidPortFour,int encoderSlotOne,int encoderSlotTwo){
        this.intakeMotor = new Talon(1,motorPort);
        this.leftPiston = new Piston(solenoidPortOne,solenoidPortTwo);
        this.rightPiston = new Piston(solenoidPortThree,solenoidPortFour);
        this.intakeEncoder = new Encoder(new DigitalInput(1,encoderSlotOne),new DigitalInput(1,encoderSlotTwo));
        raise();
    }
    
    public void intake(){
        intakeMotor.set(INTAKE_VALUE);
    }
    
    public void flush(){
        intakeMotor.set(FLUSH_VALUE);
    }
    
    public void raise(){
        leftPiston.retract();
        rightPiston.retract();
    }
    
    public void lower(){
        leftPiston.extend();
        rightPiston.extend();
    }
}
