/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author MorTorq
 */
public class Piston {
    
    private Solenoid bottom;
    private Solenoid top;
    
    public Piston(int bottomPort, int topPort){
        this.bottom = new Solenoid(1,bottomPort);
        this.top = new Solenoid(1,topPort);
    }
    
    public void extend(){
        top.set(false);
        bottom.set(true);
    }
    
    public void retract(){
        top.set(true);
        bottom.set(false);
    }
}
