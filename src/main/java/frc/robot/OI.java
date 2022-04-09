// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/** Add your docs here. */
public class OI {

    private static final XboxController DriverXbox = new XboxController(0);

    public static double getY() {
        return DriverXbox.getLeftY();
    }

    public static double getX() {
        return -(DriverXbox.getRightX());
    }

    public void mapButtons () {
        return;
    }
}
