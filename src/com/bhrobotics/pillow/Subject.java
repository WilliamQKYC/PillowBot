/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhrobotics.pillow;

/**
 *
 * @author MorTorq
 */
public abstract class Subject {
    
    public abstract void attach(Observer observer);
    
    public abstract void detach(Observer observer);
    
    public abstract void notifyObservers();
}
