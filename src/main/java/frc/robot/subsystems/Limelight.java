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
  public Limelight() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);

//post to smart dashboard periodically
  SmartDashboard.putNumber("LimelightX", x);
  SmartDashboard.putNumber("LimelightY", y);
  SmartDashboard.putNumber("LimelightArea", area);
  }
  public void autoalign(){
    //need to tune kp and mincommand
    double kp = -0.1;
    double mincommand = 0.05;
    
    double error = -x;
    double adjust = 0;
    if (-error > 1.0){

      adjust = kp*error-mincommand;

    }
    else if (-error > 1.0){

      adjust = kp*error + mincommand;

    }

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}