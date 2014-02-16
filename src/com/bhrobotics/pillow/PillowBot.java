/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.bhrobotics.pillow;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import java.util.Timer;
import java.util.TimerTask;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PillowBot extends IterativeRobot {
    
    
    private Joystick driveStick;
    private Joystick secondStick;
    private DriveTrain driveTrain;
    private Intake intake;
    private Catapult catapult;
    private Relay compressor;
   // private AxisCamera camera;
    private DigitalInput valve;
    //CriteriaCollection cc; 
    //private VisionTracker driveCam;
    // button commands for driver 2
    public static final int INTAKE_UP = 11;
    public static final int INTAKE_DOWN = 12;
    public static final int WINCH_CLUTCH_ENGAGE = 7;
    public static final int WINCH_CLUTCH_DISENGAGE = 8;
    public static final int WINCH_SPOOL = 1;
    public static final int INTAKE_MOTOR_FORWARD = 5;
    public static final int INTAKE_MOTOR_BACKWARD = 3;
    
    // Digital I/O
    public static final int COMPRESSOR_GAUGE = 1;
    public static final int RIGHT_LIMIT_SWITCH = 2;
    public static final int LEFT_LIMIT_SWITCH = 3;
    // button commands for driver 1
    public static final int REVERSE_DRIVE = 1;
     
    public void robotInit() {
        driveStick = new Joystick(1);
        secondStick = new Joystick(2);
        driveTrain = new DriveTrain(1,2,3,4,1,2,3,4,driveStick); //check encoder ports (and now apparently there are only 2 motors)
        intake = new Intake(5,1,2,5,6); //check motor, solenoid, and encoder ports
        catapult = new Catapult(6,3,4,7,8,RIGHT_LIMIT_SWITCH,LEFT_LIMIT_SWITCH); //check motor, solenoid, and encoder ports
        compressor = new Relay(1, 8);
      //  camera = AxisCamera.getInstance();
      //  cc = new CriteriaCollection();      // create the criteria for the particle filter
       // driveCam = new VisionTracker();
        valve = new DigitalInput(1,COMPRESSOR_GAUGE);
    }

    public void robotMaintenance() {
        // PSI set to factory value automatically
        if (valve.get()) {
            System.out.println("valve off");
            compressor.set(Relay.Value.kForward);
        } else {
            System.out.println("valve on");
        compressor.set(Relay.Value.kOff);
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        robotMaintenance();
	Timer timer = new Timer();
        driveTrain.autoDrive(0.75, 0.75);
        //driveCam.autonomous();
        //if (driveCam.hot)
            catapult.shoot();
                
        timer.schedule(new TimerTask() {
            public void run() {
                driveTrain.stop();
            }           
        }, 1200);
    }
    

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        robotMaintenance();
        if(driveStick.getRawButton(REVERSE_DRIVE)){
            driveTrain.setIsReversed(true);
        }
        else {
            driveTrain.setIsReversed(false);         
        }

        //set drive style
        if(driveStick.getRawButton(10)){
            driveTrain.setChessyDrive();
        } else if(driveStick.getRawButton(9)){
            driveTrain.setOneStickDrive();
        }
        
        //set turn style
        if(driveStick.getRawButton(11)){
            driveTrain.setAxisTurn();
        } else if(driveStick.getRawButton(12)){
            driveTrain.setTwistTurn();
        }
        
        //winch winding
        if(secondStick.getRawButton(WINCH_SPOOL)){
            catapult.winchSpool();
             System.out.println("winchspool");
        } else {
            catapult.winchStop();
                         System.out.println("not winch");

        }

        
        //intake motor
        if(secondStick.getRawButton(INTAKE_MOTOR_FORWARD) && !secondStick.getRawButton(INTAKE_MOTOR_BACKWARD)){
            intake.intake();

        } else if(secondStick.getRawButton(INTAKE_MOTOR_BACKWARD) && !secondStick.getRawButton(INTAKE_MOTOR_FORWARD)){
            intake.flush();
        } else{
            intake.stop();
        }
        
        //intake position
        if(secondStick.getRawButton(INTAKE_UP) && !secondStick.getRawButton(INTAKE_DOWN)){
            intake.raise();
                         System.out.println("intake up");

        } else if(secondStick.getRawButton(INTAKE_DOWN) && !secondStick.getRawButton(INTAKE_UP)){
            intake.lower();
                         System.out.println("intake down");

        }
                
        //catapult shooting
        if(secondStick.getRawButton(WINCH_CLUTCH_ENGAGE) && !secondStick.getRawButton(WINCH_CLUTCH_DISENGAGE)){
            catapult.clutchOpen();
                         System.out.println("clutch open");

        } else if(secondStick.getRawButton(WINCH_CLUTCH_DISENGAGE) && !secondStick.getRawButton(WINCH_CLUTCH_ENGAGE)){
           catapult.clutchClose();
                        System.out.println("clutch close");

        }
        
        //drive code
        driveTrain.drive();  
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
