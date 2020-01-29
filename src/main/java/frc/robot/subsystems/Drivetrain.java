/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.function.Consumer;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  public enum TalonGroup {
    kAll,
    kLeft,
    kRight,
    kMasters,
    kSlaves
  }
  
  private final WPI_TalonSRX m_masterLeftTalon = new WPI_TalonSRX(Constants.Drivetrain.Map.kBackLeftTalonPort);
  private final WPI_TalonSRX m_slaveLeftTalon = new WPI_TalonSRX(Constants.Drivetrain.Map.kFrontLeftTalonPort);
  private final WPI_TalonSRX m_masterRightTalon =  new WPI_TalonSRX(Constants.Drivetrain.Map.kBackRightTalonPort);
  private final WPI_TalonSRX m_slaveRightTalon = new WPI_TalonSRX(Constants.Drivetrain.Map.kFrontRightTalonPort);

  private final AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_masterLeftTalon, m_masterRightTalon);

  // private final PIDController m_leftPidController = new PIDController(0.f, 0.f, 0.f);
  // private final PIDController m_rightPidController = new PIDController(0.f, 0.f, 0.f);

  private void applyToTalons(TalonGroup group, Consumer<WPI_TalonSRX> consumer) {
    switch(group) {
      case kAll:

      consumer.accept(m_masterLeftTalon);
      consumer.accept(m_slaveLeftTalon);
      consumer.accept(m_masterRightTalon);
      consumer.accept(m_slaveRightTalon);
      break;

      case kLeft:
      consumer.accept(m_masterLeftTalon);
      consumer.accept(m_slaveLeftTalon);
      break;

      case kRight:
      consumer.accept(m_masterRightTalon);
      consumer.accept(m_slaveRightTalon);
      break;

      case kMasters:
      consumer.accept(m_masterLeftTalon);
      consumer.accept(m_masterRightTalon);
      break;

      case kSlaves:
      consumer.accept(m_slaveLeftTalon);
      consumer.accept(m_slaveRightTalon);
      break;
    }
  }

  /**
   * Creates a new DriveSubsystem.
   */
  public Drivetrain() {

    // reset to defaults
    m_ahrs.zeroYaw();

    applyToTalons(TalonGroup.kAll, allTalons -> {

      allTalons.configFactoryDefault();
      allTalons.setNeutralMode(NeutralMode.Brake);
    });

    // slavery
    m_slaveLeftTalon.follow(m_masterLeftTalon);
    m_slaveRightTalon.follow(m_masterRightTalon);

    // invert motors
    m_drive.setRightSideInverted(false);

    m_masterLeftTalon.setInverted(InvertType.InvertMotorOutput);
    m_masterRightTalon.setInverted(InvertType.None);
    
    applyToTalons(TalonGroup.kSlaves, slaveTalons -> slaveTalons.setInverted(InvertType.FollowMaster));

    // setup encoders
    applyToTalons(TalonGroup.kMasters, masterTalons -> {

      masterTalons.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
      masterTalons.setSensorPhase(true);
    });

    // pid
    // m_leftPidController.disableContinuousInput();
    // m_rightPidController.disableContinuousInput();
    // SmartDashboard.putData("leftPidController", m_leftPidController);
    // SmartDashboard.putData("rightPidController", m_rightPidController);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putNumber("yaw", getYaw());

    // m_masterLeftTalon.pidWrite(m_leftPidController.calculate(
    //   m_masterLeftTalon.getSelectedSensorVelocity(),
    //   m_masterLeftTalon.get() * Constants.Drivetrain.kMaxVelocity) / (float)Constants.Drivetrain.kMaxVelocity
    // );

    // m_masterRightTalon.pidWrite(m_rightPidController.calculate(
    //   m_masterRightTalon.getSelectedSensorVelocity(), 
    //   m_masterRightTalon.get() * Constants.Drivetrain.kMaxVelocity)  / (float)Constants.Drivetrain.kMaxVelocity
    // );
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double speed, double rot) {
    m_drive.arcadeDrive(speed, rot);
  }

  public void resetYaw() {
    m_ahrs.zeroYaw();
  }

  public double getYaw() {
    return m_ahrs.getYaw();
  }

  public double getDistance() {
    return (float)(m_masterLeftTalon.getSelectedSensorPosition() + m_masterRightTalon.getSelectedSensorPosition()) / 2.f;
  }

  public double getVelocity() {
    return (float)(m_masterLeftTalon.getSelectedSensorVelocity() + m_masterRightTalon.getSelectedSensorVelocity()) / 2.f;
  }
  
  
}
