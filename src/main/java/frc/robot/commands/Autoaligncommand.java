/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;

public class Autoaligncommand extends CommandBase {
  /**
   * Creates a new Autoaligncommand.
   */
  public Autoaligncommand(Limelight n) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(n);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double kp = -0.1;
    double mincommand = 0.05;
    
    double error = -Limelight.getAngleX();
    double adjust = 0;
    if (-error > 1.0){

      adjust = kp*error-mincommand;

    }
    else if (-error > 1.0){

      adjust = kp*error + mincommand;

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
