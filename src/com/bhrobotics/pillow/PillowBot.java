/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.bhrobotics.pillow;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PillowBot extends IterativeRobot {
    
    
    private Joystick driveStick;
    private DriveTrain driveTrain;
    private Intake intake;
    private Catapult catapult;
    
    public void robotInit() {
        driveStick = new Joystick(1);
        driveTrain = new DriveTrain(1,2,3,4,5,6,1,2,3,4,driveStick); //check encoder ports (and now apparently there are only 2 motors)
        intake = new Intake(7,1,2,3,4,5,6); //check motor,solenoid, and encoder ports
        catapult = new Catapult(8,5,6,7,8); //check motor, solenoid, and encoder ports
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
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
        
        //intake motor
        if(driveStick.getRawButton(1)){
            intake.intake();
        } else if(driveStick.getRawButton(6)){
            intake.flush();
        } else{
            intake.stop();
        }
        
        //intake position
        if(driveStick.getRawButton(3)){
            intake.raise();
        } else if(driveStick.getRawButton(4)){
            intake.lower();
        }
        
        //catapult shooting
        if(driveStick.getRawButton(2)){
            catapult.shoot();
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
