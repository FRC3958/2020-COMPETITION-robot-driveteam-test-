/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
  /**
   * Creates a new Limelight.
   */
  static NetworkTable table;

  public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");

  }

  public static double getAngleX() {
    return table.getEntry("tx").getDouble(0);
  }

  public static double getAngleY() {
    return table.getEntry("ty").getDouble(0);
  }

  public static  double getArea() {
    return table.getEntry("ta").getDouble(0);
  }

  public static  double getSkew() {
    return table.getEntry("ts").getDouble(0);
  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
