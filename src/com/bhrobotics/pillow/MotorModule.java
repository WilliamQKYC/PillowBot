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
public class MotorModule {
    
    private Talon one;
    private Talon two;
  //  private Talon three;
    
    public MotorModule(int portOne, int portTwo){
        this.one = new Talon(1,portOne);
        this.two = new Talon(1,portTwo);
       // this.three = new Talon(1,portThree);
    }
    
    public void setSpeed(double speed){
        if(speed > 1.0){
            speed = 1.0;
        }
        if(speed < -1.0){
            speed = -1.0;
        }
        one.set(speed);
        two.set(speed);
        //three.set(speed);
    }
    
    public void stop(){
        setSpeed(0.0);
    }
}
