/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.NeutralMode;




public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  public WPI_TalonSRX hookup;
  public WPI_TalonFX hookdown;

  public Climber() {
    hookup = new WPI_TalonSRX(Constants.hookupmotor);
    hookdown = new WPI_TalonFX(Constants.hookdownmotor);

    hookup.configFactoryDefault();
    hookdown.configFactoryDefault();

    hookup.setNeutralMode(NeutralMode.Brake);
    hookdown.setNeutralMode(NeutralMode.Brake);

    hookup.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    hookup.setSensorPhase(true);




  }
  public void manualControl(Double speed, double speedt){

    hookup.set(ControlMode.PercentOutput,speed);
    hookdown.set(ControlMode.PercentOutput,speedt);

  }
  
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
