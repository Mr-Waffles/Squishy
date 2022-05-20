// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Dimensions;
import static frc.robot.RobotContainer.driveTrain;

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
    addRequirements(driveTrain);

    driveSpeed = DriveSpeed;
    inchesDistance = DriveDistance;

    driveDistance = (inchesDistance / 
    (Dimensions.wheelCircumference.value / 
    Dimensions.gearRatio.value))
    + driveTrain.getEncoderRotations()[1];
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isFinished=false;

    driveDistance = (inchesDistance / 
    (Dimensions.wheelCircumference.value / 
    Dimensions.gearRatio.value)) // result is the number of motor rotations to cover inchesDistance
    + driveTrain.getEncoderRotations()[1];
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drive(-driveSpeed, 0);
    if (driveTrain.getEncoderRotations()[1] > driveDistance) isFinished=true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
