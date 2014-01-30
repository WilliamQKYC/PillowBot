/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author MorTorq
 */
public class Intake {
    
    private Talon intakeMotor;
    private Piston leftPiston;
    private Piston rightPiston;
    private static final double INTAKE_VALUE = 1.0;
    private static final double FLUSH_VALUE = 1.0;
    
    public Intake(int motorPort,int solenoidPortOne,int solenoidPortTwo,int solenoidPortThree,int solenoidPortFour){
        this.intakeMotor = new Talon(1,motorPort);
        this.leftPiston = new Piston(solenoidPortOne,solenoidPortTwo);
        this.rightPiston = new Piston(solenoidPortThree,solenoidPortFour);
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
