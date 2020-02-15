/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelightlib extends SubsystemBase {
  /**
   * Creates a new Limelightlib.
   * 
   */
  NetworkTable table;
  NetworkTableEntry xangle;
  NetworkTableEntry yangle;
  NetworkTableEntry vangle;
  NetworkTableEntry ledpower;
  NetworkTableEntry cammode;
  boolean validtarget;

  public Limelightlib() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    xangle = table.getEntry("tx");
    yangle = table.getEntry("ty");
    vangle = table.getEntry("tv");
    ledpower = table.getEntry("ledMode");
    cammode = table.getEntry("camMode");
    validtarget = false;
  }

  public double gettx() {

    return xangle.getDouble(0.0);
 }
 public double gety(){

  return yangle.getDouble(0.0);

 }
 public double gettv(){
  if (vangle.getDouble(0.0) == 0){

    validtarget = false;

  }
  else {
    validtarget = true;
  }
  return vangle.getDouble(0.0);
  

 }
public void setledmode(int mode) {

  ledpower.setDouble(mode);

}
public void setcammode(int mode){

  cammode.setDouble(mode);

}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("txvalue", gettx());
    SmartDashboard.putBoolean("Is there a valid target?", validtarget);
  }
}
