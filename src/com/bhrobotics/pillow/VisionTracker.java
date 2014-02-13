/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bhrobotics.pillow;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;

/**
 *
 * @author m3hr
 */
public class VisionTracker {
    AxisCamera visionCamera;
    CriteriaCollection particleCriteria;      // the criteria for doing the particle filter operation

    static public int rmin = 0, rmax = 90, gmin = 50, gmax = 255, bmin = 150, bmax = 255;
     
}
