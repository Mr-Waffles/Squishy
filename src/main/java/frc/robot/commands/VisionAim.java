// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.RobotContainer.driveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionAim extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  private double kP = 0.05;
  private double kI = 0.00;
  private double kD = 0.00;
  private static PIDController pid = null;

  /** Creates a new VisionAim. */
  public VisionAim() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("P", SmartDashboard.getNumber("P", kP));
    SmartDashboard.putNumber("I", SmartDashboard.getNumber("I", kI));
    SmartDashboard.putNumber("D", SmartDashboard.getNumber("D", kD));
    if (pid == null ) pid = new PIDController(SmartDashboard.getNumber("P", kP),SmartDashboard.getNumber("I", kI),SmartDashboard.getNumber("D", kD));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = tx.getDouble(0.0);

    double tSpeed = -(pid.calculate(x));

    SmartDashboard.putNumber("LimeLightX", x);
    driveTrain.drive(0, tSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
