// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveToDistance extends CommandBase {

    private boolean isFinished = false;
    private double driveSpeed = 0.2;
    private double inchesDistance = 12;
    private double driveDistance = 0;

  /** Creates a new DriveToDistance. 
   * 
   * @param DriveSpeed The speed to drive at.
   * @param DriveDistance The distance to drive in inches.
  */
  public DriveToDistance(double DriveSpeed, double DriveDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    isFinished=false;
    addRequirements(RobotContainer.driveTrain);
    driveSpeed = DriveSpeed;
    inchesDistance = DriveDistance;
    driveDistance = (inchesDistance / 
    Constants.Dimensions.wheelCircumference.value / 
    Constants.Dimensions.gearRatio.value)
    + RobotContainer.driveTrain.getEncoderRotations()[1];
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("inches", inchesDistance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.driveTrain.drive(-driveSpeed, 0);
    SmartDashboard.putNumber("driveDistance", driveDistance);
    SmartDashboard.putNumber("distance", RobotContainer.driveTrain.getEncoderRotations()[1]);
    if (RobotContainer.driveTrain.getEncoderRotations()[1] > driveDistance) isFinished=true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ended"+interrupted);
    RobotContainer.driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
