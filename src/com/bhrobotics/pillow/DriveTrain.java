/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author MorTorq
 */
public class DriveTrain {
    
    private MotorModule left;
    private MotorModule right;
    private Joystick joystick;
    private boolean twisted;
    private boolean chessy;
    private static final double DRIVING_SCALE = 1.0;
    private static final double TURNING_SCALE = 1.0;
    
    public DriveTrain(int motorPortOne, int motorPortTwo, int motorPortThree, int motorPortFour, int motorPortFive, int motorPortSix, Joystick joystick){
        this.left = new MotorModule(motorPortOne,motorPortThree,motorPortFive);
        this.right = new MotorModule(motorPortTwo,motorPortFour,motorPortSix);
        this.joystick = joystick;
        twisted = true;
        chessy = false;
    }
    
    public void setTwistTurn(){
        twisted = true;
    }
    
    public void setAxisTurn(){
        twisted = false;
    }
    
    public void setChessyDrive(){
        chessy = true;
    }
    
    public void setOneStickDrive(){
        chessy = false;
    }
    
    public void drive(){
        double y;
        double x;
        if(chessy){
            y = Math.sin(joystick.getDirectionRadians()) * joystick.getRawAxis(3);
            x = Math.cos(joystick.getDirectionRadians()) * joystick.getRawAxis(3);
        } else{
            y = joystick.getRawAxis(2);
            if(twisted){
                x = joystick.getRawAxis(4);
            } else{
                x = joystick.getRawAxis(1);
            }
        }
        y *= DRIVING_SCALE;
        x *= TURNING_SCALE;
        left.setSpeed(-y + x);
        right.setSpeed(y + x);
    }
    
}
