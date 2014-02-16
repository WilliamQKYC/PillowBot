/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhrobotics.pillow;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author MorTorq
 */
public class DriveTrain {
    
    private MotorModule left;
    private MotorModule right;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private Joystick joystick;
    private boolean twisted;
    private boolean chessy;
    private static final double DRIVING_SCALE = 1.0;
    private static final double TURNING_SCALE = 1.0;
    private static final double LEFT_SPEED_TRIM = 1.0;
    private static final double RIGHT_SPEED_TRIM = 1.0;
    private boolean isReversed;
    
    public DriveTrain(int motorPortOne, int motorPortTwo, int motorPortThree, int motorPortFour, int encoderSlotOne, int encoderSlotTwo, int encoderSlotThree, int encoderSlotFour, Joystick joystick){
        this.left = new MotorModule(motorPortTwo,motorPortFour);
        this.right = new MotorModule(motorPortOne,motorPortThree);
        //this.leftEncoder = new Encoder(new DigitalInput(1,encoderSlotOne),new DigitalInput(1,encoderSlotTwo));
        //this.rightEncoder = new Encoder(new DigitalInput(1,encoderSlotThree),new DigitalInput(1,encoderSlotFour));
        this.joystick = joystick;
        // changed booleans
        twisted = true;
        chessy = false;
        isReversed = false;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public void setIsReversed(boolean isReversed) {
        this.isReversed = isReversed;
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
        double reverseFactor = isReversed? -1.0 : 1.0;
        double y;
        double x;
        
        if(chessy){
            y = Math.sin(joystick.getDirectionRadians()) * joystick.getRawAxis(3);
            if(twisted){
                x = Math.cos(MathUtils.atan(joystick.getRawAxis(2)/joystick.getRawAxis(6))) * joystick.getRawAxis(3);
            } else{
                x = Math.cos(joystick.getDirectionRadians()) * joystick.getRawAxis(3);
            }
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
        left.setSpeed(LEFT_SPEED_TRIM*reverseFactor*(-y - x));
        right.setSpeed(RIGHT_SPEED_TRIM*reverseFactor*(y + x));
//        System.out.print("x=" + x);
//        System.out.print(" y=" + y);
//        System.out.print(" 2=" + joystick.getRawAxis(2));
//        System.out.print(" 3=" + joystick.getRawAxis(3));
//        System.out.print(" 6=" + joystick.getRawAxis(6));
//        System.out.println("");
    }
    
    public void autoDrive(double leftSpeed, double rightSpeed){
        left.setSpeed(leftSpeed);
        right.setSpeed(rightSpeed);
    }
    
    public void stop(){
        left.stop();
        right.stop();
    }
}
