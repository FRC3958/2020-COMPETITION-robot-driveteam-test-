/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class indexerandbelttoshooter extends SubsystemBase {
  /**
   * Creates a new indexerandbelttoshooter.
   */
  public WPI_TalonSRX indexmotor,beltmotor;
  public indexerandbelttoshooter() {
    indexmotor = new WPI_TalonSRX(Constants.indexmotor);
    beltmotor = new WPI_TalonSRX(Constants.beltmotor);
  }
  //these are certain methods I made that mght be used or not used.
  public void towardstheshooter(double indexspeed, double beltspeed){

      indexmotor.set(ControlMode.PercentOutput,indexspeed);
      beltmotor.set(ControlMode.PercentOutput,beltspeed);
  }
  public void stopindexing(){

    indexmotor.set(0);
    beltmotor.set(0);

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
