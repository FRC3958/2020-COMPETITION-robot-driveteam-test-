/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HoodedShooter;
import frc.robot.subsystems.indexerandbelttoshooter;
import frc.robot.Constants;

public class Shootatrpm extends CommandBase {
  /**
   * Creates a new Shootatrpm.
   */
  private HoodedShooter m_shooter;
  private indexerandbelttoshooter m_index;
  private double rpmnum;
  private double nativenum;

  public Shootatrpm(HoodedShooter h, double rpmnum, double nativenum) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(h);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setRPM(rpmnum);
    m_shooter.setNative(nativenum);

    if (m_shooter.isReadyToShoot() == true){

      m_index.towardstheshooter(Constants.indexspeed, Constants.beltspeed);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
