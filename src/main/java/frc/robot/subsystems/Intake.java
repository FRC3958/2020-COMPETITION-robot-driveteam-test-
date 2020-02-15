/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   * 
   */
  public WPI_TalonSRX intakewheels;
  public Solenoid intakeonesol, intaketwosol;
  boolean intakeon;
  public Intake() {
    intakewheels = new WPI_TalonSRX(Constants.intakewheels);
    intakeonesol = new Solenoid(Constants.intakeonesol);
    //intaketwosol = new Solenoid(Constants.intaketwosol);
  }
  public void intakeposition(double speed){

    intakeonesol.set(true);
    intaketwosol.set(false);
    intakewheels.set(ControlMode.PercentOutput, speed);
    intakeon = true;

  }
  public void notintaking(){

    intakeonesol.set(false);
    intaketwosol.set(true);
    intakewheels.set(0);
    intakeon = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean(" Is intake on?", intakeon);
  }
}
